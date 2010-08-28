// Copyright (C) 2009 Hans Malherbe
//
// This file is part of Derev.
//
// Derev is free software: you can redistribute it and/or modify
// it under the terms of the GNU General Public License as published by
// the Free Software Foundation, either version 3 of the License, or
// (at your option) any later version.
//
// Derev is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// GNU General Public License for more details.
//
// You should have received a copy of the GNU General Public License
// along with Derev.  If not, see <http://www.gnu.org/licenses/>.

package net.derev.nuts;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Vector;

import platform.Omgewing;

import net.derev.infrastruktuur.VastePare;

public class StringFunksies implements Vergelyking {
	public static String maakString(char kar, int aantal) {
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < aantal; ++i) {
			buffer.append(kar);
		}
		return buffer.toString();
	}

	public static void quicksort(Object[] items, int begin, int einde,
			Vergelyking vergelyking) {
		if (begin >= einde)
			return;
		Object spilpunt = items[einde];
		if (spilpunt == null) { // null word heel voor ingesit
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
		quicksort(stringe, 0, stringe.length - 1, new StringFunksies());
	}

	public static void omkeer(Object[] stringe) {
		if (stringe == null || stringe.length <= 1)
			return;
		for (int i = 0; i < stringe.length / 2; ++i) {
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
		if (formaat == null || formaat.length() == 0 || parameters == null
				|| parameters.length == 0)
			return formaat;

		StringBuffer buffer = new StringBuffer(formaat);
		for (int i = 0; i < parameters.length; i++) {
			String parameterPasString = "{" + i + "}";
			String parameter = parameters[i];
			int parameterPasPosisie = formaat.indexOf(parameterPasString);
			int bufferPosisieVerskil = 0;
			while (parameterPasPosisie >= 0) {
				buffer.delete(parameterPasPosisie + bufferPosisieVerskil,
						parameterPasPosisie + bufferPosisieVerskil
								+ parameterPasString.length());
				buffer.insert(parameterPasPosisie + bufferPosisieVerskil,
						parameter);
				bufferPosisieVerskil += parameter.length()
						- parameterPasString.length();
				parameterPasPosisie = formaat.indexOf(parameterPasString,
						parameterPasPosisie + parameterPasString.length());
			}

			formaat = buffer.toString();
		}
		return formaat;
	}

	public int vergelyk(Object links, Object regs) {
		if (links == regs)
			return 0;
		if (links == null)
			return -1;
		if (regs == null)
			return 1;
		return ((String) links).compareTo((String) regs);
	}

	public static String formatteer(String formaat, String par1, String par2) {
		return formatteer(formaat, new String[] { par1, par2 });
	}

	public static String formatteer(String formaat, String parameter) {
		return formatteer(formaat, new String[] { parameter });
	}

	public static String formatteer(String formaat, VastePare parameters) {
		if (formaat == null || formaat.length() == 0 || parameters == null)
			return formaat;

		StringBuffer buffer = new StringBuffer(formaat);
		for (int i = 0; i < parameters.geeLengte(); i++) {
			String parameterPasString = "{" + parameters.geeSleutel(i) + "}";
			Object parameterWaarde = parameters.geeWaarde(i);
			String parameter = parameterWaarde == null ? "" : parameterWaarde.toString();
			
			int parameterPasPosisie = formaat.indexOf(parameterPasString);
			int bufferPosisieVerskil = 0;
			while (parameterPasPosisie >= 0) {
				buffer.delete(parameterPasPosisie + bufferPosisieVerskil,
						parameterPasPosisie + bufferPosisieVerskil
								+ parameterPasString.length());
				buffer.insert(parameterPasPosisie + bufferPosisieVerskil,
						parameter);
				bufferPosisieVerskil += parameter.length()
						- parameterPasString.length();
				parameterPasPosisie = formaat.indexOf(parameterPasString,
						parameterPasPosisie + parameterPasString.length());
			}

			formaat = buffer.toString();
		}
		return formaat;
	}

	public static String kryMasjienVanUri(String url) {
		return veld(url, '/', 2);
	}

	
	public static String urlEnkodeer(String s) throws IOException {
		return encodeTwitteresce(s);
	}

	private static String encodeTwitteresce(String s) throws IOException {
		ByteArrayOutputStream bOut = new ByteArrayOutputStream();
		DataOutputStream dOut = new DataOutputStream(bOut);
		StringBuffer ret = new StringBuffer(); //return value
		
		dOut.writeUTF(s);
		
		ByteArrayInputStream bIn = new ByteArrayInputStream(bOut.toByteArray());
		
		bIn.read();
		bIn.read();
		
		int c = bIn.read();
		while (c >= 0) {
			if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || (c >= '0' && c <= '9') || c == '.' || c == '-' || c == '*' || c == '_') 
			{
				ret.append((char)c);
			}
			else if (c == ' ')
			{
				ret.append('+');
			}
			else 
			{
				if (c < 128) {
					appendHexTwitteresce(c, ret);
				} else if (c < 224) {
					appendHexTwitteresce(c, ret);
					appendHexTwitteresce(bIn.read(), ret);
				} else if (c < 240) {
					appendHexTwitteresce(c,ret);
					appendHexTwitteresce(bIn.read(), ret);
					appendHexTwitteresce(bIn.read(), ret);
				}
			
			}
			c = bIn.read();
			
		}
		return ret.toString();
	}
	
	private static void appendHexTwitteresce(int arg0, StringBuffer buff){
		buff.append('%');
		if (arg0 < 16) {
			buff.append('0');
		}
		buff.append(Integer.toHexString(arg0));
	}

	public static String kryMediaParameter(Omgewing omgewing, String mediaTipe, String parameterNaam) {
		String parameterTeks = stert(mediaTipe, ';', 1);
		if (parameterTeks == null)
			return null;
		String[] parameters = verdeel(parameterTeks.trim(), ";");
		if (parameters == null)
			return null;
		for (int parPos = 0; parPos < parameters.length; ++parPos) {
			String parameter = parameters[parPos];
			String naam = veld(parameter, '=', 0);
			if (omgewing.vergelykKasOnsensitief(parameterNaam, naam))
				return veld(parameter, '=', 1);
		}
		return null;
	}

	public static String kryMediaTipe(String mediaTipe) {
		// rfc2616 3.7: HTTP Content-Type en Accept 
		return veld(mediaTipe, '/', 0);
	}
	
	public static String kryMediaSubtipe(String mediaTipe) {
		String stert = veld(mediaTipe, '/', 1);
		if (stert == null)
			return null;
		return veld(stert, ';', 0);
	}

	public static boolean vergelykStringe(String s1, String s2, int pos1, int pos2, int len) {
		if (s1 == null || s2 == null || s1.length() < pos1 + len || s2.length() < pos2 + len)
			return false;
		for (int i = 0; i < len; ++i) {
			if (s1.charAt(i + pos1) != s2.charAt(i + pos2))
				return false;
		}
		return true;
	}
	public static int lastIndexOf(String dokkie, String pas, int beginPosisie) {
		if (dokkie == null || pas == null || pas.length() == 0 || dokkie.length() < pas.length())
			return -1;
		if (beginPosisie == -1 || beginPosisie >= dokkie.length())
			beginPosisie = dokkie.length() - 1;
		if (vergelykStringe(dokkie, pas, beginPosisie, 0, pas.length()))
			return beginPosisie;
		char eerstePas = pas.charAt(0);
		while ((beginPosisie = dokkie.lastIndexOf(eerstePas, beginPosisie - 1)) >= 0) {
			if (vergelykStringe(dokkie, pas, beginPosisie, 0, pas.length()))
				return beginPosisie;
		}
		return -1;
	}

	public static String vervang(final String inhoud, final String pasString, final String vervang) {
		if (inhoud == null || pasString == null || vervang == null)
			return null;
		StringBuffer buffer = new StringBuffer(inhoud);
		int soekPos = 0;
		int bufferAanpassing = vervang.length() - pasString.length();
		int aantalVervangings = 0;
		while ((soekPos = inhoud.indexOf(pasString, soekPos)) != -1) {
			int begin = soekPos + bufferAanpassing*aantalVervangings;
			buffer.delete(begin, begin + pasString.length());
			buffer.insert(begin, vervang);
			++aantalVervangings;
			soekPos += pasString.length();
		}
		return buffer.toString();
	}

	public static int kryPosisie(final String teks, final String toetsKarakters, int beginPosisie) {
		if (teks == null || toetsKarakters == null || beginPosisie < 0)
			return -1;
		for (int pos = beginPosisie; pos < teks.length(); ++pos) 
			for (int toetsPos = 0; toetsPos < toetsKarakters.length(); ++toetsPos) 
				if (teks.charAt(pos) == toetsKarakters.charAt(toetsPos))
					return pos;
		return -1;
	}
	
	public static String verwyderKarakters(final String teks, final String verwyderKarakters) {
		if (teks == null || verwyderKarakters == null)
			return teks;
		StringBuffer buffer = new StringBuffer(teks);
		int vindPos = 0;
		int aantalUitgevee = 0;
		while ((vindPos = kryPosisie(teks, verwyderKarakters, vindPos)) != -1) {
			buffer.deleteCharAt(vindPos - aantalUitgevee);
			++vindPos;
			++aantalUitgevee;
		}
		return aantalUitgevee == 0 ? teks : buffer.toString();
	}

	public static String lvul(String teks, char kar, int wydte) {
		if (teks == null || teks.length() >= wydte)
			return teks;
		return StringFunksies.maakString(kar, wydte - teks.length()) + teks;
	}
}
