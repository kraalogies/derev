//package standardbank;
//
//import javax.microedition.lcdui.Alert;
//import javax.microedition.lcdui.AlertType;
//import javax.microedition.lcdui.Display;
//import javax.microedition.lcdui.Displayable;
//import javax.microedition.midlet.MIDlet;
//import javax.microedition.midlet.MIDletStateChangeException;
//
//import net.derev.Komposisie;
//import net.derev.infrastruktuur.RefleksieHouer;
//import net.derev.l10n.Globaal;
//import net.derev.model.EntiteitModelFabriek;
//import net.derev.model.MyEntiteitModelFabriek;
//import net.derev.voorstelling.KontroleHouer;
//import net.derev.voorstelling.VertoonVenster;
//import net.derev.voorstelling.kontrole.EntiteitKontrole;
//import net.derev.voorstelling.kontrole.LysNavigasieKontrole;
//import net.derev.voorstelling.spesifikasie.MyLysSpesifikasie;
//import net.derev.voorstelling.spesifikasie.MyNuweSpesifikasie;
//import net.derev.voorstelling.spesifikasie.SkermSpesifikasie;
//import standardbank.kontrole.BalansKontrole;
//import standardbank.kontrole.ProfielKontrole;
//import standardbank.kontrole.SessieKontrole;
//import standardbank.l10n.Lokaal;
//
//public class StdBank extends MIDlet implements VertoonVenster {
//
//	private boolean opgestel;
//
//	public StdBank() {
//	}
//
//	protected void destroyApp(boolean unconditional)
//			throws MIDletStateChangeException {
//	}
//
//	protected void pauseApp() {
//	}
//
//	protected void startApp() throws MIDletStateChangeException {
//		try {
//			stelOp();
//			MyLysSpesifikasie.maakBasies(LysNavigasieKontrole.NAAM, Lokaal.STDBANK).doen();
//		} catch (Exception e) {
//			e.printStackTrace();
//			wys(new Alert("Fout", e.toString(), null, AlertType.ERROR));
//		}
//	}
//
//	public void vernietig() {
//		try {
//			destroyApp(false);
//		} catch (MIDletStateChangeException e) {
//		}
//		notifyDestroyed();
//	}
//
//	public void wys(Displayable skerm) {
//		Display.getDisplay(this).setCurrent(skerm);
//	}
//
//	private void stelOp() throws Exception  {
//		synchronized(this) {
//			if (opgestel)
//				return;
//			RefleksieHouer houer = new RefleksieHouer();
//			houer.registreerEnkeling(VertoonVenster.class, this);
//			Komposisie.stelOp(houer, Lokaal.GLOBALE_FRASES, Lokaal.LOKALE_FRASES);
//			
//			houer.registreerEnkeling(EntiteitModelFabriek.class, new MyEntiteitModelFabriek());
//			
//			KontroleHouer kontroleHouer = (KontroleHouer) houer.gee(KontroleHouer.class);
//			EntiteitKontrole.registreer(kontroleHouer);
//			BalansKontrole.registreer(kontroleHouer);
//			ProfielKontrole.registreer(kontroleHouer);
//			SessieKontrole.registreer(kontroleHouer);
//
//			SkermSpesifikasie[] skerms = new SkermSpesifikasie[] {
//					MyLysSpesifikasie.maakBasies(BalansKontrole.NAAM, Globaal.BALANS),
//					MyNuweSpesifikasie.maakBasies(ProfielKontrole.NAAM, Globaal.PROFIEL),
//					MyNuweSpesifikasie.maakBasies(SessieKontrole.NAAM, Globaal.SESSIE)
//			};
//			LysNavigasieKontrole.registreer(kontroleHouer, skerms);
//		}
//	}
//	
//}
