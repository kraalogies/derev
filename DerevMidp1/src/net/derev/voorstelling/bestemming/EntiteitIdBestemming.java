package net.derev.voorstelling.bestemming;

import net.derev.model.Sleutel;
import net.derev.voorstelling.spesifikasie.SkermSpesifikasie;

public interface EntiteitIdBestemming {
	void vat(SkermSpesifikasie oorsprong, String naam, Sleutel sleutel, String opsomming) throws Exception;
}
