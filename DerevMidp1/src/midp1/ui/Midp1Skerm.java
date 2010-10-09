package midp1.ui;

import i18n.Bevel;
import i18n.Etiket;
import i18n.Woordeboek;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.midlet.MIDlet;

import midp1.Midp1Woordeboek;
import midp1.Seine;
import midp1.ia.Midp1Leser;

import platform.Joernaal;
import platform.Sein;
import platform.ui.Merk;
import platform.ui.PrentjieVeld;
import platform.ui.Teks;
import platform.ui.Skerm;

public class Midp1Skerm implements Skerm, CommandListener {
	private final Form vorm;
	private final Seine seine = new Seine();
	private final MIDlet midlet;
	private final Joernaal joernaal;
	private final Woordeboek woordeboek;
	public Midp1Skerm(MIDlet midlet, Woordeboek woordeboek, Joernaal joernaal) {
		this.joernaal = joernaal;
		this.midlet = midlet;
		this.woordeboek = woordeboek;
		vorm = new Form("");
		vorm.setCommandListener(this);
	}
	public void voegbyBevel(Bevel bevel, Sein sein) {
		String naam = woordeboek.kry(bevel);
		if (seine.bestaan(naam)) 
			vorm.removeCommand(seine.kryBevel(naam));
		Command bevelObjek = new Command(naam, Command.SCREEN, 1);
		seine.voegby(naam, sein, bevelObjek);
		vorm.addCommand(bevelObjek);
	}

	public Teks voegbyTeks(Etiket etiket) {
		return new TeksInvoer(woordeboek, vorm, etiket, null, 10, false);
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
	public Merk voegbyMerk(Etiket etiket) {
		return new Midp1Merk(woordeboek, vorm, etiket);
	}
	public PrentjieVeld voegbyPrentjie(Etiket etiket) {
		return new Midp1PrentjieVeld(woordeboek, midlet, this, joernaal, vorm, etiket);
	}
	public void verwyderBevel(Bevel bevel) {
		String naam = woordeboek.kry(bevel);
		if (seine.bestaan(naam)) 
			vorm.removeCommand(seine.kryBevel(naam));
		seine.verwyder(naam);
	}

}
