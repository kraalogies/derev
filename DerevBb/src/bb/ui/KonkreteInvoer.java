package bb.ui;

import platform.ui.Kontrole;
import platform.ui.Teks;
import net.rim.device.api.ui.Screen;
import net.rim.device.api.ui.component.EditField;
import net.rim.device.api.ui.component.NullField;
import net.rim.device.api.ui.container.MainScreen;

public class KonkreteInvoer implements Teks {
	private EditField veld;
	private NullField plekhouer;
	
	public KonkreteInvoer(MainScreen skerm, String etiket, String teks, int maksLengte, boolean slegsLees) {
		veld = new EditField(etiket, teks, maksLengte, 0);
		skerm.add(veld);
	}

	public String kry() {
		return veld.getText();
	}

	public Teks stel(String teks) {
		veld.setText(teks);
		return this;
	}

	public Kontrole aktiveer() {
		veld.setEditable(true);
		return this;
	}

	public Kontrole deaktiveer() {
		veld.setEditable(false);
		return this;
	}

	public Kontrole weg() {
		if (plekhouer != null) return this;
		plekhouer = new NullField();
		veld.getScreen().replace(veld, plekhouer);
		return this;
	}

	public Kontrole wys() {
		if (plekhouer == null) return this;
		plekhouer.getScreen().replace(plekhouer, veld);
		plekhouer = null;
		return this;
	}

}
