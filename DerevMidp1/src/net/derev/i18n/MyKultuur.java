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

import net.derev.infrastruktuur.Kultuur;
import net.derev.nuts.StringFunksies;


public class MyKultuur implements Kultuur {
	private String taalKode;
	private final Fraselys fraseLys;
	private String landKode;
	
	public MyKultuur() throws IOException {
		this(null, null, null);
	}
	
	public MyKultuur(Fraselys fraselys, String taalKode, String landKode) throws IOException {
		super();
		if (taalKode == null)
			this.taalKode = kryStelselTaalkode();
		else
			this.taalKode = taalKode;
		if (landKode == null)
			this.landKode = kryStelselLandkode();
		else
			this.landKode = landKode;
		this.fraseLys = fraselys; 
	}

	public String geeTaalKode() {
		return taalKode;
	}

	public String kryFrase(String sleutelWoord) {
		if (fraseLys == null || sleutelWoord == null)
			return null;
		return fraseLys.gee(sleutelWoord);
	}
	
	public static String kryStelselTaalkode() {
		return StringFunksies.veld(System.getProperty("microedition.locale"), '-', 0);
	}

	public static String kryStelselLandkode() {
		return StringFunksies.veld(System.getProperty("microedition.locale"), '-', 1);
	}

	public String geeLandKode() {
		return landKode;
	}
}
