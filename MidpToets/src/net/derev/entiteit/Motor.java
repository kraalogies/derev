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

import net.derev.model.Sleutel;
import net.derev.nuts.StringFunksies;

public class Motor {
	private final int id;
	private final int jaar;
	private final String spesifikasie;
	private final MotorVervaardiger maak;
	
	public MotorVervaardiger geeMaak() {
		return maak;
	}

	public Motor(int id, int jaar, String spesifikasie, MotorVervaardiger maak) {
		super();
		this.id = id;
		this.jaar = jaar;
		this.spesifikasie = spesifikasie;
		this.maak = maak;
	}

	public int geeId() {
		return id;
	}

	public int geeJaar() {
		return jaar;
	}

	public String geeSpesifikasie() {
		return spesifikasie;
	}

	public boolean isGeldig(String filterNaam, Sleutel filterWaarde) {
		if (filterWaarde == null)
			return false;
		if ("Jaar".equals(filterNaam))
			return filterWaarde.vergelyk(jaar);
		if ("Motor".equals(filterNaam))
			return filterWaarde.vergelyk(maak.geeId());
		return true;
	}
	
	public int vergelyk(Motor regs) {
		int vergelyking = maak.geeNaam().compareTo(regs.geeMaak().geeNaam());
		if (vergelyking != 0)
			return vergelyking;
		vergelyking = spesifikasie.compareTo(regs.geeSpesifikasie());
		if (vergelyking != 0)
			return vergelyking;
		if (jaar < regs.geeJaar())
			return -1;
		if (jaar > regs.geeJaar())
			return 1;
			
		return 0;
	}

	public String geeBeskrywing() {
		return StringFunksies.formatteer(
				"{0} {1}, {2}", 
				new String[]{
						geeMaak().geeNaam(),
						geeSpesifikasie(),
						String.valueOf(geeJaar())
						});
	}
}
