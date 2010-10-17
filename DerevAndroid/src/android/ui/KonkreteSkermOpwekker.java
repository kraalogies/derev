package android.ui;

import i18n.Woordeboek;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import platform.Joernaal;
import platform.ui.SkermOpwekker;
import platform.ui.Skerm;

public class KonkreteSkermOpwekker implements SkermOpwekker, AndroidBedrading {
	private final Activity activity;
	private AndroidBedrading bedrading;
	private final Woordeboek woordeboek;
	private final Joernaal joernaal;
	
	public KonkreteSkermOpwekker(Activity activity, Woordeboek woordeboek, Joernaal joernaal) {
		this.activity = activity;
		this.joernaal = joernaal;
		this.woordeboek = woordeboek;
	}
	
	@Override
	public Skerm maak() {
		AndroidSkerm screen = new AndroidSkerm(activity, woordeboek, joernaal);
		bedrading = screen;
		return screen;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		return bedrading != null ? bedrading.onCreateOptionsMenu(menu) : false;
	}

	@Override
	public boolean onOptionsItemSeletected(MenuItem item) {
		return bedrading != null ? bedrading.onOptionsItemSeletected(item) : false;
	}

}
