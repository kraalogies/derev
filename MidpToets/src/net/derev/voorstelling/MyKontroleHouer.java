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

package net.derev.voorstelling;

import java.util.Hashtable;

public class MyKontroleHouer  {
	private final Hashtable klasse = new Hashtable();
	private final Hashtable enkelinge = new Hashtable();
	private Class verstekKontroleKlas;

	private Object kryKontroleImpl(String naam, Class koppelvlak) throws Exception {
		Object kontrole = enkelinge.get(naam);
		if (kontrole != null && koppelvlak.isInstance(kontrole)) 
			return kontrole;

		Class klas = (Class) klasse.get(naam);
		if (klas != null) {
			kontrole = klas.newInstance();
			if (kontrole != null && koppelvlak.isInstance(kontrole)) 
				return kontrole;
		}
		if (verstekKontroleKlas != null) {
			return verstekKontroleKlas.newInstance();
		}
		throw new InstantiationException(this.getClass().getName() + ".kryKontrole");
	}

	public void registreerKontroleKlas(String naam, Class kontroleKlas) {
		klasse.put(naam, kontroleKlas);
	}

//	public Kontrole kry(String naam) throws Exception {
//		return (Kontrole) kryKontroleImpl(naam, Kontrole.class);
//	}

	public void registreerKlas(String naam, Class kontroleKlas) {
		registreerKontroleKlas(naam, kontroleKlas);
	}

	public void registreerEnkelingLys(String naam, LysKontrole kontrole) {
		enkelinge.put(naam, kontrole);
	}

	public void registreerEnkelingKontrole(String naam, Kontrole kontrole) {
		enkelinge.put(naam, kontrole);
	}

	public void registreerKlas(String naam, Class kontroleKlas,
			boolean verstekKontrole) {
		registreerKontroleKlas(naam, kontroleKlas, verstekKontrole);
	}

	public void registreerKontroleKlas(String naam, Class kontroleKlas,
			boolean verstekKontrole) {
		registreerKontroleKlas(naam, kontroleKlas);
		if (!verstekKontrole)
			return;
		if (verstekKontroleKlas != null)
			throw new IllegalArgumentException("Duplikaat verstek kontrole " + naam);
		verstekKontroleKlas = kontroleKlas;
	}

//	public LysKontrole kryLys(String naam) throws Exception {
//		return (LysKontrole) kryKontroleImpl(naam, LysKontrole.class);
//	}
//
//	public DetailKontrole geeDetail(String naam) throws Exception {
//		return (DetailKontrole) kryKontroleImpl(naam, DetailKontrole.class);
//	}
//
//	public NuweRekordKontrole geeNuwe(String naam) throws Exception {
//		return (NuweRekordKontrole) kryKontroleImpl(naam, NuweRekordKontrole.class);
//	}
//
//	public  StoorKontrole geeStoor(String naam) throws Exception {
//		return (StoorKontrole) kryKontroleImpl(naam, StoorKontrole.class);
//	}
}
