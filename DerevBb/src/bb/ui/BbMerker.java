package bb.ui;

import platform.ui.Kontrole;
import platform.ui.Merk;
import net.rim.device.api.ui.component.CheckboxField;
import net.rim.device.api.ui.component.NullField;

public class BbMerker implements Merk {
	private final CheckboxField merker;
	private NullField plekhouer;
	public BbMerker(KonkreteSkerm konkreteSkerm, String etiket) {
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
		return this;
	}

	public Kontrole deaktiveer() {
		merker.setEditable(false);
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
