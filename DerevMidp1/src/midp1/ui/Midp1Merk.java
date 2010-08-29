package midp1.ui;

import i18n.Etiket;
import i18n.Woordeboek;

import javax.microedition.lcdui.Choice;
import javax.microedition.lcdui.ChoiceGroup;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Item;
import javax.microedition.lcdui.StringItem;

import platform.ui.Kontrole;
import platform.ui.Merk;

public class Midp1Merk implements Merk {
	private ChoiceGroup merker;
	private StringItem teksVeld;
	private StringItem plekhouer;
	private boolean gemerk;
	private final String etiket;
	private final Form vorm;
	public Midp1Merk(Woordeboek woordeboek, Form vorm, Etiket etiket) {
		this.etiket = woordeboek.kry(etiket);
		this.vorm = vorm;
		merker = new ChoiceGroup(null, Choice.MULTIPLE, new String[] {this.etiket}, null);
		vorm.append(merker);
	}

	public boolean isGemerk() {
		if (merker == null) return gemerk;
		
		final boolean[] merk = new boolean[1];
		merker.getSelectedFlags(merk);
		return merk[0];
	}

	public Merk merk(boolean aan) {
		if (merker == null) return this;
		
		final boolean[] merk = new boolean[] { aan };
		merker.setSelectedFlags(merk);
		return this;
	}

	public Kontrole aktiveer() {
		if (teksVeld == null) return this;
		
		merker = new ChoiceGroup(null, Choice.MULTIPLE, new String[] {etiket}, null);
		merk(gemerk);
		TeksInvoer.stelKontrole(vorm, teksVeld, merker);
		teksVeld = null;
		
		return this;
	}

	public Kontrole deaktiveer() {
		gemerk = isGemerk();
		if (merker == null) return this;
		
		teksVeld = new StringItem(etiket, gemerk ? "ja" : "nee");
		TeksInvoer.stelKontrole(vorm, merker, teksVeld);
		merker = null;
		
		return this;
	}

	public Kontrole weg() {
		if (plekhouer != null) return this;
		
		plekhouer = new StringItem(null, null);
		TeksInvoer.stelKontrole(vorm, merker != null ? (Item) merker : teksVeld, plekhouer);
		
		return this;
	}

	public Kontrole wys() {
		if (plekhouer == null) return this;
		
		TeksInvoer.stelKontrole(vorm, plekhouer, merker != null ? (Item) merker : teksVeld);
		plekhouer = null;
		
		return this;
	}

}
