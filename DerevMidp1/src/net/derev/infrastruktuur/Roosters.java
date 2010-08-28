package net.derev.infrastruktuur;

import java.util.Vector;

public class Roosters {
	private final Vector waardes = new Vector();
	public void voegby(Object[] waarde) {
		waardes.addElement(waarde);
	}
	public Object[][] vries() {
		Object[][] nuweWaardes = new Object[waardes.size()][];
		waardes.copyInto(nuweWaardes);
		return nuweWaardes;
	}
	public void voegby(int geeId) {
		voegby(new Object[] { new Integer(geeId) });
	}
}
