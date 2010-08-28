package net.derev.infrastruktuur;

import platform.Omgewing;

public class VastePareInPare {
	private final VastePare pare;

	public VastePareInPare(Omgewing omgewing, String[] sleutels, VastePare[] waardes) {
		super();
		this.pare = new VastePare(omgewing, sleutels, waardes);
	}
	
	public int geeLengte() {
		return pare.geeLengte();
	}
	
	public String geeSleutel(int posisie) {
		return pare.geeSleutel(posisie);
	}
	
	public VastePare geeWaarde(int posisie) {
		return (VastePare) pare.geeWaarde(posisie);
	}

	public boolean pasSleutel(int posisie, String naam) {
		return pare.pasSleutel(posisie, naam);
	}

	public VastePare soekWaarde(String sleutelNaam) {
		return (VastePare) pare.soekWaarde(sleutelNaam);
	}
}
