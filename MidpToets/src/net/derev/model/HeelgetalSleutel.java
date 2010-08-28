package net.derev.model;

public class HeelgetalSleutel implements Sleutel {
	private final int waarde;

	public HeelgetalSleutel(int waarde) {
		super();
		this.waarde = waarde;
	}

	public boolean vergelyk(int id) {
		return id == waarde;
	}

	public boolean vergelyk(Sleutel sleutel) {
		return sleutel.vergelyk(waarde);
	}

	public Object[] geeWaardes() {
		return new Object[] { new Integer(waarde) };
	}

	public boolean vergelyk(String waarde) {
		if (waarde == null || waarde.length() == 0)
			return false;
		return this.waarde == Integer.parseInt(waarde);
	}

}
