package net.derev.model;

public class StringSleutel implements Sleutel {
	private final String waarde;
	public StringSleutel(String sleutel) {
		super();
		this.waarde = sleutel;
	}

	public Object[] geeWaardes() {
		return new Object[] { waarde };
	}

	public boolean vergelyk(int id) {
		return waarde.equals(Integer.toString(id));
	}

	public boolean vergelyk(Sleutel sleutel) {
		return sleutel.vergelyk(waarde);
	}

	public boolean vergelyk(String waarde) {
		if (waarde == this.waarde)
			return true;
		if (waarde == null || this.waarde == null)
			return false;
		return this.waarde.equals(waarde);
	}

}
