//package standardbank.kontrole;
//
//import java.io.IOException;
//
//import net.derev.infrastruktuur.Houer;
//import net.derev.infrastruktuur.HouerKoppelvlak;
//import net.derev.infrastruktuur.VastePare;
//import net.derev.l10n.Globaal;
//import net.derev.model.Veld;
//import net.derev.voorstelling.KontroleHouer;
//import net.derev.voorstelling.NuweRekordKontrole;
//import net.derev.voorstelling.StoorKontrole;
//import net.derev.voorstelling.aansig.NuweEntiteitAansig;
//import net.derev.voorstelling.spesifikasie.NuweSpesifikasie;
//import net.derev.voorstelling.spesifikasie.StoorSpesifikasie;
//import standardbank.model.Sessie;
//
//public class SessieKontrole  implements NuweRekordKontrole, StoorKontrole {
//	private final HouerKoppelvlak houer;
//	public SessieKontrole() throws NullPointerException, InstantiationException, IllegalAccessException {
//		houer = Houer.geeHouer();
//	}
//	public static final String NAAM = "Sessie";
//	public static void registreer(KontroleHouer kontroleHouer) {
//		kontroleHouer.registreerKontroleKlas(SessieKontrole.NAAM,
//				SessieKontrole.class);
//	}
//	public void wysInvoer(NuweSpesifikasie spes) throws Exception {
//		new NuweEntiteitAansig(spes, new Veld[]{Veld.maakWagwoordVeld("Wagwoord", houer.geeKultuur().kryFrase(Globaal.WAGWOORD), 0, 20)}, null);
//	}
//	public void stoor(StoorSpesifikasie spes) throws NullPointerException,
//			InstantiationException, IllegalAccessException, IOException,
//			InterruptedException {
//		VastePare pare = spes.geeInvoer();
//		String wagwoord = pare.soekWaarde("Wagwoord").toString();
//		Houer.registreerEnkeling(Sessie.class, new Sessie(wagwoord));
//	}
//}
