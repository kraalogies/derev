//package standardbank.kontrole;
//
//import net.derev.Komposisie;
//import net.derev.infrastruktuur.Houer;
//import net.derev.infrastruktuur.HouerKoppelvlak;
//import net.derev.infrastruktuur.VastePare;
//import net.derev.l10n.Globaal;
//import net.derev.model.ProfielModel;
//import net.derev.navigasie.Keuselys;
//import net.derev.voorstelling.KontroleHouer;
//import net.derev.voorstelling.NuweRekordKontrole;
//import net.derev.voorstelling.StoorKontrole;
//import net.derev.voorstelling.aansig.NuweEntiteitAansig;
//import net.derev.voorstelling.bestemming.NuweEntiteitBestemming;
//import net.derev.voorstelling.spesifikasie.MyNuweSpesifikasie;
//import net.derev.voorstelling.spesifikasie.NuweSpesifikasie;
//import net.derev.voorstelling.spesifikasie.SkermSpesifikasie;
//import net.derev.voorstelling.spesifikasie.StoorSpesifikasie;
//import standardbank.l10n.Lokaal;
//import standardbank.model.Profiel;
//import standardbank.model.SensitieweProfiel;
//import standardbank.model.Sessie;
//
//public class ProfielKontrole implements NuweRekordKontrole, StoorKontrole {
//	private final Keuselys keuselys;
//	private final HouerKoppelvlak houer;
//
//	public ProfielKontrole() throws Exception {
//		houer = Houer.geeHouer();
//		keuselys = (Keuselys) houer.gee(Keuselys.class);
//	}
//
//	public static final String NAAM = "Profiel";
//
//	public static void registreer(KontroleHouer kontroleHouer) {
//		kontroleHouer.registreerKontroleKlas(ProfielKontrole.NAAM,
//				ProfielKontrole.class);
//	}
//
//	public void wysInvoer(NuweSpesifikasie spes) throws Exception {
//		Sessie sessie = krySessie();
//		if (sessie == null) {
//			laaiSessieSkerm(spes);
//			return;
//		}
//		try {
//			Profiel huidig = (Profiel) houer.gee(Profiel.class);
//			SensitieweProfiel sensitieweProfiel = huidig
//					.geeSensitieweProfiel(sessie.geeWagwoord());
//			if (sensitieweProfiel == null) {
//				if (huidig.geeDekripsiePogings() > 3)
//					houer.verwyder(Profiel.class);
//				laaiSessieSkerm(spes);
//				return;
//			}
//			if (spes.geeHuidigeInvoer() == null) {
//				String[] verstekInvoer = new String[] { ProfielModel.NOMMER,
//						sensitieweProfiel.geeRekeningNommer(),
//						ProfielModel.PIN, sensitieweProfiel.geePin(),
//						ProfielModel.WAGWOORD, sensitieweProfiel.geeWagwoord(),
//						ProfielModel.TAAL, huidig.geeTaalKode(),
//						ProfielModel.TAAL + "/", huidig.geeTaalKode() };
//				spes = spes.kloonMetInvoer(new VastePare(verstekInvoer));
//			}
//		} catch (Exception e) {
//		}
//		ProfielModel model = new ProfielModel(houer.geeKultuur(), sessie.geeWagwoord());
//		new NuweEntiteitAansig(spes, model.geeVelde(), model.geeVerwysings());
//
//	}
//
//	public void stoor(StoorSpesifikasie spes) throws Exception {
//		Sessie sessie = krySessie();
//		if (sessie == null) {
//			laaiSessieSkerm(spes);
//			return;
//		}
//		Profiel profiel = new ProfielModel(houer.geeKultuur(), sessie.geeWagwoord()).stoor(spes
//				.geeInvoer());
//		profiel.stoor();
//		Komposisie.stelKultuur(houer, profiel.geeTaalKode(), null, Lokaal.GLOBALE_FRASES, Lokaal.LOKALE_FRASES);
//		houer.registreerEnkeling(Profiel.class, profiel);
//		keuselys.gaanTerug();
//	}
//
//	private void laaiSessieSkerm(SkermSpesifikasie spes) throws Exception {
//		NuweEntiteitBestemming bestemming = new NuweEntiteitBestemming(
//				ProfielKontrole.NAAM, Globaal.PROFIEL);
//		new MyNuweSpesifikasie("Sessie", Globaal.SESSIE, null, bestemming)
//				.doen();
//	}
//
//	private Sessie krySessie() {
//		try {
//			return (Sessie) Houer.gee(Sessie.class);
//		} catch (Exception e) {
//			return null;
//		}
//	}
//
//}
