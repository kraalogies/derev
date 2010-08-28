package net.derev.infrastruktuur;

import platform.Omgewing;

public class RoostersInPare {
	private final Pare pare = new Pare();
	public void voegby(String sleutel, Object[] waarde) {
		pare.voegby(sleutel, waarde);
	}

	public VasteRoosters vries(Omgewing omgewing) {
		Object[] roosters = pare.vriesWaardes();
		Object[][] waardes = new Object[roosters.length][];
		for (int i = 0; i < roosters.length; ++i) {
			waardes[i] = (Object[]) roosters[i];
		}
		String[] sleutels = pare.vriesSleutels();
		return new VasteRoosters(omgewing, sleutels, waardes);
	}
}
