package net.derev.infrastruktuur;

import platform.Omgewing;

public class VasteRoosters {
	private final VastePare pare;
	public VasteRoosters(Omgewing omgewing, String[] sleutels, Object[][] waardes) {
		pare = new VastePare(omgewing, sleutels, waardes);
	}
	
	public int geeLengte() {
		return pare.geeLengte();
	}
	
	public String geeSleutel(int posisie) {
		return pare.geeSleutel(posisie);
	}
	
	public Object[] geeWaarde(int posisie) {
		return (Object[]) pare.geeWaarde(posisie);
	}
	
	public Object[][] geeWaardes() {
		return (Object[][]) pare.geeWaardes();
	}

	public boolean pasSleutel(int posisie, String naam) {
		return pare.pasSleutel(posisie, naam);
	}

	public Object[] soekWaarde(String sleutelNaam) {
		return (Object[]) pare.soekWaarde(sleutelNaam);
	}
}
