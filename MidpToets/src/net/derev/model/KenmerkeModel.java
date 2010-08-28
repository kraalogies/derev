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

import java.io.IOException;

import net.derev.infrastruktuur.DataLeser;
import net.derev.infrastruktuur.Kultuur;


public class KenmerkeModel {
	private final Kultuur kultuur;
	private DataLeser leser;
	public KenmerkeModel(DataLeser leser, Kultuur kultuur) {
		super();
		this.leser = leser;
		this.kultuur = kultuur;
	}

	public String geeLokaliteit() {
		return kultuur.geeTaalKode() + "-" + kultuur.geeLandKode() + " (" + System.getProperty("microedition.locale") + ")";
	}
	
	public String leesLokaleJar() throws IOException {
		return leser.leesLokaleString("jar://lokaal.txt", kultuur.geeTaalKode(), kultuur.geeLandKode());
	}

	public String leesJar() {
		try {
			return leser.leesString("jar://toets.txt");
		} catch (IOException e) {
			return "Fout met die lees van toets.txt - " + e.toString();
		}
	}
}
