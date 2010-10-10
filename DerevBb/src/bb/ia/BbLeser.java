package bb.ia;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import platform.ia.Leser;
import platform.ia.Skema;

public class BbLeser implements Leser {
	public static byte[] kryData(InputStream in) throws IOException {
		final ByteArrayOutputStream uit = new ByteArrayOutputStream();
		try {
			byte[] buffer = new byte[1024];
			for (int n; (n = in.read(buffer)) > 0;)
				uit.write(buffer, 0, n);
			uit.flush();
			return uit.toByteArray();
		} finally {
			uit.close();
		}
	}
	public String lees(String pad) throws IOException {
		if (Skema.Intern.Pas(pad))
			return leesJar(Skema.Intern.KryPad(pad));
		return null;
	}
	private String leesJar(String pad) throws IOException {
		final InputStream in = this.getClass().getResourceAsStream(pad);
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
	}

}
