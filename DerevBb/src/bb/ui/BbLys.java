package bb.ui;

import i18n.Bevel;
import i18n.Woordeboek;
import net.rim.device.api.ui.MenuItem;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.component.Menu;
import net.rim.device.api.ui.component.ObjectListField;
import net.rim.device.api.ui.container.MainScreen;
import platform.Joernaal;
import platform.Sein;
import platform.roep.GetalProsedure;
import platform.ui.ProtoSkerm;
import bb.Bevele;

public class BbLys extends MainScreen implements ProtoSkerm {
	private final UiApplication app;
	private final Bevele bevele = new Bevele();
	private final Woordeboek woordeboek;
	private final ObjectListField lys;
	private final GetalProsedure kies;
	public BbLys(String[] items, final GetalProsedure kies, UiApplication app, Woordeboek woordeboek, Joernaal joernaal, String titel) {
		super();
		this.kies = kies;
		this.app = app;
		this.woordeboek = woordeboek;
		lys = new ObjectListField();
		add(lys);
		lys.set(items);
		
		setTitle(titel);
	}

	protected void makeMenu(Menu menu, int instance) {
		menu.add(new MenuItem(woordeboek.kry(Bevel.Kies), 100, 0){
			public void run() {
				kies.roep(lys.getSelectedIndex());
			}});
		for (int i = 0; i < bevele.kryAantal(); ++i) {
			final Sein sein = bevele.krySein(i);
			menu.add(new MenuItem(bevele.kryNaam(i), i+101, i+1) {
				public void run() {
					sein.stuur(); 
				}});
		}
	}

	public void verwyderBevel(Bevel bevel) {
		bevele.verwyder(woordeboek.kry(bevel));
	}

	public void voegbyBevel(Bevel bevel, Sein sein) {
		bevele.voegby(woordeboek.kry(bevel), sein);
	}

	public void wys() {
		app.pushScreen(this);
	}
}
