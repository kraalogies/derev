package bb.ui;

import i18n.Bevel;
import i18n.Etiket;
import i18n.Woordeboek;
import platform.Joernaal;
import platform.Sein;
import platform.ui.Merk;
import platform.ui.PrentjieVeld;
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
	private final Woordeboek woordeboek;
	private final Joernaal joernaal;
	public KonkreteSkerm(UiApplication app, Woordeboek woordeboek, Joernaal joernaal) {
		super();
		this.app = app;
		this.woordeboek = woordeboek;
		this.joernaal = joernaal;
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

	public void verwyderBevel(Bevel bevel) {
		bevele.verwyder(woordeboek.kry(bevel));
	}

	public void voegbyBevel(Bevel bevel, Sein sein) {
		bevele.voegby(woordeboek.kry(bevel), sein);
	}

	public Merk voegbyMerk(Etiket etiket) {
		return new BbMerker(this, woordeboek.kry(etiket));
	}

	public PrentjieVeld voegbyPrentjie(Etiket etiket) {
		return new BbPrentjieVeld(this, app, woordeboek, this, joernaal, etiket);
	}

	public Teks voegbyTeks(Etiket etiket) {
		return new KonkreteInvoer(this, woordeboek.kry(etiket), null, 10, false);
	}

}
