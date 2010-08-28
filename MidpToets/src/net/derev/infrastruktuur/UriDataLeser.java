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

public class UriDataLeser implements DataLeser {
	private final DataLeser jarLeser;
	
	public UriDataLeser()  {
		super();
		this.jarLeser = new JarLeser();
	}

	private DataLeser geeLeser(String uri) throws IOException {
		if (uri.startsWith("jar"))
			return jarLeser;
		throw new IOException();
	}
	
	public Image geeBeeld(String uri) {
		// TODO Auto-generated method stub
		return null;
	}

	public String leesString(String uri) throws IOException {
		return geeLeser(uri).leesString(uri);
	}

	public String leesLokaleString(String uri, String taalKode, String landKode) throws IOException {
		return geeLeser(uri).leesLokaleString(uri, taalKode, landKode);
	}

}
