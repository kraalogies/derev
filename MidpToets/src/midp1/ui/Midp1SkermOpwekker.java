package midp1.ui;

import javax.microedition.midlet.MIDlet;

import platform.Joernaal;
import platform.ui.SkermOpwekker;
import platform.ui.Skerm;

public class Midp1SkermOpwekker implements SkermOpwekker {
	private final Joernaal joernaal;
	public Midp1SkermOpwekker(MIDlet midlet, Joernaal joernaal) {
		super();
		this.joernaal = joernaal;
		this.midlet = midlet;
	}

	private final MIDlet midlet;

	public Skerm maak() {
		return new Midp1Skerm(midlet, joernaal);
	}

}
