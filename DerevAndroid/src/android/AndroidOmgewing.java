package android;

import platform.Joernaal;
import platform.Omgewing;

public class AndroidOmgewing implements Omgewing {
	private final Joernaal joernaal;
	
	public AndroidOmgewing(String joernaalNaam) {
		joernaal = new AndroidJoernaal(joernaalNaam);
	}
	@Override
	public Joernaal kryJoernaal() {
		return joernaal;
	}

}
