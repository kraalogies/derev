package net.derev.model;

import platform.Omgewing;
import net.derev.infrastruktuur.VastePare;

public class VasteSleutels {
	private final VastePare pare;
	public VasteSleutels(Omgewing omgewing, String[] name, Sleutel[] waardes) {
		pare = new VastePare(omgewing, name, waardes);
		
	}
	public int geeLengte() {
		return pare.geeLengte();
	}

	public boolean pasNaam(int posisie, String naam) {
		return pare.pasSleutel(posisie, naam);
	}
	public String geeNaam(int posisie) {
		return pare.geeSleutel(posisie);
	}
	public Sleutel geeWaarde(int posisie) {
		return (Sleutel) pare.geeWaarde(posisie);
	}
	public Sleutel soekWaarde(String sleutelNaam) {
		return (Sleutel) pare.soekWaarde(sleutelNaam);
	}
}
