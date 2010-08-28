package net.derev.voorstelling.kontrole;

import javax.microedition.lcdui.Alert;

import net.derev.model.EntiteitModel;
import net.derev.model.EntiteitModelFabriek;
import net.derev.voorstelling.DetailKontrole;
import net.derev.voorstelling.Kontrole;
import net.derev.voorstelling.KontroleHouer;
import net.derev.voorstelling.LysKontrole;
import net.derev.voorstelling.NuweRekordKontrole;
import net.derev.voorstelling.StoorKontrole;
import net.derev.voorstelling.VertoonVenster;
import net.derev.voorstelling.spesifikasie.DetailSpesifikasie;
import net.derev.voorstelling.spesifikasie.LysSpesifikasie;
import net.derev.voorstelling.spesifikasie.NuweSpesifikasie;
import net.derev.voorstelling.spesifikasie.SkermSpesifikasie;
import net.derev.voorstelling.spesifikasie.StoorSpesifikasie;
import platform.Omgewing;

public class EntiteitKontrole {//implements Kontrole, LysKontrole, DetailKontrole, NuweRekordKontrole, StoorKontrole{

	private static final String NAAM = "entitiet";
	private final VertoonVenster venster;
	private final Omgewing omgewing;
	private final EntiteitModelFabriek modelFabriek;

//	public static void registreer(KontroleHouer houer) {
//		houer.registreerKontroleKlas(EntiteitKontrole.NAAM,
//				EntiteitKontrole.class, true);
//	}

	private void nogNie(String skermNaam, SkermSpesifikasie spes) throws NullPointerException, InstantiationException, IllegalAccessException {
		venster.wys(new Alert(skermNaam + " " + spes.geeNaam()));
	}
	
//	public void lys(LysSpesifikasie spes) throws Exception {
//		int grootte = omgewing.kryBlaaiGrootte();
//
//		EntiteitModel model = modelFabriek.maak(spes.geeNaam(), spes.geeBlaai(),
//				grootte, spes.isVanKleinNaGroot(), spes.geeFilters());
//		new LysEntiteitAansig(spes, model.kryBeskrywings(), model.kryIds(), model
//				.geeMaksBlaaie(), model.geeMoontlikeSiwwe());
//	}

	public EntiteitKontrole(VertoonVenster venster, Omgewing omgewing, EntiteitModelFabriek modelFabriek) {
		super();
		this.venster = venster;
		this.omgewing = omgewing;
		this.modelFabriek = modelFabriek;
	}

	public void wysDetail(DetailSpesifikasie spes) throws NullPointerException, InstantiationException, IllegalAccessException {
		nogNie("detail", spes);
	}

	public void wysInvoer(NuweSpesifikasie spes) throws NullPointerException, InstantiationException, IllegalAccessException {
		nogNie("invoer", spes);
	}

	public void stoor(StoorSpesifikasie spes) throws NullPointerException, InstantiationException, IllegalAccessException {
		nogNie("stoor", spes);
	}

	public void wys(SkermSpesifikasie spes) throws NullPointerException, InstantiationException, IllegalAccessException {
		nogNie("wys", spes);
	}

}
