package net.derev.nuts;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Vector;

import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;

import platform.Omgewing;

import net.derev.infrastruktuur.VastePare;
import net.derev.infrastruktuur.VasteStringe;

public class Http {
	private static final String UA = "Mozilla/5.0 (Windows; U; Windows NT 6.0; en-US; rv:1.9.1.3) Gecko/20090824 Firefox/3.5.3";

	private final HttpResultaat resultaat;

	private Http(Omgewing omgewing, String metode, String url, String koekie, String verwyser,
			VastePare vormInvoer) throws IOException, InterruptedException {
		resultaat = doen(omgewing, metode, url, koekie, verwyser, vormInvoer);
	}

	public static HttpResultaat lees(Omgewing omgewing, String url, String koekie, String verwyser)
			throws IOException, InterruptedException {
		return new Http(omgewing, HttpConnection.GET, url, koekie, verwyser, null).geeResultaat();
	}

	private static void stel(HttpConnection konneksie, String naam,
			String waarde) throws IOException {
		konneksie.setRequestProperty(naam, waarde);
		System.out.println(naam + ": " + waarde);
	}

	private static void stelHofies(HttpConnection konneksie, String koekie,
			String verwyser) throws IOException {
		stel(konneksie, HttpResultaat.HOOF_NAVRAAG_UA, UA);
		stel(konneksie, HttpResultaat.HOOF_NAVRAAG_ACCEPT, "text/html");
		stel(konneksie, HttpResultaat.HOOF_NAVRAAG_ACCEPT_LANG, "en");
		stel(konneksie, HttpResultaat.HOOF_NAVRAAG_ACCEPT_CHAR, "utf-8");
		stel(konneksie, HttpResultaat.HOOF_NAVRAAG_ACCEPT_ENC, "gzip");
		if (verwyser != null)
			stel(konneksie, HttpResultaat.HOOF_NAVRAAG_REFERER, verwyser);
		if (koekie != null)
			stel(konneksie, HttpResultaat.HOOF_NAVRAAG_COOKIE, koekie);
	}

	private static VasteStringe kryHofies(Omgewing omgewing, HttpConnection konneksie)
			throws IOException {
		Vector dubbels = new Vector();
		int i = 0;
		String hofie = konneksie.getHeaderFieldKey(i);
		while (hofie != null) {
			dubbels.addElement(hofie);
			String waarde = konneksie.getHeaderField(i);
			dubbels.addElement(waarde);
			System.out.println(hofie + ": " + waarde);
			hofie = konneksie.getHeaderFieldKey(++i);
		}
		return new VasteStringe(omgewing, dubbels);
	}

	private static HttpResultaat doen(Omgewing omgewing, String metode, String url,
			String koekie, String verwyser, VastePare vormInvoer) throws IOException,
			InterruptedException {
		System.out.println(metode + " " + url);
		final HttpConnection konneksie = (HttpConnection) Connector.open(url);
		konneksie.setRequestMethod(metode);
		stelHofies(konneksie, koekie, verwyser);
		stelVormInvoer(konneksie, vormInvoer);
		try {
			int responsKode = konneksie.getResponseCode();
			System.out.println(StringFunksies.formatteer(
					"HTTP status {0} url {1}", Integer.toString(responsKode),
					url));
			if (responsKode >= 300 && responsKode < 400) {
				String verwysUrl = konneksie
						.getHeaderField(HttpResultaat.HOOF_NAVRAAG_LOCATION);
				if (verwysUrl != null && verwysUrl.length() != 0)
					return doen(omgewing, metode, verwysUrl, koekie, verwyser, vormInvoer);
				long beginWag = System.currentTimeMillis();
				while (System.currentTimeMillis() - beginWag < 1000
						&& responsKode == HttpConnection.HTTP_TEMP_REDIRECT) {
					Thread.sleep(100);
					responsKode = konneksie.getResponseCode();
				}
			}
			if (responsKode >= 300 && responsKode < 400)
				throw new IllegalStateException(StringFunksies.formatteer(
						"HTTP status:{0}", Integer.toString(responsKode)));
			VasteStringe hofies = kryHofies(omgewing, konneksie);
			long inhoudGrootte = konneksie.getLength();
			if (inhoudGrootte > Integer.MAX_VALUE)
				throw new StringIndexOutOfBoundsException();
			if (inhoudGrootte != -1) {
				byte[] rouData = new byte[(int) inhoudGrootte];
				final DataInputStream in = konneksie.openDataInputStream();
				try {
					in.readFully(rouData);
					return new HttpResultaat(omgewing, rouData, hofies, url, koekie);
				} finally {
					in.close();
				}
			} 
			return new HttpResultaat(omgewing, Stroom.leesAlles(konneksie.openInputStream()), hofies, url, koekie);
				
/*			final InputStream inStroom = konneksie.openInputStream();
			try {
				byte[] antwoord = inhoudGrootte == -1 || inhoudGrootte > Integer.MAX_VALUE ? Stroom
						.leesAlles(inStroom) : Stroom.leesPresies(inStroom,
						(int) inhoudGrootte);
				return new HttpResultaat(antwoord, hofies, url, koekie);
			} finally {
				inStroom.close();
			}*/
		} finally {
			konneksie.close();
		}
	}

	private static void stelVormInvoer(HttpConnection konneksie,
			VastePare vormInvoer) throws IOException {
		if (vormInvoer == null)
			return;
		StringBuffer invoer = new StringBuffer();
		for (int invoerPos = 0; invoerPos < vormInvoer.geeLengte(); ++invoerPos) {
			if (invoer.length() != 0)
				invoer.append('&');
			invoer.append(vormInvoer.geeSleutel(invoerPos)).append('=');
			invoer.append(StringFunksies.urlEnkodeer(vormInvoer.geeWaarde(
					invoerPos).toString()));
		}
		String invoerTeks = invoer.toString();
		stel(konneksie, HttpResultaat.HOOF_NAVRAAG_CONTENT_TYPE,
				"application/x-www-form-urlencoded");
		stel(konneksie, HttpResultaat.HOOF_NAVRAAG_CONTENT_LENGTH, Integer
				.toString(invoerTeks.length()));
		System.out.println(invoerTeks);
		final OutputStream uit = konneksie.openOutputStream();
		try {
			uit.write(invoerTeks.getBytes());
			uit.flush();
		} finally {
			uit.close();
		}
	}

	public static HttpResultaat plaas(Omgewing omgewing, String url, String koekie, String verwyser,
			VastePare vormInvoer) throws IOException, InterruptedException {
		return new Http(omgewing, HttpConnection.POST, url, koekie, verwyser, vormInvoer)
				.geeResultaat();
	}

	private HttpResultaat geeResultaat() {
		return resultaat;
	}
}
