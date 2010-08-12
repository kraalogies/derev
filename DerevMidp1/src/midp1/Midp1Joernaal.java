package midp1;

import platform.Joernaal;
import platform.roep.StringFunk;

public class Midp1Joernaal implements Joernaal {

	public void fout(String teks) {
		System.out.println(teks);
	}

	public void fout(StringFunk teks) {
		System.out.println(teks.roep());
	}

	public void info(String teks) {
		System.out.println(teks);
	}

	public void info(StringFunk teks) {
		System.out.println(teks.roep());
	}

	public void fout(Exception e) {
		e.printStackTrace();
	}

}
