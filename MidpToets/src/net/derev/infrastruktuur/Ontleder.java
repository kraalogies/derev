package net.derev.infrastruktuur;

import net.derev.nuts.StringFunksies;

public class Ontleder {
	public static int kryPosisie(String dokkie, String merker) {
		return kryPosisie(dokkie, merker, 0);
	}

	public static int kryPosisie(String dokkie, String merker, int beginPosisie) {
		if (dokkie == null || merker == null || merker.length() == 0)
			return -1;
		String pas = "<" + merker;
		int posisie = dokkie.indexOf(pas, beginPosisie);
		while (true) {
			if (posisie < 0)
				return -1;
			int posisieNaMerker = posisie + pas.length();
			if (posisieNaMerker >= dokkie.length())
				return -1;
			if (dokkie.charAt(posisieNaMerker) == '>'
					|| dokkie.charAt(posisieNaMerker) == ' ')
				return posisie;
			posisie = dokkie.indexOf(dokkie, posisieNaMerker);
		}
	}

	public static int kryPosisie(String dokkie, String merker,
			String merkerInhoud) {
		if (merkerInhoud == null)
			return -1;
		int pos = kryPosisie(dokkie, merker);
		if (pos < 0)
			return pos;
		while (pos < dokkie.length()) {
			if (merkerInhoud.equals(kryMerkerInhoud(dokkie, pos, merker)))
				return pos;
			pos = kryPosisie(dokkie, merker, pos + 1 + merker.length());
			if (pos < 0)
				return pos;
		}
		return -1;
	}

	private static String kryMerkerInhoud(String dokkie, int pos, String merker) {
		if (dokkie == null || dokkie.length() <= pos
				|| dokkie.charAt(pos) != '<' || merker == null
				|| merker.length() == 0)
			return null;

		int begin = dokkie.indexOf('>', pos) + 1;
		String stop = "</" + merker + ">";
		int einde = dokkie.indexOf(stop, begin);
		if (einde == -1)
			return null;
		return dokkie.substring(begin, einde);
	}

	public static int kryPosisieTerugwaarts(String dokkie, String merker,
			int beginPosisie) {
		if (dokkie == null || merker == null || merker.length() == 0)
			return -1;
		String pas = "<" + merker;
		int posisie = StringFunksies.lastIndexOf(dokkie, pas, beginPosisie);
		while (true) {
			if (posisie < 0)
				return -1;
			int posisieNaMerker = posisie + pas.length();
			if (posisieNaMerker >= dokkie.length())
				return -1;
			if (dokkie.charAt(posisieNaMerker) == '>'
					|| dokkie.charAt(posisieNaMerker) == ' ')
				return posisie;
			posisie = StringFunksies.lastIndexOf(dokkie, pas, posisieNaMerker);
		}
	}

	private static String kryMerker(String dokkie, int posisie) {
		// Kry die merker naam by die posisie wat begin met <
		if (dokkie.charAt(posisie) != '<')
			return null;
		int elementEinde = dokkie.indexOf('/', posisie);
		int merkerEinde = dokkie.indexOf('>', posisie);
		int spasie = dokkie.indexOf(' ', posisie);
		
		int einde = 0;
		if (elementEinde != -1)
			einde = elementEinde;
		if (merkerEinde != -1)
			einde = Math.min(einde, merkerEinde);
		if (spasie != -1)
			einde = Math.min(einde, spasie);

		if (einde == 0 || einde == elementEinde)
			return null;
		return dokkie.substring(posisie + 1, einde);
	}
	

	public static String kryElementTeks(String dokkie, int posisie) {
		if (dokkie == null || dokkie.length() <= posisie + 1)
			return null;
		String merker = kryMerker(dokkie, posisie);
		if (merker == null)
			return null;

		String endTeks = "</" + merker + ">";
		int soekVanaf = posisie + merker.length() + 2;
		String merkerTeks = "<" + merker;
		while (true) {
			int einde = dokkie.indexOf(endTeks, soekVanaf);
			if (einde == -1)
				return null;

			int aantalInterneMerkers = 0;
			int interneMerker = dokkie.indexOf(merkerTeks, soekVanaf);
			while (interneMerker > 0 && interneMerker < einde) {
				++aantalInterneMerkers;
				interneMerker = dokkie.indexOf(merkerTeks, interneMerker + merkerTeks.length());
			}
			
			if (aantalInterneMerkers == 0) {
				int merkerEinde = dokkie.indexOf('>', posisie);
				if (merkerEinde == -1 || merkerEinde >= einde)
					return null;
				return dokkie.substring(merkerEinde + 1, einde);
			}

			--aantalInterneMerkers;
			soekVanaf = einde + endTeks.length();
			while (aantalInterneMerkers > 0) {
				--aantalInterneMerkers;
				soekVanaf = dokkie.indexOf(endTeks, soekVanaf);
				if (soekVanaf == -1)
					return null;
				soekVanaf += endTeks.length();
			}
		}
	}

	public static String maakSkoon(String vuil) {
		vuil = StringFunksies.vervang(vuil, "&nbsp;", "");
		vuil = StringFunksies.vervang(vuil, "\n", "");
		vuil = StringFunksies.vervang(vuil, "\r", "");
		return vuil.trim();
	}
}
