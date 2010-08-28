package net.derev.voorstelling.bestemming;

import platform.Omgewing;
import net.derev.infrastruktuur.Pare;
import net.derev.infrastruktuur.VastePare;
import net.derev.model.Sleutel;
import net.derev.navigasie.Keuselys;
import net.derev.voorstelling.KontroleHouer;
import net.derev.voorstelling.spesifikasie.MyNuweSpesifikasie;
import net.derev.voorstelling.spesifikasie.SkermSpesifikasie;

public class NuweEntiteitIdBestemming implements EntiteitIdBestemming {
	private final String entiteitNaam;
	private final String entiteitBeskrywing;
	private final VastePare huidigeInvoer;
	private final String[] sleutelVeldName;
	private final Keuselys keuselys;
	private final Omgewing omgewing;
	private final KontroleHouer houer;
	public NuweEntiteitIdBestemming(KontroleHouer houer, Omgewing omgewing, Keuselys keuselys, String entiteitNaam, String entiteitBeskrywing, VastePare huidigeInvoer, String[] sleutelVeldName) {
		super();
		this.houer = houer;
		this.omgewing = omgewing;
		this.keuselys = keuselys;
		this.entiteitNaam = entiteitNaam;
		this.entiteitBeskrywing = entiteitBeskrywing;
		this.huidigeInvoer = huidigeInvoer;
		this.sleutelVeldName = sleutelVeldName;
	}
	public void vat(SkermSpesifikasie oorsprong, String naam, Sleutel sleutel, String opsomming)
			throws Exception {
		keuselys.vergeetLaaste(); 
		// NuweEntiteitBestemming is daar vir verwysing skerms om terug te kom na die Nuwe skerm toe
		// Toe die verwysing skerm geroep is, is 'n broodkrummel gegooi om te kan terug kom. 
		// Die broodkrummel moet nou uitgevee word deur vergeetLaaste te roep
		Pare pare = new Pare(huidigeInvoer);
		voegSleutelsBy(sleutelVeldName, sleutel, pare);
		String opsommingNaam = naam + "/";
		pare.veeEersteUit(opsommingNaam);
		pare.voegby(opsommingNaam, opsomming);
		SkermSpesifikasie spes = new MyNuweSpesifikasie(houer, entiteitNaam, entiteitBeskrywing, pare.vries(omgewing), null);
		spes.doen();
	}
	private static void voegSleutelsBy(String[] sleutelVeldName, Sleutel sleutel, Pare pare) {
		if (sleutelVeldName == null)
			return;
		Object[] sleutelVeldWaardes = sleutel.geeWaardes();
		if (sleutelVeldWaardes.length != sleutelVeldName.length)
			throw new IllegalArgumentException();
		for (int veldPos = 0; veldPos < sleutelVeldName.length; ++veldPos) {
			String sleutelNaam = sleutelVeldName[veldPos];
			pare.veeEersteUit(sleutelNaam);
			pare.voegby(sleutelNaam, sleutelVeldWaardes[veldPos]);
		}
	}

}
