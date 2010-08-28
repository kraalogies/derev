package net.derev.infrastruktuur;

import net.derev.nuts.RoosterFunksies;

public class VasteRekords {
	private final String[] velde;
	private final Object[][] waardes;
	
	public VasteRekords(String veld, Object[] waardes) {
		velde = new String[] { veld };
		this.waardes = new Object[][] { waardes };
	}
	public VasteRekords(String[] velde, Object[][] waardes) {
		this.velde = velde;
		this.waardes = waardes;
	}
	
	public int geeAantalRekords() {
		return waardes.length;
	}
	
	public boolean vergelyk(Object[] toetsWaarde, int posisie) {
		if (posisie < 0 || posisie >= velde.length)
			throw new IllegalArgumentException();
		Object[] hierdieWaardes = (Object[]) waardes[posisie];
		return RoosterFunksies.vergelyk(hierdieWaardes, toetsWaarde);
	}

	public Object[] geeWaarde(int posisie) {
		if (posisie < 0 || posisie >= velde.length)
			throw new IllegalArgumentException();
		return (Object[]) waardes[posisie];
	}
}
