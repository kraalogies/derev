package net.derev.infrastruktuur;

import net.derev.nuts.Vergelyking;

public class Paar implements Vergelyking {
	private final String naam;
	private final Object waarde;
	public Paar(String naam, Object waarde) {
		super();
		this.naam = naam;
		this.waarde = waarde;
	}
	public String geeNaam() {
		return naam;
	}
	public Object geeWaarde() {
		return waarde;
	}
	public int vergelyk(Object links, Object regs) {
		if (links == regs)
			return 0;
		if (links == null)
			return -1;
		if (regs == null)
			return 1;
		return ((Paar) links).geeNaam().compareTo(((Paar) regs).geeNaam());
	}
}
