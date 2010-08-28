package net.derev.model;

import net.derev.model.VasteSleutels;

public interface EntiteitModelFabriek {

	EntiteitModel maak(String naam, int blaai, int blaaiGrootte, boolean vanKleinNaGroot,
			VasteSleutels geeFilters);

}
