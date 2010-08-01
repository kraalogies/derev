package midp1.ui;

import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.TextField;

import platform.ui.Invoer;

public class KonkreteInvoer implements Invoer {
	private final TextField veld;
	public KonkreteInvoer(Form vorm, String etiket) {
		veld = new TextField(etiket, null, 10, TextField.ANY);
		vorm.append(veld);
	}
	public void stelTeks(String teks) {
		veld.setString(teks);
	}
	public String kryTeks() {
		return veld.getString();
	}
}
