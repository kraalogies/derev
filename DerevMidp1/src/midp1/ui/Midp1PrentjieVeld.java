package midp1.ui;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Image;

import platform.ui.Kontrole;
import platform.ui.PrentjieVeld;

public class Midp1PrentjieVeld implements PrentjieVeld {
	private final Form vorm;
	private byte[] data;
	
	public Midp1PrentjieVeld(Form vorm, String etiket) {
		this.vorm = vorm;
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

}
