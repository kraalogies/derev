package midp1;

import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;

import platform.Omgewing;

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
		Omgewing omgewing = new Midp1Omgewing();
		new Klient(new Midp1SkermOpwekker(this, omgewing.kryJoernaal()), omgewing);
	}

}
