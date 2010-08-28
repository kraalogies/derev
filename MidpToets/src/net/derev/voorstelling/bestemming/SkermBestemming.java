package net.derev.voorstelling.bestemming;

import net.derev.model.Sleutel;
import net.derev.navigasie.Keuselys;
import net.derev.voorstelling.spesifikasie.SkermSpesifikasie;

public class SkermBestemming implements EntiteitIdBestemming {
	private final SkermSpesifikasie[] skerms;
	private final Keuselys keuselys;
	public SkermBestemming(Keuselys keuselys, SkermSpesifikasie[] skerms) throws NullPointerException, InstantiationException, IllegalAccessException {
		super();
		this.skerms = skerms;
		this.keuselys = keuselys;
	}
	public void vat(SkermSpesifikasie oorsprong, String naam, Sleutel sleutel,
			String opsomming) throws Exception {
		for (int skermPos = 0; skermPos < skerms.length; ++skermPos) {
			if (!sleutel.vergelyk(skermPos))
				continue;
			keuselys.gooiBroodKrummel(oorsprong);
			skerms[skermPos].doen();
			return;
		}
	}

}
