package android.ui;

import i18n.Woordeboek;

import java.io.IOException;

import platform.Joernaal;

import android.AndroidJoernaal;
import android.AndroidOmgewing;
import android.AndroidWoordeboek;
import android.app.Activity;
import android.ia.AndroidLeser;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import application.Klient;

public class DerevActivity extends Activity {
	private KonkreteSkermOpwekker skermOpwekker;
	
	public DerevActivity() throws Exception {
	}
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		Joernaal joernaal = new AndroidJoernaal("AndroidDerev");
		Woordeboek woordeboek;
		try {
			woordeboek = new AndroidWoordeboek(new AndroidLeser(this, joernaal), joernaal);
			skermOpwekker = new KonkreteSkermOpwekker(this, woordeboek, joernaal);
			new Klient(skermOpwekker, new AndroidOmgewing(woordeboek, joernaal));
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

    public boolean onCreateOptionsMenu(Menu menu) {
    	return skermOpwekker.onCreateOptionsMenu(menu);
    }
    
    public boolean onOptionsItemSelected(MenuItem item) {
    	return skermOpwekker.onOptionsItemSeletected(item);
    }
}