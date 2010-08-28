package net.derev.voorstelling.bestemming;

import net.derev.infrastruktuur.VastePare;
import net.derev.voorstelling.KontroleHouer;
import net.derev.voorstelling.spesifikasie.MyNuweSpesifikasie;
import net.derev.voorstelling.spesifikasie.SkermSpesifikasie;

public class NuweEntiteitBestemming implements EntiteitBestemming {

	private final String entiteitBeskrywing;
	private final String entiteitNaam;
	private final KontroleHouer houer;
	public void vat(SkermSpesifikasie oorsprong, 
			VastePare invoer) throws Exception {
		new MyNuweSpesifikasie(houer, entiteitNaam, entiteitBeskrywing, null, null).doen();
	}

	public NuweEntiteitBestemming(KontroleHouer houer, String entiteitBeskrywing, String entiteitNaam) {
		super();
		this.houer = houer;
		this.entiteitBeskrywing = entiteitBeskrywing;
		this.entiteitNaam = entiteitNaam;
	}

}
