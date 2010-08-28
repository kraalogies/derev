package net.derev.model;

import platform.Omgewing;
import net.derev.infrastruktuur.Pare;

public class Sleutels {
	private final Pare pare = new Pare();
	public void voegby(String naam, Sleutel sleutelWaarde) {
		pare.voegby(naam, sleutelWaarde);
	}

	public VasteSleutels vries(Omgewing omgewing) {
		Object[] sleutelObjekte = pare.vriesWaardes();
		Sleutel[] sleutels = new Sleutel[sleutelObjekte.length];
		for (int i = 0; i < sleutels.length; ++i) {
			sleutels[i] = (Sleutel) sleutelObjekte[i];
		}
		String[] name = pare.vriesSleutels();
		return new VasteSleutels(omgewing, name, sleutels);
	}

}
