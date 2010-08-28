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

package net.derev.voorstelling.spesifikasie;

import net.derev.voorstelling.KontroleHouer;

public class BasieseSpesifikasie implements SkermSpesifikasie {
	private final KontroleHouer kontroleHouer;
	public BasieseSpesifikasie(KontroleHouer kontroleHouer, String naam, String beskrywing) throws NullPointerException, InstantiationException, IllegalAccessException {
		super();
		this.naam = naam;
		this.beskrywing = beskrywing;
		this.kontroleHouer = kontroleHouer;
	}

	private final String naam;
	private final String beskrywing;

	public String geeBeskrywingSleutel() {
		return beskrywing;
	}

	public void doen() throws Exception {
		kontroleHouer.kry(naam).wys(this);
	}
	
	public String geeNaam() {
		return naam;
	}

	public Object geeKenmerk(String naam) {
		return null;
	}

	
}
