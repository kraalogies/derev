package midp1;

import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;

import midp1.ui.Midp1SkermOpwekker;

import application.Klient;

public class HoofMid extends MIDlet {

	public HoofMid() {
	}

	protected void destroyApp(boolean unconditional)
			throws MIDletStateChangeException {
	}

	protected void pauseApp() {
	}

	protected void startApp() throws MIDletStateChangeException {
		new Klient(new Midp1SkermOpwekker(this), new Midp1Omgewing());
	}

}
