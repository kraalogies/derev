package net.derev.voorstelling.spesifikasie;

import net.derev.model.Sleutel;
import net.derev.voorstelling.DetailKontrole;
import net.derev.voorstelling.KontroleHouer;

public class MyDetailSpesifikasie implements DetailSpesifikasie {
	private final String naam;
	private final String beskrywingSleutel;
	private final Sleutel waarde;
	private final KontroleHouer kontroleHouer;
	
	public MyDetailSpesifikasie(KontroleHouer kontroleHouer, String naam, String beskrywingSleutel,
			Sleutel waarde) throws Exception {
		super();
		this.naam = naam;
		this.beskrywingSleutel = beskrywingSleutel;
		this.waarde = waarde;
		this.kontroleHouer = kontroleHouer;
	}

	public void doen() throws Exception {
		DetailKontrole kontrole = kontroleHouer.geeDetail(naam);
		kontrole.wysDetail(this);
	}

	public String geeBeskrywingSleutel() {
		return beskrywingSleutel;
	}

	public String geeNaam() {
		return naam;
	}

	public Sleutel geeWaarde() {
		return waarde;
	}

	public Object geeKenmerk(String naam) {
		return null;
	}

}
