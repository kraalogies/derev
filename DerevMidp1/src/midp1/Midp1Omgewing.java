package midp1;

import i18n.Woordeboek;

import java.io.IOException;
import java.io.InputStream;

import platform.Joernaal;
import platform.Omgewing;
import platform.data.PrentjieOpwekker;
import platform.ia.Leser;

public class Midp1Omgewing implements Omgewing {
	private static Woordeboek woordeboek;
	private static Joernaal joernaal = new Midp1Joernaal();
	public Midp1Omgewing(Leser leser) throws IOException {
		woordeboek = new Midp1Woordeboek(leser, joernaal);
	}
	public Joernaal kryJoernaal() {
		return joernaal;
	}

	public PrentjieOpwekker kryPrentjieOpwekker() {
		return new Midp1PrentjieOpwekker();
	}

	public int kryBlaaiGrootte() {
		return 10;
	}

	public boolean vergelykKasOnsensitief(String links, String regs) {
		if (links == regs)
			return true;
		if (links == null || regs == null)
			return false;
		return links.toLowerCase() == regs.toLowerCase();
	}

	public Woordeboek kryWoordeboek() {
		return woordeboek;
	}

	
}
