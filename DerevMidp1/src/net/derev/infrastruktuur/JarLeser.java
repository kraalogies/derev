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

package net.derev.infrastruktuur;


import java.io.IOException;

import javax.microedition.lcdui.Image;

import net.derev.nuts.Stroom;

public class JarLeser implements DataLeser {

	public Image geeBeeld(String uri) {
		// TODO Auto-generated method stub
		return null;
	}

	private String leesVanJar(String pad) throws IOException {
		byte[] rouData = Stroom.leesAlles(this.getClass().getResourceAsStream(
				pad));
		return new String(rouData, "UTF-8");
	}

	public String leesString(String uri) throws IOException {
		return leesVanJar(uri.substring(5));
	}

	public String leesLokaleString(final String uri, String taalKode, String landKode)
			throws IOException {
		if (taalKode == null)
			return leesString(uri);
		try {
			String pad = "/" + taalKode;
			if (landKode != null)
				try {
					return leesVanJar(pad + "/" + landKode + uri.substring(5));
				} catch (IOException e) { //Probeer eers die land kode lees
				}
			return leesVanJar(pad + uri.substring(5));
		} catch (IOException e) {
			try {
				return leesString(uri);
			} catch (IOException e1) {
				throw new IOException(StelselFoute.kanNieJarLees(uri));
			}
		}
	}

}
