package net.derev.voorstelling.bestemming;

import net.derev.model.Sleutel;
import net.derev.navigasie.Keuselys;
import net.derev.voorstelling.KontroleHouer;
import net.derev.voorstelling.spesifikasie.MyDetailSpesifikasie;
import net.derev.voorstelling.spesifikasie.SkermSpesifikasie;

public class DetailIdBestemming implements EntiteitIdBestemming {
	private final String beskrywingSleutel;
	private final Keuselys keuselys;
	private final KontroleHouer kontroleHouer;
	public DetailIdBestemming(KontroleHouer kontroleHouer, Keuselys keuselys, String beskrywingSleutel) {
		super();
		this.kontroleHouer = kontroleHouer;
		this.keuselys = keuselys; 
		this.beskrywingSleutel = beskrywingSleutel;
	}
	public void vat(SkermSpesifikasie oorsprong, String naam, Sleutel waarde, String opsomming) throws Exception {
		SkermSpesifikasie spes = new MyDetailSpesifikasie(kontroleHouer, naam, beskrywingSleutel, waarde);
		keuselys.gooiBroodKrummel(oorsprong);
		spes.doen();
	}

}
