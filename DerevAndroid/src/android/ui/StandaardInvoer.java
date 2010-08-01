package android.ui;

import platform.ui.Invoer;
import android.app.Activity;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class StandaardInvoer implements Invoer {
	private final EditText invoer;
	public StandaardInvoer(ViewGroup ouer, Activity aktiwiteit, String etiket) {
		LinearLayout teksUitleg = new LinearLayout(aktiwiteit);

		TextView etiketVeld = new TextView(aktiwiteit);
		etiketVeld.setText(etiket);
		teksUitleg.addView(etiketVeld);
		
		invoer = new EditText(aktiwiteit);
		teksUitleg.addView(invoer);
		
		ouer.addView(teksUitleg);
	}
	@Override
	public String kryTeks() {
		return invoer.getText().toString();
	}

	@Override
	public void stelTeks(String teks) {
		invoer.setText(teks);
	}

}
