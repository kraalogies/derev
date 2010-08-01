package bb;

import java.util.Vector;

import platform.Dubbel;
import platform.Sein;

public class Bevele {
	private final Vector bevele = new Vector();
	
	public void voegby(String naam, Sein sein) {
		bevele.addElement(new Dubbel(naam, sein));
	}

	public int kryAantal() {
		return bevele.size();
	}

	private Dubbel kryDubbel(int i) {
		return (Dubbel) bevele.elementAt(i);
	}
	
	public String kryNaam(int i) {
		return (String) kryDubbel(i).kryEerste();
	}

	public Sein krySein(int i) {
		return (Sein) kryDubbel(i).kryTweede();
	}

}
