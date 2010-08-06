package android.ui;

import platform.ui.Kontrole;
import platform.ui.Teks;
import android.app.Activity;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class StandaardInvoer implements Teks {
	private final TextView etiketVeld;
	private final EditText invoer;
	public StandaardInvoer(ViewGroup ouer, Activity aktiwiteit, String etiket) {
		LinearLayout teksUitleg = new LinearLayout(aktiwiteit);

		etiketVeld = new TextView(aktiwiteit);
		etiketVeld.setText(etiket);
		teksUitleg.addView(etiketVeld);
		
		invoer = new EditText(aktiwiteit);
		teksUitleg.addView(invoer);
		
		ouer.addView(teksUitleg);
	}
	@Override
	public String kry() {
		return invoer.getText().toString();
	}
	@Override
	public Teks stel(String teks) {
		invoer.setText(teks);
		return this;
	}
	@Override
	public Kontrole aktiveer() {
		invoer.setEnabled(true);
		return this;
	}
	@Override
	public Kontrole deaktiveer() {
		invoer.setEnabled(false);
		return this;
	}
	@Override
	public Kontrole weg() {
		etiketVeld.setVisibility(TextView.GONE);
		invoer.setVisibility(EditText.GONE);
		return this;
	}
	@Override
	public Kontrole wys() {
		etiketVeld.setVisibility(TextView.VISIBLE);
		invoer.setVisibility(EditText.VISIBLE);
		return this;
	}

}
