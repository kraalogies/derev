package android.ia;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import android.R;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import platform.Joernaal;
import platform.ia.Leser;
import platform.ia.Skema;

public class AndroidLeser implements Leser {
	private final Context context;
	private final Joernaal joernaal;
	public AndroidLeser(Context context, Joernaal joernaal) {
		this.context = context;
		this.joernaal = joernaal;
	}
	@Override
	public String lees(String pad) throws Exception {
		if (Skema.Intern.Pas(pad))
			return leesJar(Skema.Intern.KryPad(pad));
		return null;
	}
	private String leesJar(String pad) throws Exception {
		try {
			pad = pad.substring(1);
			final InputStream in = context.getAssets().open(pad);
			if (in == null)
				return null;
			try {
				Reader r = new InputStreamReader(in, "UTF-8");
				StringBuffer sb = new StringBuffer();
				int ch;
				while((ch = r.read()) != -1)
				    sb.append((char)ch);
				return sb.toString();
			} finally {
				in.close();
			}
		} catch (Exception e) {
			joernaal.fout("Kan nie " + pad + " lees nie", e);
			throw e;
		}
	}
	public Bitmap leesPrentjie(String pad) throws IOException {
		if (Skema.Intern.Pas(pad))
			return leesPrentjieLokaal(Skema.Intern.KryPad(pad));
		return null;
	}
	private Bitmap leesPrentjieLokaal(String pad) throws IOException {
		pad = pad.substring(1); 
		final InputStream in = context.getAssets().open(pad);
		if (in == null)
			return null;
		try {
			return BitmapFactory.decodeStream(in);
		} finally {
			in.close();
		}
	}
}
