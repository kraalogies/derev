package net.derev.voorstelling.spesifikasie;

import net.derev.infrastruktuur.VastePare;
import net.derev.voorstelling.KontroleHouer;
import net.derev.voorstelling.StoorKontrole;

public class MyStoorSpesifikasie implements StoorSpesifikasie {
	private final String naam;
	private final String beskrywingSleutel;
	private final VastePare invoer;
	private final KontroleHouer kontroleHouer;
	public MyStoorSpesifikasie(KontroleHouer kontroleHouer, String naam, String beskrywingSleutel,
			VastePare invoer) throws Exception {
		this.naam = naam;
		this.beskrywingSleutel = beskrywingSleutel;
		this.invoer = invoer;
		this.kontroleHouer = kontroleHouer;
	}

	public void doen() throws Exception {
		StoorKontrole kontrole = kontroleHouer.geeStoor(naam);
		kontrole.stoor(this);
	}

	public String geeBeskrywingSleutel() {
		return beskrywingSleutel;
	}

	public String geeNaam() {
		return naam;
	}

	public VastePare geeInvoer() {
		return invoer;
	}

	public Object geeKenmerk(String naam) {
		return null;
	}

}
