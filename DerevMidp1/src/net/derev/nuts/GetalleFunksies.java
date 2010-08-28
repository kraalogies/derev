package net.derev.nuts;

public class GetalleFunksies {
	public static int totDieMag(int syfer, int totDieMag) {
		if (totDieMag == 0)
			return 1;
		if (totDieMag < 0)
			throw new IllegalArgumentException();
		int antwoord = syfer;
		while (totDieMag > 1) {
			antwoord *= syfer;
			--totDieMag;
		}
		return antwoord;
	}
	public static int kryAantalKarakters(int toets) {
		if (toets == 0)
			return 1;
		int aantalKarakters = 1;
		if (toets < 0) {
			if (toets == Integer.MIN_VALUE)
				++toets; // Verhoed oorvloei;
			toets *= -1;
			++aantalKarakters;
		}
		int grens = 1;
		for (; toets / 10 > grens - 1; ++aantalKarakters) 
			grens *= 10;
		return aantalKarakters;
	}

}
