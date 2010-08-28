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

import java.util.Vector;

import net.derev.entiteit.Motor;
import net.derev.l10n.Globaal;
import net.derev.nuts.StringFunksies;
import net.derev.nuts.Vergelyking;

public class MotorModel {
	public static final Vector motors = new Vector();
	static {
		motors.addElement(new Motor(1, 2006, "3", MotorMaakModel.mazda));
		motors.addElement(new Motor(2, 2007, "3", MotorMaakModel.mazda));
		motors.addElement(new Motor(3, 2005, "6", MotorMaakModel.mazda));
		motors.addElement(new Motor(4, 2007, "6", MotorMaakModel.mazda));

		motors.addElement(new Motor(5, 2006, "206", MotorMaakModel.peugoet));
		motors.addElement(new Motor(6, 2007, "206", MotorMaakModel.peugoet));
		motors.addElement(new Motor(7, 2005, "208", MotorMaakModel.peugoet));
		motors.addElement(new Motor(8, 2007, "208", MotorMaakModel.peugoet));

		motors.addElement(new Motor(9, 2006, "A4 2.8", MotorMaakModel.audi));
		motors.addElement(new Motor(10, 2007, "A4 2.8", MotorMaakModel.audi));
		motors.addElement(new Motor(11, 2008, "A4 2.8 TDi", MotorMaakModel.audi));
		motors.addElement(new Motor(12, 2009, "A6 3.2", MotorMaakModel.audi));

		motors.addElement(new Motor(13, 2006, "120i", MotorMaakModel.bmw));
		motors.addElement(new Motor(14, 2007, "120i", MotorMaakModel.bmw));
		motors.addElement(new Motor(15, 2008, "120i", MotorMaakModel.bmw));
		motors.addElement(new Motor(16, 2009, "120i", MotorMaakModel.bmw));

		motors.addElement(new Motor(17, 2006, "323i", MotorMaakModel.bmw));
		motors.addElement(new Motor(18, 2007, "540i", MotorMaakModel.bmw));
		motors.addElement(new Motor(19, 2008, "M3", MotorMaakModel.bmw));
		motors.addElement(new Motor(20, 2009, "640i", MotorMaakModel.bmw));

		motors.addElement(new Motor(21, 2006, "Indigo GSX", MotorMaakModel.tata));
		motors.addElement(new Motor(22, 2007, "Indigo GSX", MotorMaakModel.tata));
		motors.addElement(new Motor(23, 2008, "Indigo GSX", MotorMaakModel.tata));
		motors.addElement(new Motor(24, 2009, "Indica 140", MotorMaakModel.tata));

		motors.addElement(new Motor(25, 2006, "WRX", MotorMaakModel.subaru));
		motors.addElement(new Motor(26, 2007, "WRX", MotorMaakModel.subaru));
		motors.addElement(new Motor(27, 2008, "WRX STi", MotorMaakModel.subaru));
		motors.addElement(new Motor(28, 2009, "WRX", MotorMaakModel.subaru));
	}
	
	private final int blaaiGrootte;
	private final Motor[] items;
	private final String[] perkName;
	private final String[] beskrywings;
	private final Sleutel[] ids;

	public MotorModel(int blaai, int blaaiGrootte, boolean vanKleinNaGroot, VasteSleutels vasteSleutels) {
		this.blaaiGrootte = blaaiGrootte;

		Vector gesif = new Vector();
		for (int motorIndeks = 0; motorIndeks < motors.size(); ++motorIndeks) {
			Motor motor = (Motor) motors.elementAt(motorIndeks);
			if (vasteSleutels == null) {
				gesif.addElement(motor);
				continue;
			} 
			boolean voegby = true;
			for (int filterIndeks = 0; filterIndeks < vasteSleutels.geeLengte(); ++filterIndeks) {
				String filterNaam = vasteSleutels.geeNaam(filterIndeks);
				Sleutel filterWaarde = vasteSleutels.geeWaarde(filterIndeks);
				if (filterNaam == null || filterWaarde == null)
					continue;
				if (!motor.isGeldig(filterNaam, filterWaarde)) {
					voegby = false;
					break;
				}
			}
			if (voegby)
				gesif.addElement(motor);
		}		
		
		items = new Motor[gesif.size()];
		for (int motorIndeks = 0; motorIndeks < items.length; ++motorIndeks) {
			items[motorIndeks] = (Motor) gesif.elementAt(motorIndeks);
		}
		StringFunksies.quicksort(items, 0, items.length - 1, new MotorVergelyking());
		perkName = new String[] { Globaal.JAAR, Globaal.MOTOR };

		if (!vanKleinNaGroot)
			StringFunksies.omkeer(items);

		blaai = Math.max(0, Math.min(blaai, geeMaksBlaaie() - 1));
		beskrywings = bouBeskrywings(blaai, blaaiGrootte, items);
		ids = bouIds(blaai, blaaiGrootte, items);
	}
	
	private class MotorVergelyking implements Vergelyking {

		public int vergelyk(Object links, Object regs) {
			if (links == null)
				return -1;
			if (regs == null)
				return 1;
			return ((Motor) links).vergelyk((Motor) regs);
		}
		
	}

	private static String[] bouBeskrywings(int blaai, int blaaiGrootte, Motor[] items) {
		Vector venster = new Vector();
		int beginPosisie = blaai * blaaiGrootte;
		for (int itemIndeks = beginPosisie; itemIndeks < items.length
				&& itemIndeks - beginPosisie < blaaiGrootte; ++itemIndeks) {
			venster.addElement(items[itemIndeks].geeBeskrywing());
		}

		String[] finaleLys = new String[venster.size()];
		venster.copyInto(finaleLys);
		return finaleLys;
	}

	private static Sleutel[] bouIds(int blaai, int blaaiGrootte, Motor[] items) {
		int beginPosisie = blaai * blaaiGrootte;
		Vector sleutels = new Vector();
		for (int itemIndeks = beginPosisie; itemIndeks < items.length
				&& itemIndeks - beginPosisie < blaaiGrootte; ++itemIndeks) {
			sleutels.addElement(new HeelgetalSleutel(items[itemIndeks].geeId()));
		}
		Sleutel[] sleutelWaardes = new Sleutel[sleutels.size()];
		sleutels.copyInto(sleutelWaardes);
		return sleutelWaardes;
	}

	public String[] kryBeskrywings() {
		return beskrywings;
	}

	public int geeMaksBlaaie() {
		return items.length / blaaiGrootte + (items.length % blaaiGrootte > 0 ? 1 : 0);
	}

	public String[] geeMoontlikeSiwwe() {
		return perkName;
	}

	public Sleutel[] kryIds() {
		return ids;
	}
}
