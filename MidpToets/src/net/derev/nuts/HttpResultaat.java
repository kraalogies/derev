package net.derev.nuts;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import platform.Omgewing;

import net.derev.infrastruktuur.VasteStringe;

public class HttpResultaat {
	public static final String HOOF_NAVRAAG_LOCATION = "location";
	public static final String HOOF_NAVRAAG_UA = "user-agent";
	public static final String HOOF_NAVRAAG_ACCEPT = "accept";
	public static final String HOOF_NAVRAAG_ACCEPT_LANG = "accept-language";
	public static final String HOOF_NAVRAAG_ACCEPT_CHAR = "accept-charset";
	public static final String HOOF_RESPONS_SET_COOKIE = "set-cookie";
	public static final String HOOF_NAVRAAG_CONTENT_TYPE = "content-type";
	public static final String HOOF_NAVRAAG_CONTENT_LENGTH = "content-length";
	public static final String HOOF_NAVRAAG_COOKIE = "cookie";
	private static final String HOOF_RESPONS_CONTENT_ENCODING = "Content-Encoding";
	public static final String HOOF_NAVRAAG_REFERER = "Referer";
	public static final String HOOF_NAVRAAG_ACCEPT_ENC = "Accept-Encoding";

	private final byte[] antwoord;
	private final VasteStringe hofies;
	private final String url;
	private final String koekie;
	private final Omgewing omgewing;
	public HttpResultaat(Omgewing omgewing, byte[] antwoord, VasteStringe hofies, String url, String koekie) {
		super();
		this.omgewing = omgewing;
		this.antwoord = antwoord;
		this.hofies = hofies;
		this.url = url;
		this.koekie = koekie;
	}
	public int geeDataLengte() {
		if (antwoord == null)
			return 0;
		return antwoord.length;
	}
	public String soekHofie(String hofie) {
		return hofies.soekWaarde(hofie);
	}
	public String geeUrl() {
		return url;
	}
	private static String geeTeksInhoud(Omgewing omgewing, String karakterVersameling, byte[] antwoord) throws UnsupportedEncodingException {
		if (karakterVersameling == null || omgewing.vergelykKasOnsensitief(karakterVersameling, "ISO-8859-1"))
			return new String(antwoord);
		if (omgewing.vergelykKasOnsensitief(karakterVersameling, "UTF-8"))
			return new String(antwoord, karakterVersameling);
		throw new IllegalArgumentException("Charset:" + karakterVersameling);
	}
	private byte[] dekodeerAntwoord() throws IOException {
		String enkodering = hofies.soekWaarde(HOOF_RESPONS_CONTENT_ENCODING);
		if (enkodering == null || enkodering.length() == 0)
			return antwoord;
		if (omgewing.vergelykKasOnsensitief(enkodering, "gzip"))
			return Gzip.inflate(antwoord);
		throw new UnsupportedEncodingException(enkodering);
	}
	public String geeInhoud() throws IOException {
		if (antwoord == null)
			return null;
		String media = hofies.soekWaarde(HOOF_NAVRAAG_CONTENT_TYPE);
		if (media == null)
			return geeTeksInhoud(omgewing, null, dekodeerAntwoord());
		String mediaTipe = StringFunksies.kryMediaTipe(media);
		if (omgewing.vergelykKasOnsensitief(mediaTipe, "text"))
			return geeTeksInhoud(omgewing, StringFunksies.kryMediaParameter(omgewing, media, "charset"), dekodeerAntwoord());
		throw new IllegalArgumentException(HOOF_NAVRAAG_CONTENT_TYPE+':'+mediaTipe);
	}
	public String geeKoekie() {
		String nuweKoekie = StringFunksies.veld(soekHofie(HOOF_RESPONS_SET_COOKIE), ';', 0);
		if (nuweKoekie != null)
			return nuweKoekie;
		return koekie;
	}
}
