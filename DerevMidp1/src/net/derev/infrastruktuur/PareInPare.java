package net.derev.infrastruktuur;

import platform.Omgewing;

public class PareInPare {
	private final Pare pare = new Pare();
	public void voegby(String sleutel, VastePare waarde) {
		pare.voegby(sleutel, waarde);
	}

	public VastePareInPare vries(Omgewing omgewing) {
		Object[] waardes = pare.vriesWaardes();
		String[] sleutels = pare.vriesSleutels();
		return new VastePareInPare(omgewing, sleutels, (VastePare[]) waardes);
	}
}
