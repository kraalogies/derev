package net.derev.voorstelling.spesifikasie;

import net.derev.infrastruktuur.VastePare;
import net.derev.voorstelling.bestemming.EntiteitBestemming;

public interface NuweSpesifikasie extends SkermSpesifikasie {
	VastePare geeHuidigeInvoer();
	EntiteitBestemming geeBestemming();
	NuweSpesifikasie kloonMetInvoer(VastePare invoer) throws Exception;

}
