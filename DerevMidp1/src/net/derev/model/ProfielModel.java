package net.derev.model;

import net.derev.infrastruktuur.Kultuur;
import net.derev.infrastruktuur.VastePare;
import net.derev.l10n.Globaal;
import standardbank.l10n.Lokaal;
import standardbank.model.Profiel;

public class ProfielModel {

	public static final String WAGWOORD = "Wagwoord";
	public static final String PIN = "PIN";
	public static final String NOMMER = "Nommer";
	public static final String TAAL = "Taal";
	
	private final String sessieWagwoord;
	private final Kultuur kultuur;

	public ProfielModel(Kultuur kultuur, String sessieWagwoord) {
		super();
		this.kultuur = kultuur;
		this.sessieWagwoord = sessieWagwoord;
	}

	public Veld[] geeVelde() {
		Veld[] velde = new Veld[3];
		velde[0] = Veld.maakSyferVeld(NOMMER, kultuur.kryFrase(Lokaal.REKENINGNOMMER), 18, true);
		velde[1] = Veld.maakPinVeld(PIN, "PIN", 5);
		velde[2] = Veld.maakWagwoordVeld(WAGWOORD, kultuur.kryFrase(Lokaal.REKENINGWAGWOORD), 6, 20);
		return velde;
	}

	public Verwysing[] geeVerwysings() {
		return new Verwysing[] { new Verwysing(kultuur.kryFrase(Globaal.TAAL), "Taal", "Taal", true) };
	}

	public Profiel stoor(VastePare invoer) {
		String nommer = (String) invoer.soekWaarde(NOMMER);
		String wagwoord = (String) invoer.soekWaarde(WAGWOORD);
		String pin = (String) invoer.soekWaarde(PIN);
		byte[] data = Profiel.enkripteerSensitieweData(sessieWagwoord, nommer, pin, wagwoord);
		return new Profiel(data, (String) invoer.soekWaarde(TAAL));
	}

}
