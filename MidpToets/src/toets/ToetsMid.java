package toets;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;

public class ToetsMid extends MIDlet implements CommandListener {

	public ToetsMid() {
	}

	protected void destroyApp(boolean unconditional)
			throws MIDletStateChangeException {
	}

	protected void pauseApp() {
	}

	protected void startApp() throws MIDletStateChangeException {
		Form form = new Form("Hallo Midp");
		form.addCommand(new Command("Exit!", Command.EXIT, 1));
		form.setCommandListener(this);
		Display.getDisplay(this).setCurrent(form);
	}

	public void commandAction(Command c, Displayable d) {
		if (c.getCommandType() == Command.EXIT)
		{
			notifyDestroyed();
			return;
		}
	}

}
