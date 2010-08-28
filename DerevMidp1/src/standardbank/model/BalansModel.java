package standardbank.model;

import platform.Omgewing;
import net.derev.model.Sleutel;
import net.derev.model.StringSleutel;
import net.derev.model.VasteSleutels;
import standardbank.Balans;
import standardbank.Skraap;

public class BalansModel {
	private Balans[] balanse;

	public BalansModel(Omgewing omgewing, int blaai, boolean vanKleinNaGroot,
			VasteSleutels geeFilters, String rekeningNommer, String pin,
			String wagwoord) throws Exception {
		if (rekeningNommer == null || rekeningNommer.length() == 0)
			throw new NullPointerException("Rekening nommer leeg");
		if (pin == null || pin.length() == 0)
			throw new NullPointerException("PIN leeg");
		if (wagwoord == null || wagwoord.length() == 0)
			throw new NullPointerException("Wagwoord leeg");
		
		balanse = kryBalanse(omgewing, rekeningNommer, pin, wagwoord);
	}

	private static Balans[] vorigeBalanse = new Balans[] { 
		new Balans(100012, 5000, "JT Malherbe", "0234", "Tjek"), 
		new Balans(1000034, 50000, "Huis", "35234", "Verband"), 
		new Balans(200056, 7000, "Krediet", "5765", "Krediet") };
	//private static Balans[] vorigeBalanse = new Balans[0];

	private static Balans[] kryBalanse(Omgewing omgewing, String rekeningNommer, String pin,
			String wagwoord) throws Exception {
		synchronized (vorigeBalanse) {
			if (vorigeBalanse.length != 0)
				return vorigeBalanse;
			
			vorigeBalanse = Skraap.skraapBalans(omgewing, rekeningNommer, pin, wagwoord);
			return vorigeBalanse;
		}
	}

	public String[] kryBeskrywings() {
		String[] beskrywings = new String[balanse.length];
		for (int balansPos = 0; balansPos < balanse.length; ++balansPos) {
			Balans balans = balanse[balansPos];
			beskrywings[balansPos] = balans.toString();
		}
		return beskrywings;
	}

	public Sleutel[] kryIds() {
		Sleutel[] sleutels = new Sleutel[balanse.length];
		for (int balansPos = 0; balansPos < balanse.length; ++balansPos) {
			Balans balans = balanse[balansPos];
			sleutels[balansPos] = new StringSleutel(balans.geeRekeningNommer());
		}
		return sleutels;
	}

	public String[] geeMoontlikeSiwwe() {
		return null;
	}

	public int geeMaksBlaaie() {
		return 1;
	}

}
