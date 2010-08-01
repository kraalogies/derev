package bb.ui;

import platform.ui.Invoer;
import net.rim.device.api.ui.component.EditField;
import net.rim.device.api.ui.container.MainScreen;

public class KonkreteInvoer implements Invoer {
	private EditField veld;

	public KonkreteInvoer(MainScreen skerm, String etiket) {
		veld = new EditField(etiket, "");
		skerm.add(veld);
	}

	public String kryTeks() {
		return veld.getText();
	}

	public void stelTeks(String teks) {
		veld.setText(teks);
	}

}
