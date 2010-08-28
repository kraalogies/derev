package net.derev.infrastruktuur;

import java.util.Vector;

public class Rekords {
	private final String[] velde;
	private final Vector waardes = new Vector();
	
	public void voegby(int waarde) {
		voegby(new Integer(waarde));
	}
	
	public void voegby(Object waarde) {
		voegby(new Object[]{waarde});
	}
	public void voegby(Object[] ry) {
		if (ry == null)
			throw new NullPointerException();
		if (ry.length != velde.length)
			throw new IllegalArgumentException();
		waardes.addElement(ry);
	}

	public Rekords(String veld) {
		this(new String[]{veld});
	}
	public Rekords(String[] velde) {
		super();
		this.velde = velde;
	}
	
	public VasteRekords vries() {
		Object[][] nuweWaardes = new Object[waardes.size()][];
		waardes.copyInto(nuweWaardes);
		return new VasteRekords(velde, nuweWaardes);
	}
}
