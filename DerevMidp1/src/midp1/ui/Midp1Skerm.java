package midp1.ui;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.midlet.MIDlet;

import midp1.Seine;

import platform.Sein;
import platform.ui.Merk;
import platform.ui.PrentjieVeld;
import platform.ui.Teks;
import platform.ui.Skerm;

public class Midp1Skerm implements Skerm, CommandListener {
	private final Form vorm;
	private final Seine seine = new Seine();
	private final MIDlet midlet;
	
	public Midp1Skerm(MIDlet midlet) {
		this.midlet = midlet;
		vorm = new Form("");
		vorm.setCommandListener(this);
	}
	public void voegbyBevel(String naam, Sein sein) {
		if (seine.bestaan(naam)) 
			vorm.removeCommand(seine.kryBevel(naam));
		Command bevel = new Command(naam, Command.SCREEN, 1);
		seine.voegby(naam, sein, bevel);
		vorm.addCommand(bevel);
	}

	public Teks voegbyTeks(String etiket) {
		return new TeksInvoer(vorm, etiket, null, 10, false);
	}

	public void wys() {
		Display.getDisplay(midlet).setCurrent(vorm);
	}
	
	public void commandAction(Command bevel, Displayable d) {
		if (!seine.bestaan(bevel))
			return;
		Sein sein = seine.krySein(bevel);
		sein.stuur();
	}
	public Merk voegbyMerk(String etiket) {
		return new Midp1Merk(vorm, etiket);
	}
	public PrentjieVeld voegbyPrentjie(String etiket) {
		return new Midp1PrentjieVeld(vorm, etiket);
	}

}
