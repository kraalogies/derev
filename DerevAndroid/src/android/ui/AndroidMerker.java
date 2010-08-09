package android.ui;

import platform.ui.Kontrole;
import platform.ui.Merk;
import android.app.Activity;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

public class AndroidMerker implements Merk {
	private final CheckBox merker;
	private final TextView etiketVeld;
	public AndroidMerker(ViewGroup ouer, Activity aktiwiteit, String etiket) {
		merker = new CheckBox(aktiwiteit);

		LinearLayout teksUitleg = new LinearLayout(aktiwiteit);

		etiketVeld = new TextView(aktiwiteit);
		etiketVeld.setText(etiket);
		teksUitleg.addView(etiketVeld);
		
		teksUitleg.addView(merker);
		
		ouer.addView(teksUitleg);
}

	@Override
	public boolean isGemerk() {
		return merker.isChecked();
	}

	@Override
	public Merk merk(boolean aan) {
		merker.setChecked(aan);
		return this;
	}

	@Override
	public Kontrole aktiveer() {
		merker.setEnabled(true);
		return this;
	}

	@Override
	public Kontrole deaktiveer() {
		merker.setEnabled(false);
		return this;
	}

	@Override
	public Kontrole weg() {
		etiketVeld.setVisibility(TextView.INVISIBLE);
		merker.setVisibility(CheckBox.INVISIBLE);
		return this;
	}

	@Override
	public Kontrole wys() {
		etiketVeld.setVisibility(TextView.VISIBLE);
		merker.setVisibility(CheckBox.VISIBLE);
		return this;
	}

}
