package bb;

import platform.Joernaal;
import platform.Omgewing;

public class BbOmgewing implements Omgewing {
	private final Joernaal joernaal;
	public BbOmgewing(long appGuid, String appNaam) {
		joernaal = new BbJoernaal(appGuid, appNaam);
	}
	public Joernaal kryJoernaal() {
		return joernaal;
	}

}
