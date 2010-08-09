package bb.ui;

import platform.ui.Kontrole;
import platform.ui.Merk;
import net.rim.device.api.ui.XYRect;
import net.rim.device.api.ui.component.CheckboxField;
import net.rim.device.api.ui.component.NullField;

public class BbMerker implements Merk {
	private final CheckboxField merker;
	private NullField plekhouer;
	private final KonkreteSkerm skerm;
	public BbMerker(KonkreteSkerm konkreteSkerm, String etiket) {
		skerm = konkreteSkerm;
		merker = new CheckboxField(etiket, false);
		konkreteSkerm.add(merker);
	}

	public boolean isGemerk() {
		return merker.getChecked();
	}

	public Merk merk(boolean aan) {
		merker.setChecked(aan);
		return this;
	}

	public Kontrole aktiveer() {
		merker.setEditable(true);
		tekenWeer();
		return this;
	}

	private void tekenWeer() {
		if (merker.isVisible()) {
			XYRect posisie = merker.getContentRect();
			skerm.invalidate(posisie.x, posisie.y, posisie.width, posisie.height);
		}
	}

	public Kontrole deaktiveer() {
		merker.setEditable(false);
		tekenWeer();
		return this;
	}

	public Kontrole weg() {
		if (plekhouer != null) return this;
		plekhouer = new NullField();
		merker.getScreen().replace(merker, plekhouer);
		return this;
	}

	public Kontrole wys() {
		if (plekhouer == null) return this;
		plekhouer.getScreen().replace(plekhouer, merker);
		plekhouer = null;
		return this;
	}

}
