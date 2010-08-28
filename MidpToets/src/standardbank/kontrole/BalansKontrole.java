//package standardbank.kontrole;
//
//import net.derev.infrastruktuur.Houer;
//import net.derev.l10n.Globaal;
//import net.derev.voorstelling.KontroleHouer;
//import net.derev.voorstelling.LysKontrole;
//import net.derev.voorstelling.aansig.LysEntiteitAansig;
//import net.derev.voorstelling.bestemming.EntiteitBestemming;
//import net.derev.voorstelling.bestemming.LysEntiteitBestemming;
//import net.derev.voorstelling.spesifikasie.LysSpesifikasie;
//import net.derev.voorstelling.spesifikasie.MyNuweSpesifikasie;
//import standardbank.model.BalansModel;
//import standardbank.model.Profiel;
//import standardbank.model.SensitieweProfiel;
//import standardbank.model.Sessie;
//
//public class BalansKontrole implements LysKontrole {
//
//	public static final String NAAM = "Balans";
//	public static void registreer(KontroleHouer kontroleHouer) {
//		kontroleHouer.registreerKontroleKlas(BalansKontrole.NAAM,
//				BalansKontrole.class);
//	}
//
//	private Profiel kryProfiel() {
//		try {
//			return (Profiel) Houer.gee(Profiel.class);
//		} catch (Exception e) {
//			return Profiel.lees();
//		}
//	}
//	
//	public void lys(LysSpesifikasie spes) throws Exception {
//		Profiel profiel = kryProfiel();
//		if (profiel == null) {
//			laaiProfielSkerm(spes);
//			return;
//		}
//		Sessie sessie = (Sessie) Houer.gee(Sessie.class); // As ons op die punt kom het ons ' profiel wat beteken ons moet 'n sessie he
//		SensitieweProfiel sensitieweProfiel = profiel.geeSensitieweProfiel(sessie.geeWagwoord());
//		String rekeningNommer = sensitieweProfiel.geeRekeningNommer();
//		String pin = sensitieweProfiel.geePin();
//		String wagwoord = sensitieweProfiel.geeWagwoord();
//		BalansModel model = new BalansModel(spes.geeBlaai(), spes.isVanKleinNaGroot(), spes.geeFilters(), rekeningNommer, pin, wagwoord);
//		new LysEntiteitAansig(spes, model.kryBeskrywings(), model.kryIds(), model
//				.geeMaksBlaaie(), model.geeMoontlikeSiwwe());
//	}
//
//	private void laaiProfielSkerm(LysSpesifikasie spes) throws Exception {
//		EntiteitBestemming bestemming = new LysEntiteitBestemming(BalansKontrole.NAAM, Globaal.BALANS);
//		new MyNuweSpesifikasie("Profiel", Globaal.PROFIEL, null, bestemming).doen();
//	}
//
//}
