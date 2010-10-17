package android.ui;

import java.io.IOException;

import android.app.Activity;
import android.content.Context;
import android.ia.AndroidLeser;
import android.view.ViewGroup;
import android.widget.ImageView;
import i18n.Bevel;
import platform.Joernaal;
import platform.ui.Kontrole;
import platform.ui.PrentjieVeld;

public class AndroidPrentjieVeld implements PrentjieVeld {
	private final ImageView prentjieVeld;
	private final Joernaal joernaal;
	private final Context konteks;
	public AndroidPrentjieVeld(ViewGroup ouer, Activity aktiwiteit, Joernaal joernaal) {
		this.joernaal = joernaal;
		konteks = aktiwiteit;
		prentjieVeld = new ImageView(aktiwiteit);
		ouer.addView(prentjieVeld);
	}
	@Override
	public PrentjieVeld laatFotoNeem(Bevel fotoBevel) {
		return this;
	}

	@Override
	public PrentjieVeld lees(String uri) {
		AndroidLeser leser = new AndroidLeser(konteks, joernaal);
		try {
			prentjieVeld.setImageBitmap(leser.leesPrentjie(uri));
		} catch (IOException e) {
			joernaal.fout("Kan nie prentjie lees nie", e);
		}
		return this;
	}

	@Override
	public Kontrole aktiveer() {
		prentjieVeld.setEnabled(true);
		return this;
	}

	@Override
	public Kontrole deaktiveer() {
		prentjieVeld.setEnabled(false);
		return this;
	}

	@Override
	public Kontrole weg() {
		prentjieVeld.setVisibility(ImageView.INVISIBLE);
		return this;
	}

	@Override
	public Kontrole wys() {
		prentjieVeld.setVisibility(ImageView.VISIBLE);
		return this;
	}

}
