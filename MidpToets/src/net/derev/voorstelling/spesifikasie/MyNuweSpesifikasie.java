package net.derev.voorstelling.spesifikasie;

import net.derev.infrastruktuur.VastePare;
import net.derev.voorstelling.KontroleHouer;
import net.derev.voorstelling.NuweRekordKontrole;
import net.derev.voorstelling.bestemming.EntiteitBestemming;

public class MyNuweSpesifikasie implements NuweSpesifikasie {
	public MyNuweSpesifikasie(
			KontroleHouer kontroleHouer,
			String naam, 
			String beskrywingSleutel, 
			VastePare huidigeInvoer,
			EntiteitBestemming bestemming) throws Exception {
		super();
		this.naam = naam;
		this.beskrywingSleutel = beskrywingSleutel;
		this.huidigeInvoer = huidigeInvoer;
		this.bestemming = bestemming;
		this.kontroleHouer = kontroleHouer;
	}
	private final String naam;
	private final String beskrywingSleutel;
	private final VastePare huidigeInvoer;
	private final KontroleHouer kontroleHouer;
	private final EntiteitBestemming bestemming;

	public void doen() throws Exception {
		NuweRekordKontrole kontrole = kontroleHouer.geeNuwe(naam);
		kontrole.wysInvoer(this);
	}
	
	public String geeBeskrywingSleutel() {
		return beskrywingSleutel;
	}
	public String geeNaam() {
		return naam;
	}

	public VastePare geeHuidigeInvoer() {
		return huidigeInvoer;
	}

	public NuweSpesifikasie kloonMetInvoer(VastePare invoer) throws Exception {
		return new MyNuweSpesifikasie(kontroleHouer, naam, beskrywingSleutel, invoer, bestemming);
	}

	public Object geeKenmerk(String naam) {
		return null;
	}

	public EntiteitBestemming geeBestemming() {
		return bestemming;
	}

	public static SkermSpesifikasie maakBasies(KontroleHouer kontroleHouer, String naam, String beskrywingSleutel) throws Exception {
		return new MyNuweSpesifikasie(kontroleHouer, naam, beskrywingSleutel, null, null);
	}

}
