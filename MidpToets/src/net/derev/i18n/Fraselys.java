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

package net.derev.i18n;

import java.io.IOException;
import java.util.Hashtable;

import net.derev.nuts.StringFunksies;

public class Fraselys {
	private final Hashtable frases = new Hashtable();

	public Fraselys(String[] lyne, String[] verstekLyne) throws IOException {
		bouFraseKaart(frases, verstekLyne);
		bouFraseKaart(frases, lyne);
	}
	
	public static void bouFraseKaart(Hashtable frases, String[] lyne) throws IOException {
		if (lyne == null)
			return;
		for (int lynTeller = 0; lynTeller < lyne.length; ++lynTeller) {
			String lyn = lyne[lynTeller];
			if (lyn == null)
				continue;
			String sleutelWoord = StringFunksies.veld(lyn, '=', 0);
			if (sleutelWoord == null)
				continue;
			String frase = StringFunksies.stert(
					lyn, '=', 1);
			if (frase == null)
				continue;
			frases.put(sleutelWoord, frase);
		}
	}

	
	public String gee(String sleutelWoord) {
		String antwoord = (String) frases.get(sleutelWoord);
		if (antwoord == null)
			return sleutelWoord;
		return antwoord;
	}
}
