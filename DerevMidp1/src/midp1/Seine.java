package midp1;

import java.util.Hashtable;

import javax.microedition.lcdui.Command;

import platform.Dubbel;
import platform.Sein;

public class Seine {
	private Hashtable seine = new Hashtable();
	public boolean bestaan(Command bevel) {
		return seine.containsKey(bevel.getLabel());
	}
	public boolean bestaan(String naam) {
		return seine.containsKey(naam);
	}
	public Command kryBevel(String naam) {
		Dubbel dubbel = (Dubbel) seine.get(naam);
		return (Command) dubbel.kryTweede();
	}
	public void voegby(String naam, Sein sein, Command command) {
		seine.put(naam, new Dubbel(sein, command));
	}
	public Sein krySein(Command bevel) {
		Dubbel dubbel = (Dubbel) seine.get(bevel.getLabel());
		return (Sein) dubbel.kryEerste();
	}
	public void verwyder(String naam) {
		seine.remove(naam);
	}
}
