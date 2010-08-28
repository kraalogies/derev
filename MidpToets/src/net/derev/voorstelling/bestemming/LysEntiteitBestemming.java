package net.derev.voorstelling.bestemming;

import net.derev.infrastruktuur.VastePare;
import net.derev.voorstelling.KontroleHouer;
import net.derev.voorstelling.spesifikasie.MyLysSpesifikasie;
import net.derev.voorstelling.spesifikasie.SkermSpesifikasie;

public class LysEntiteitBestemming implements EntiteitBestemming {

	private final String entiteitBeskrywing;
	private final KontroleHouer houer;
	public LysEntiteitBestemming(KontroleHouer houer, String entiteitBeskrywing, String entiteitNaam) {
		super();
		this.houer = houer;
		this.entiteitBeskrywing = entiteitBeskrywing;
		this.entiteitNaam = entiteitNaam;
	}

	private final String entiteitNaam;

	public void vat(SkermSpesifikasie oorsprong, VastePare invoer)
			throws Exception {
		MyLysSpesifikasie.maakKenmerke(houer, entiteitNaam, entiteitBeskrywing, invoer).doen();
	}

}
