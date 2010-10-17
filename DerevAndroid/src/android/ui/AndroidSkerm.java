package android.ui;

import i18n.Bevel;
import i18n.Etiket;
import i18n.Woordeboek;
import platform.Joernaal;
import platform.Sein;
import platform.ui.Merk;
import platform.ui.PrentjieVeld;
import platform.ui.Skerm;
import platform.ui.Teks;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TableLayout;

public class AndroidSkerm implements Skerm, AndroidBedrading {
	private final TableLayout uitleg;
	private final Activity aktiwiteit;
	private final Bevele bevele = new Bevele();
	private final Woordeboek woordeboek;
	private final Joernaal joernaal;
	public AndroidSkerm(Activity aktiwiteit, Woordeboek woordeboek, Joernaal joernaal) {
		this.woordeboek = woordeboek;
		this.aktiwiteit = aktiwiteit;
		this.joernaal = joernaal;
		uitleg = new TableLayout(aktiwiteit);
	}

	@Override
	public void wys() {
		aktiwiteit.setContentView(uitleg);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		for (int i = 0; i < bevele.kryAantal(); ++i) {
			String naam = bevele.kryNaam(i);
			menu.add(0, i, i, naam);
		}
		return true;
	}
	@Override
	public boolean onOptionsItemSeletected(MenuItem item) {
		Sein sein = bevele.krySein(item.getItemId());
		sein.stuur();
		return true;
	}
	@Override
	public void verwyderBevel(Bevel bevel) {
		bevele.verwyder(woordeboek.kry(bevel));
	}
	@Override
	public void voegbyBevel(Bevel bevel, Sein sein) {
		bevele.voegby(woordeboek.kry(bevel), sein);
	}
	@Override
	public Merk voegbyMerk(Etiket etiket) {
		return new AndroidMerker(uitleg, aktiwiteit, woordeboek.kry(etiket));
	}
	@Override
	public PrentjieVeld voegbyPrentjie(Etiket etiket) {
		return new AndroidPrentjieVeld(uitleg, aktiwiteit, joernaal);
	}
	@Override
	public Teks voegbyTeks(Etiket etiket) {
		return new StandaardInvoer(uitleg, aktiwiteit, woordeboek.kry(etiket));
	}

}
