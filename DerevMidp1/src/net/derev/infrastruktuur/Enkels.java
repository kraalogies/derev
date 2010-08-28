package net.derev.infrastruktuur;

import java.util.Vector;

public class Enkels {
	private final Vector enkels = new Vector();

	public void voegby(String string) {
		enkels.addElement(string);
	}
	
	public String[] gee() {
		String[] stringe = new String[enkels.size()];
		for (int i = 0; i < enkels.size(); ++i) {
			stringe[i] = (String) enkels.elementAt(i);
		}
		return stringe;
	}
}
