package midp1.ui;

import i18n.Bevel;
import i18n.Woordeboek;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.List;
import javax.microedition.midlet.MIDlet;

import midp1.Seine;
import platform.Joernaal;
import platform.Sein;
import platform.roep.GetalProsedure;
import platform.ui.ProtoSkerm;

public class Midp1Lys implements ProtoSkerm, CommandListener {
	private final MIDlet midlet;
	private final Woordeboek woordeboek;
	private final List lys;
	private final Seine seine = new Seine();
	private final GetalProsedure kies;
	public Midp1Lys(String titel, String[] items, GetalProsedure kies, MIDlet midlet, Woordeboek woordeboek, Joernaal joernaal) {
		this.kies = kies;
		this.midlet = midlet;
		this.woordeboek = woordeboek;
		lys = new List(titel, List.IMPLICIT);
		lys.setCommandListener(this);
		for (int i = 0; i < items.length; ++i) {
			lys.append(items[i], null);
		}
		lys.addCommand(List.SELECT_COMMAND);
	}

	public void verwyderBevel(Bevel bevel) {
		String naam = woordeboek.kry(bevel);
		if (seine.bestaan(naam)) 
			lys.removeCommand(seine.kryBevel(naam));
		seine.verwyder(naam);
	}

	public void voegbyBevel(Bevel bevel, Sein sein) {
		String naam = woordeboek.kry(bevel);
		if (seine.bestaan(naam)) 
			lys.removeCommand(seine.kryBevel(naam));
		Command bevelObjek = new Command(naam, Command.SCREEN, 1);
		seine.voegby(naam, sein, bevelObjek);
		lys.addCommand(bevelObjek);
	}

	public void wys() {
		Display.getDisplay(midlet).setCurrent(lys);
	}

	public void commandAction(Command bevel, Displayable skerm) {
		if (seine.bestaan(bevel))
			seine.krySein(bevel).stuur();
		else if (bevel == List.SELECT_COMMAND)
			kies.roep(lys.getSelectedIndex());
	}

}
