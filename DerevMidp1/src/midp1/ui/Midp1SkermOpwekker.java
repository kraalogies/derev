package midp1.ui;

import i18n.Woordeboek;

import javax.microedition.midlet.MIDlet;

import platform.Joernaal;
import platform.roep.GetalProsedure;
import platform.ui.ProtoSkerm;
import platform.ui.SkermOpwekker;
import platform.ui.Skerm;

public class Midp1SkermOpwekker implements SkermOpwekker {
	private final Joernaal joernaal;
	public Midp1SkermOpwekker(MIDlet midlet, Woordeboek woordeboek, Joernaal joernaal) {
		super();
		this.joernaal = joernaal;
		this.midlet = midlet;
		this.woordeboek = woordeboek;
	}

	private final MIDlet midlet;
	private final Woordeboek woordeboek;

	public Skerm maak() {
		return new Midp1Skerm(midlet, woordeboek, joernaal);
	}

	public ProtoSkerm maakLys(String titel, String[] items, GetalProsedure kies) {
		return new Midp1Lys(titel, items, kies, midlet, woordeboek, joernaal);
	}

}
