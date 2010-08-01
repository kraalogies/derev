package android.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import application.Klient;

public class DerevActivity extends Activity {
	private final KonkreteSkermOpwekker skermOpwekker;
	
	public DerevActivity() {
		skermOpwekker = new KonkreteSkermOpwekker(this);
	}
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new Klient(skermOpwekker);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
    	return skermOpwekker.onCreateOptionsMenu(menu);
    }
    
    public boolean onOptionsItemSelected(MenuItem item) {
    	return skermOpwekker.onOptionsItemSeletected(item);
    }
}