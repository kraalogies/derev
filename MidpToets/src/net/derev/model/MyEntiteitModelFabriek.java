package net.derev.model;

import platform.Omgewing;

public class MyEntiteitModelFabriek implements EntiteitModelFabriek {
	private final Omgewing omgewing;
	public MyEntiteitModelFabriek(Omgewing omgewing) {
		super();
		this.omgewing = omgewing;
	}
	public EntiteitModel maak(String naam, int blaai, int grootte,
			boolean vanKleinNaGroot, VasteSleutels filters) {
		return new MyEntiteitModel(omgewing, naam, blaai, grootte, vanKleinNaGroot, filters);
	}

}
