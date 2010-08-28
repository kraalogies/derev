package net.derev.model;

import java.util.Vector;

import net.derev.entiteit.MotorVervaardiger;
import net.derev.nuts.StringFunksies;
import net.derev.nuts.Vergelyking;

public class MotorMaakModel {
	private static final Vector make = new Vector();
	public static final MotorVervaardiger mazda = new MotorVervaardiger("Mazda", 1);
	public static final MotorVervaardiger chev = new MotorVervaardiger("Chevrolet", 2);
	public static final MotorVervaardiger peugoet = new MotorVervaardiger("Peugoet", 3);
	public static final MotorVervaardiger renault = new MotorVervaardiger("Renault", 4);
	public static final MotorVervaardiger citroen = new MotorVervaardiger("Citroen", 5); 
	public static final MotorVervaardiger audi = new MotorVervaardiger("Audi", 6);
	public static final MotorVervaardiger bmw = new MotorVervaardiger("BMW", 7);
	public static final MotorVervaardiger chrysler = new MotorVervaardiger("Chrysler", 8);
	public static final MotorVervaardiger ford = new MotorVervaardiger("Ford", 9);
	public static final MotorVervaardiger mitsi = new MotorVervaardiger("Mitsibushi", 10);
	public static final MotorVervaardiger subaru = new MotorVervaardiger("Subaru", 11);
	public static final MotorVervaardiger tata = new MotorVervaardiger("Tata", 12);
	public static final MotorVervaardiger toyota = new MotorVervaardiger("Toyota", 13);
	
	static {
		make.addElement(mazda);
		make.addElement(chev);
		make.addElement(peugoet);
		make.addElement(renault);
		make.addElement(citroen);
		make.addElement(audi);
		make.addElement(bmw);
		make.addElement(chrysler);
		make.addElement(ford);
		make.addElement(mitsi);
		make.addElement(subaru);
		make.addElement(tata);
		make.addElement(toyota);
	}

	private final MotorVervaardiger[] items;
	private final String[] beskrywings;
	private final Sleutel[] ids;
	private int blaaiGrootte;
	
	public MotorMaakModel(int blaai, int blaaiGrootte, boolean vanKleinNaGroot) {
		super();
		this.blaaiGrootte = blaaiGrootte;
		items = new MotorVervaardiger[make.size()];
		bouMake();
		StringFunksies.quicksort(items, 0, items.length - 1, new MaakVergelyking());
		if (!vanKleinNaGroot)
			StringFunksies.omkeer(items);
		beskrywings = bouBeskrywings(blaai, blaaiGrootte, items);
		ids = bouIds(blaai, blaaiGrootte, items);
	}

	private static Sleutel[] bouIds(int blaai, int blaaiGrootte, MotorVervaardiger[] items) {
		Vector sleutels = new Vector();
		int beginPosisie = blaai * blaaiGrootte;
		for (int itemIndeks = beginPosisie; itemIndeks < items.length
				&& itemIndeks - beginPosisie < blaaiGrootte; ++itemIndeks) {
			sleutels.addElement(new HeelgetalSleutel(items[itemIndeks].geeId()));
		}
		Sleutel[] sleutelWaardes = new Sleutel[sleutels.size()];
		sleutels.copyInto(sleutelWaardes);
		return sleutelWaardes;
	}

	private void bouMake() {
		for (int maakIndeks = 0; maakIndeks < items.length; ++maakIndeks) {
			items[maakIndeks] = (MotorVervaardiger) make.elementAt(maakIndeks);
		}
	}
	
	private static String[] bouBeskrywings(int blaai, int blaaiGrootte, MotorVervaardiger[] items) {
		Vector venster = new Vector();
		int beginPosisie = blaai * blaaiGrootte;
		for (int itemIndeks = beginPosisie; itemIndeks < items.length
				&& itemIndeks - beginPosisie < blaaiGrootte; ++itemIndeks) {
			venster.addElement(items[itemIndeks].geeNaam());
		}

		String[] finaleLys = new String[venster.size()];
		venster.copyInto(finaleLys);
		return finaleLys;
	}

	
	private class MaakVergelyking implements Vergelyking {

		public int vergelyk(Object links, Object regs) {
			if (links == null)
				return -1;
			if (regs == null)
				return 1;
			
			return ((MotorVervaardiger) links).vergelyk((MotorVervaardiger) regs);
		}
		
	}

	public String[] kryBeskrywings() {
		return beskrywings;
	}

	public Sleutel[] kryIds() {
		return ids;
	}

	public int geeMaksBlaaie() {
		return items.length / blaaiGrootte + 1;
	}

	public static MotorVervaardiger geeMaak(int maakId) {
		for (int maakPos = 0; maakPos < make.size(); ++maakPos) {
			MotorVervaardiger maak = (MotorVervaardiger) make.elementAt(maakPos);
			if (maak.geeId() == maakId)
				return maak;
		}
		return null;
	}
}
