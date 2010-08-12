package midp1.ui;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.Item;
import javax.microedition.media.Manager;
import javax.microedition.media.MediaException;
import javax.microedition.media.Player;
import javax.microedition.media.control.VideoControl;

import platform.Joernaal;
import platform.Sein;
import platform.ui.Kontrole;
import platform.ui.PrentjieVeld;
import platform.ui.Skerm;

public class Midp1PrentjieVeld implements PrentjieVeld, Runnable {
	private final Form vorm;
	private byte[] data;
	private final Joernaal joernaal;
	public Midp1PrentjieVeld(Skerm skerm, Joernaal joernaal, Form vorm, String etiket) {
		this.vorm = vorm;
		this.joernaal = joernaal;
		final Midp1PrentjieVeld hierdie = this;
		skerm.voegbyBevel("Foto", new Sein() {
			public void stuur() {
				Thread t = new Thread(hierdie);
				t.start();
			}
		});
	}

	public Kontrole aktiveer() {
		// TODO Auto-generated method stub
		return this;
	}

	public Kontrole deaktiveer() {
		// TODO Auto-generated method stub
		return this;
	}

	public Kontrole weg() {
		// TODO Auto-generated method stub
		return this;
	}

	public Kontrole wys() {
		// TODO Auto-generated method stub
		return null;
	}

	public byte[] kry() {
		return data;
	}

	public byte[] lees(InputStream in) throws IOException {
		final ByteArrayOutputStream uit = new ByteArrayOutputStream();
		try {
			byte[] buffer = new byte[1024];
			for (int n; (n = in.read(buffer)) > 0;)
				uit.write(buffer, 0, n);
			uit.flush();
			return uit.toByteArray();
		} finally {
			uit.close();
		}
	}
	
	public PrentjieVeld stel(InputStream in) throws IOException {
		this.data = lees(in);
		vorm.append(Image.createImage(data, 0, data.length - 1));
		return this;
	}

	public void run() {
		try {
			Player speler = Manager.createPlayer("capture://video");
			speler.realize();
			VideoControl kontrole = (VideoControl) speler.getControl("VideoControl");
			Item item = (Item) kontrole.initDisplayMode(VideoControl.USE_GUI_PRIMITIVE, null);
			vorm.append(item);
			speler.start();
		} catch (Exception e) {
			joernaal.fout(e);
		}	
	}

}
