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

package net.derev.entiteit;

public class MotorVervaardiger extends Object {
	
	private final String naam;
	private final int id;
	
	public MotorVervaardiger(String naam, int id) {
		super();
		this.naam = naam;
		this.id = id;
	}

	public String geeNaam() {
		return naam;
	}

	public int geeId() {
		return id;
	}

	public int vergelyk(MotorVervaardiger regs) {
		return naam.compareTo(regs.geeNaam());
	}
}
