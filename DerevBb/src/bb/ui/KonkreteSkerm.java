package bb.ui;

import platform.Sein;
import platform.ui.Merk;
import platform.ui.Skerm;
import platform.ui.Teks;
import bb.Bevele;
import net.rim.device.api.ui.MenuItem;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.component.Menu;
import net.rim.device.api.ui.container.MainScreen;

public class KonkreteSkerm extends MainScreen implements Skerm {
	private final UiApplication app;
	private final Bevele bevele = new Bevele();

	public KonkreteSkerm(UiApplication app) {
		super();
		this.app = app;
	}

	public void voegbyBevel(String naam, Sein sein) {
		bevele.voegby(naam, sein);
	}

	protected void makeMenu(Menu menu, int instance) {
		for (int i = 0; i < bevele.kryAantal(); ++i) {
			final Sein sein = bevele.krySein(i);
			menu.add(new MenuItem(bevele.kryNaam(i), i, i) {
				public void run() {
					sein.stuur(); 
				}});
		}
	}

	public void wys() {
		app.pushScreen(this);
	}

	public Teks voegbyTeks(String etiket) {
		return new KonkreteInvoer(this, etiket, null, 10, false);
	}

	public Merk voegbyMerk(String etiket) {
		return new BbMerker(this, etiket);
	}

}
