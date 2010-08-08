package android;

import android.util.Log;
import platform.Joernaal;
import platform.roep.StringFunk;

public class AndroidJoernaal implements Joernaal {

	public AndroidJoernaal(String tag) {
		super();
		this.tag = tag;
	}

	private final String tag;

	@Override
	public void fout(String teks) {
		Log.e(tag, teks);
	}

	@Override
	public void fout(StringFunk teks) {
		fout(teks.roep());
	}

	@Override
	public void info(String teks) {
		Log.i(tag, teks);
	}

	@Override
	public void info(StringFunk teks) {
		info(teks.roep());
	}

}
