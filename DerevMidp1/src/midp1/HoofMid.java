package midp1;

import java.io.IOException;

import i18n.Woordeboek;

import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;
import javax.microedition.lcdui.Display;
import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;

import platform.Omgewing;

import midp1.ia.Midp1Leser;
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
		try {
			Omgewing omgewing = new Midp1Omgewing(new Midp1Leser());
			new Klient(new Midp1SkermOpwekker(this, omgewing.kryWoordeboek(), omgewing.kryJoernaal()), omgewing);
		} catch (IOException e) {
			Display.getDisplay(this).setCurrent(new Alert("Error", "Cannot initialize application - " + e.toString(), null, AlertType.ERROR));
		}
	}

}
