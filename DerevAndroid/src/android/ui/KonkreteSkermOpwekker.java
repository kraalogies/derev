package android.ui;

import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import platform.ui.SkermOpwekker;
import platform.ui.Skerm;

public class KonkreteSkermOpwekker implements SkermOpwekker, AndroidBedrading {
	private final Activity activity;
	private AndroidBedrading bedrading;
	
	public KonkreteSkermOpwekker(Activity activity) {
		this.activity = activity;
	}
	
	@Override
	public Skerm maak() {
		AndroidSkerm screen = new AndroidSkerm(activity);
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
