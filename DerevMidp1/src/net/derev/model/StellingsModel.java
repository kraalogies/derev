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

package net.derev.model;

public class StellingsModel {
	private final String[] landKodes = new String[] { "US", "UK", "ZA" };
	private final String[] landAfkortings = new String[] { "USA", "UK", "RSA" };
	private final String[] taalKodes = new String[] { "en", "af" };
	private final String[] taalName = new String[] { "English", "Afrikaans" };
	
	public String[] geeLandAfkortings() {
		return landAfkortings;
	}

	public String[] geeTaalName() {
		return taalName;
	}

	public String geeLandKode(int landIndeks) {
		return landKodes[landIndeks];
	}

	public String geeTaalKode(int taalIndeks) {
		return taalKodes[taalIndeks];
	}

	public int geeLandIndeks(String landKode) {
		if (landKode == null)
			return -1;
		for (int landIndeks = 0; landIndeks < landKodes.length; ++landIndeks) {
			if (landKode.equals(landKodes[landIndeks]))
				return landIndeks;
		}
		return -1;
	}

	public int geeTaalIndeks(String taalKode) {
		if (taalKode == null)
			return -1;
		for (int taalIndeks = 0; taalIndeks < taalKodes.length; ++ taalIndeks) {
			if (taalKode.equals(taalKodes[taalIndeks]))
				return taalIndeks;
		}
		return -1;
	}
}
