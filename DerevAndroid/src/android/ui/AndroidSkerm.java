package android.ui;

import platform.Sein;
import platform.ui.Merk;
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
	public AndroidSkerm(Activity aktiwiteit) {
		this.aktiwiteit = aktiwiteit;
		uitleg = new TableLayout(aktiwiteit);
	}
	@Override
	public void voegbyBevel(String naam, Sein sein) {
		bevele.voegby(naam, sein);
	}

	@Override
	public Teks voegbyTeks(String etiket) {
		return new StandaardInvoer(uitleg, aktiwiteit, etiket);
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
	public Merk voegbyMerk(String etiket) {
		return new AndroidMerker(uitleg, aktiwiteit, etiket);
	}

}
