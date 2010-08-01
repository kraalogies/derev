package midp1.ui;

import javax.microedition.midlet.MIDlet;

import platform.ui.SkermOpwekker;
import platform.ui.Skerm;

public class Midp1SkermOpwekker implements SkermOpwekker {

	public Midp1SkermOpwekker(MIDlet midlet) {
		super();
		this.midlet = midlet;
	}

	private final MIDlet midlet;

	public Skerm maak() {
		return new Midp1Skerm(midlet);
	}

}
