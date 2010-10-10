package platform.nuts;

import java.util.Vector;

public class StringFunksies implements Vergelyking {
	private static Vergelyking stringVergelyking = new StringFunksies();
	public static void quicksort(Object[] items, int begin, int einde, Vergelyking vergelyking) {
		if (begin >= einde)
			return;
		Object spilpunt = items[einde];
		if (spilpunt == null) { //null word heel voor ingesit
			items[einde] = items[begin];
			items[begin] = spilpunt;
			quicksort(items, begin + 1, einde, vergelyking);
			return;
		}

		int gatPosisie = einde;
		int vatPosisie = begin;
		while (vatPosisie < gatPosisie) {
			Object vat = items[vatPosisie];
			if (vat != null && vergelyking.vergelyk(spilpunt, vat) < 0) {
				items[gatPosisie--] = vat;
				items[vatPosisie] = items[gatPosisie];
			} else
				++vatPosisie;
		}
		items[gatPosisie] = spilpunt;
		quicksort(items, begin, gatPosisie - 1, vergelyking);
		quicksort(items, gatPosisie + 1, einde, vergelyking);
	}

	public static void sorteer(String[] stringe) {
		if (stringe == null || stringe.length <= 1)
			return;
		quicksort(stringe, 0, stringe.length - 1, stringVergelyking);
	}

	public static void omkeer(Object[] stringe) {
		if (stringe == null || stringe.length <= 1)
			return;
		for (int i = 0; i < stringe.length /2; ++i) {
			Object buffer = stringe[i];
			stringe[i] = stringe[stringe.length - 1 - i];
			stringe[stringe.length - 1 - i] = buffer;
		}
	}

	private static int kryBeginPosisie(String s, char skeier, int plek) {
		int beginPosisie = 0;
		for (int eerstePlek = 0; eerstePlek < plek; ++eerstePlek) {
			beginPosisie = s.indexOf(skeier, beginPosisie);
			if (beginPosisie < 0)
				return beginPosisie;
			++beginPosisie;
		}
		return beginPosisie;
	}

	public static String stert(String s, char skeier, int plek) {
		if (s == null)
			return null;
		int beginPosisie = kryBeginPosisie(s, skeier, plek);
		if (beginPosisie < 0)
			return null;
		return s.substring(beginPosisie);
	}

	public static String veld(String s, char skeier, int plek) {
		if (s == null)
			return null;
		int beginPosisie = kryBeginPosisie(s, skeier, plek);
		if (beginPosisie < 0)
			return null;
		int endPosisie = s.indexOf(skeier, beginPosisie);
		if (endPosisie < 0)
			endPosisie = s.length();
		return s.substring(beginPosisie, endPosisie);
	}

	public static String[] verdeel(String s, String skeier) {
		if (s == null)
			return null;
		Vector stringDele = new Vector();

		int huidige;
		int vorige = 0;

		while ((huidige = s.indexOf(skeier, vorige)) >= 0) {
			stringDele.addElement(s.substring(vorige, huidige));
			vorige = huidige + skeier.length();
		}
		stringDele.addElement(s.substring(vorige));

		String[] verdeeldeStringe = new String[stringDele.size()];
		stringDele.copyInto(verdeeldeStringe);

		return verdeeldeStringe;
	}

	public static String formatteer(String formaat, String[] parameters) {
		if (formaat == null || formaat.length() == 0 || parameters == null || parameters.length == 0)
			return formaat;

		StringBuffer buffer = new StringBuffer(formaat);
		for (int i = 0; i < parameters.length; i++) {
			String parameterPasString = "{" + i + "}";
			String parameter = parameters[i];
			int parameterPasPosisie = formaat.indexOf(parameterPasString);
			int bufferPosisieVerskil = 0;
			while (parameterPasPosisie >= 0) {
				buffer.delete(parameterPasPosisie + bufferPosisieVerskil, parameterPasPosisie + bufferPosisieVerskil + parameterPasString.length());
				buffer.insert(parameterPasPosisie + bufferPosisieVerskil, parameter);
				bufferPosisieVerskil += parameter.length() - parameterPasString.length();
				parameterPasPosisie = formaat.indexOf(parameterPasString, parameterPasPosisie + parameterPasString.length());
			}

			formaat = buffer.toString();
		}
		return formaat;
	}

	public int vergelyk(Object links, Object regs) {
		if (links == null)
			return -1;
		if (regs == null)
			return 1;
		return ((String) links).compareTo((String) regs);
	}

}
