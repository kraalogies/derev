package net.derev.infrastruktuur;

import java.util.Vector;

import platform.Omgewing;

public class VasteStringe {
	private final VastePare pare;
	private VasteStringe(VastePare pare) {
		this.pare = pare;
	}
	public static VasteStringe sorteer(Omgewing omgewing, String[] dubbels, boolean vanKleinNaGroot) {
		return new VasteStringe(VastePare.sorteer(omgewing, dubbels, vanKleinNaGroot));
	}
	public VasteStringe(Omgewing omgewing, String[] sleutels, String[] waardes) {
		pare = new VastePare(omgewing, sleutels, waardes);
	}
	public VasteStringe(Omgewing omgewing, Vector dubbels) {
		pare = new VastePare(omgewing, dubbels);
	}
	public int geeLengte() {
		return pare.geeLengte();
	}
	
	public String geeSleutel(int posisie) {
		return pare.geeSleutel(posisie);
	}
	
	public String geeWaarde(int posisie) {
		return (String) pare.geeWaarde(posisie);
	}
	
	public boolean pasSleutel(int posisie, String naam) {
		return pare.pasSleutel(posisie, naam);
	}

	public String soekWaarde(String sleutelNaam) {
		return (String) pare.soekWaarde(sleutelNaam);
	}
}
