package net.derev.voorstelling.bestemming;

import net.derev.infrastruktuur.VastePare;
import net.derev.voorstelling.spesifikasie.SkermSpesifikasie;

public interface EntiteitBestemming {
	void vat(SkermSpesifikasie oorsprong, VastePare invoer) throws Exception;
}
