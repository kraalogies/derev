package midp1;

import java.io.InputStream;

import platform.Joernaal;
import platform.Omgewing;
import platform.data.PrentjieOpwekker;

public class Midp1Omgewing implements Omgewing {

	public Joernaal kryJoernaal() {
		return new Midp1Joernaal();
	}

	public PrentjieOpwekker kryPrentjieOpwekker() {
		return new Midp1PrentjieOpwekker();
	}

	public InputStream kryLokalePrentjie(String naam) throws Exception {
		try {
			return getClass().getResourceAsStream(naam);
		} catch (Exception e) {
			kryJoernaal().fout("Kan nie lokale prentjie " + naam + " lees nie");
			throw e;
		}
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

	
}
