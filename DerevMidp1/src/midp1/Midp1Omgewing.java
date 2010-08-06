package midp1;

import platform.Joernaal;
import platform.Omgewing;

public class Midp1Omgewing implements Omgewing {

	public Joernaal kryJoernaal() {
		return new Midp1Joernaal();
	}

}
