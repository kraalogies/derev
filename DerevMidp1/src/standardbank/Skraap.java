package standardbank;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Vector;

import platform.Omgewing;

import net.derev.infrastruktuur.Ontleder;
import net.derev.infrastruktuur.VastePare;
import net.derev.nuts.Http;
import net.derev.nuts.HttpResultaat;
import net.derev.nuts.StringFunksies;

public class Skraap {
	public static HttpResultaat hopNaMasjien(Omgewing omgewing, String beginUrl)
			throws IOException, InterruptedException {
		return Http.lees(omgewing, beginUrl, null, null);
	}

	public static HttpResultaat kryKoekie(Omgewing omgewing, String masjien, String koekie)
			throws IOException, InterruptedException {
		return Http.lees(omgewing, "https://" + masjien + "/ibsa/InternetBanking",
				koekie, null);
	}

	private static HttpResultaat tekenAan(Omgewing omgewing, String masjien, String koekie,
			VastePare vastePare) throws IOException, InterruptedException {
		return Http.plaas(omgewing, "https://" + masjien + "/ibsa/customer/signon.do",
				koekie, null, vastePare);
	}

	public static HttpResultaat kryBalans(Omgewing omgewing, String masjien, String koekie)
			throws UnsupportedEncodingException, IOException,
			InterruptedException {
		return Http.lees(omgewing,
				"https://" + masjien + "/ibsa/accounts/getbalances.do", koekie,
				null);
	}

	private static long skakelOmVanRand(String rand) {
		boolean negatief = rand.endsWith("-");
		rand = StringFunksies.verwyderKarakters(rand, "R,.+- ");
		long sente = Long.parseLong(rand);
		return negatief ? sente * -1 : sente;
	}

	private static Balans kryBalans(String inhoud, int posisie) {
		final int tdRekeningNaamPos0 = Ontleder.kryPosisie(inhoud, "td",
				posisie);
		String rekeningNaam = Ontleder.kryElementTeks(inhoud,
				tdRekeningNaamPos0);
		rekeningNaam = Ontleder.maakSkoon(rekeningNaam);
		// assertEquals("VAN DER MERWE K", tdRekeningNaam0);

		final int spanRekeningNommerPos = Ontleder.kryPosisie(inhoud, "span",
				tdRekeningNaamPos0);
		final String rekeningNommer = Ontleder.kryElementTeks(inhoud,
				spanRekeningNommerPos);
		// assertEquals("01-234-123-4", );

		final int tdRekeningTipePos = Ontleder.kryPosisie(inhoud, "td",
				spanRekeningNommerPos);
		final String rekeningTipe = Ontleder.maakSkoon(Ontleder.kryElementTeks(
				inhoud, tdRekeningTipePos));
		// assertEquals("ELITE PLUS",

		final int tdBeskikbaarPos = Ontleder.kryPosisie(inhoud, "td",
				tdRekeningTipePos + 1);
		final String beskikbaar = Ontleder.maakSkoon(Ontleder.kryElementTeks(
				inhoud, tdBeskikbaarPos));
		// assertEquals("R 45,370.42", );

		final int tdBalansPos = Ontleder.kryPosisie(inhoud, "td",
				tdBeskikbaarPos + 1);
		final String balans = Ontleder.maakSkoon(Ontleder.kryElementTeks(
				inhoud, tdBalansPos));
		// assertEquals("R 45,370.42+", );
		return new Balans(skakelOmVanRand(balans), skakelOmVanRand(beskikbaar),
				rekeningNaam, rekeningNommer, rekeningTipe);
	}

	public static Balans[] kryBalansStaat(String inhoud) {
		final int rekeningNaamTdPos = Ontleder.kryPosisie(inhoud, "td",
				"Account name");
		if (rekeningNaamTdPos == -1)
			return null;
		int trPos = rekeningNaamTdPos;
		Vector balanse = new Vector();
		while ((trPos = Ontleder.kryPosisie(inhoud, "tr", trPos)) != -1) {
			balanse.addElement(kryBalans(inhoud, trPos));
			++trPos;
		}
		Balans[] balansRooster = new Balans[balanse.size()];
		balanse.copyInto(balansRooster);
		// System.out.println("Aantal balanse:" + balansRooster.length);
		return balansRooster;
	}

	public static HttpResultaat tekenAan(Omgewing omgewing, String masjien, String koekie,
			String ccn, String csp, String pwd) throws IOException,
			InterruptedException {
		return tekenAan(omgewing, masjien, koekie, new VastePare(omgewing, new String[] { "ccn",
				ccn, "csp", csp, "login", "Login", "pwd", pwd }));
	}

	public static Balans[] skraapBalans(Omgewing omgewing, String rekeningNommer, String pin,
			String wagwoord) throws Exception {
		final HttpResultaat eersteHop = Skraap
				.hopNaMasjien(omgewing, "https://www.encrypt.standardbank.co.za");

		final String url = eersteHop.geeUrl();
		final String masjien = StringFunksies.kryMasjienVanUri(url);
		System.out.println("Masjien:" + masjien);
		String koekie = eersteHop.geeKoekie();
		System.out.println("Koekie:" + koekie);
		final HttpResultaat koekieResultaat = Skraap.kryKoekie(omgewing, masjien, koekie);

		koekie = koekieResultaat.geeKoekie();
		System.out.println("Koekie:" + koekie);
		final HttpResultaat aantekenResultaat = Skraap.tekenAan(omgewing, masjien,
				koekie, rekeningNommer, pin, wagwoord);
		final String aantekenInhoud = aantekenResultaat.geeInhoud();
		System.out.println(aantekenInhoud);

		final HttpResultaat balansResultaat = Skraap.kryBalans(omgewing, masjien,
				aantekenResultaat.geeKoekie());
		final String balansInhoud = balansResultaat.geeInhoud();

		return Skraap.kryBalansStaat(balansInhoud);
	}
}
