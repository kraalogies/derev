package net.derev.voorstelling.bestemming;

import platform.Omgewing;
import net.derev.model.Sleutel;
import net.derev.model.Sleutels;
import net.derev.model.VasteSleutels;
import net.derev.navigasie.Keuselys;
import net.derev.voorstelling.KontroleHouer;
import net.derev.voorstelling.spesifikasie.LysSpesifikasie;
import net.derev.voorstelling.spesifikasie.MyLysSpesifikasie;
import net.derev.voorstelling.spesifikasie.SkermSpesifikasie;

public class PerkIdBestemming implements EntiteitIdBestemming {
	private final LysSpesifikasie lys;
	private final Omgewing omgewing;
	private final Keuselys keuselys;
	private final KontroleHouer houer;
	public PerkIdBestemming(KontroleHouer houer, Omgewing omgewing, Keuselys keuselys, LysSpesifikasie lysOmTeBeperk) {
		super();
		this.lys = lysOmTeBeperk;
		this.omgewing = omgewing;
		this.keuselys = keuselys;
		this.houer = houer;
	}

	private Sleutels kryToepaslikeFilters(String nuweBeperking, VasteSleutels vasteRoosters) {
		Sleutels nuwePerke = new Sleutels();
		if (vasteRoosters == null)
			return nuwePerke;
		for (int perkPos = 0; perkPos < vasteRoosters.geeLengte(); ++perkPos) {
			if (vasteRoosters.pasNaam(perkPos, nuweBeperking))
				continue;
			nuwePerke.voegby(vasteRoosters.geeNaam(perkPos), vasteRoosters.geeWaarde(perkPos));
		}
		return nuwePerke;
	}
	
	public void vat(SkermSpesifikasie oorsprong, String naam, Sleutel sleutel, String opsomming) throws Exception {
		Sleutels nuwePerke = kryToepaslikeFilters(naam, lys.geeFilters());
		nuwePerke.voegby(naam, sleutel);
		keuselys.vergeetLaaste();
		LysSpesifikasie nuweLys = new MyLysSpesifikasie(
				houer,
				lys.geeNaam(), 
				lys.geeBeskrywingSleutel(), 
				lys.geeBestemming(), 
				lys.geeLysSpes(), 
				lys.geeBlaai(), 
				lys.isVanKleinNaGroot(), 
				nuwePerke.vries(omgewing),
				lys.geeEersteKeuse(),
				null);
		nuweLys.doen();
	}
}
