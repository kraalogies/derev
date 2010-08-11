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

	public InputStream kryLokalePrentjie(String naam) {
		return getClass().getResourceAsStream(naam);
	}

	
}
