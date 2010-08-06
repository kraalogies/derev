package midp1.ui;

import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Item;
import javax.microedition.lcdui.StringItem;
import javax.microedition.lcdui.TextField;

import platform.ui.Kontrole;
import platform.ui.Teks;

public class TeksInvoer implements Teks {
	private TextField invoerVeld;
	private StringItem teksVeld;
	private StringItem plekhouer;
	private String teks;
	private final int maksLengte;
	private final Form vorm;

	public TeksInvoer(Form vorm, String etiket, String teks, int maksLengte, boolean slegsLees) {
		this.vorm = vorm;
		this.maksLengte= maksLengte;
		if (teks != null && teks.length() > maksLengte)
			teks = teks.substring(0, maksLengte - 1);
		this.teks = teks;
		if (slegsLees) 
			teksVeld = new StringItem(etiket, teks);
		else
			invoerVeld = new TextField(etiket, teks, maksLengte, TextField.ANY);
		vorm.append(invoerVeld);
	}
	public Teks stel(String teks) {
		if (invoerVeld != null)
			invoerVeld.setString(teks);
		else if (teksVeld != null)
			teksVeld.setText(teks);
		else
			this.teks = teks;
		return this;
	}
	public String kry() {
		return invoerVeld != null 
			? invoerVeld.getString() 
			: (teksVeld != null ? teksVeld.getText() : teks);
	}
	private static void stelKontrole(Form vorm, Item huidige, Item nuwe) {
		for (int i = 0; i < vorm.size(); ++i) {
			if (vorm.get(i) == huidige) {
				vorm.set(i, nuwe);
				break;
			}
		}
	}
	public Kontrole aktiveer() {
		if (teksVeld == null) return this;
		
		invoerVeld = new TextField(teksVeld.getLabel(), teksVeld.getText(), maksLengte, TextField.ANY);
		stelKontrole(vorm, teksVeld, invoerVeld);
		teksVeld = null;
		
		return this;
	}
	public Kontrole deaktiveer() {
		if (invoerVeld == null) return this;
		
		teksVeld = new StringItem(invoerVeld.getLabel(), invoerVeld.getString());
		stelKontrole(vorm, invoerVeld, teksVeld);
		invoerVeld = null;
		
		return this;
	}
	public Kontrole weg() {
		if (plekhouer != null) return this;
		
		plekhouer = new StringItem(null, null);
		teks = kry();
		stelKontrole(vorm, invoerVeld != null ? (Item) invoerVeld : teksVeld, plekhouer);
		
		return this;
	}
	public Kontrole wys() {
		if (plekhouer == null) return this;
		
		stelKontrole(vorm, plekhouer, invoerVeld != null ? (Item) invoerVeld : teksVeld);
		plekhouer = null;
		
		return this;
	}
}
