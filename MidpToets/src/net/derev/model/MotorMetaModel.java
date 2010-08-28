package net.derev.model;

import net.derev.l10n.Globaal;

public class MotorMetaModel {

	public Verwysing[] geeVerwysings() {
		return new Verwysing[] { new Verwysing("Vervaardiger", "MaakId", Globaal.MOTOR, true) };
	}
	public Veld[] geeVelde() {
		Veld[] velde = new Veld[3];
		velde[0] = Veld.maakGetalVeld("Id", "Id", true, 0, Integer.MAX_VALUE);
		velde[1] = Veld.maakStringVeld("Spesifikasie", "Spesifikasie", 0, 20, false);
		velde[2] = Veld.maakGetalVeld("Jaar", "Jaar", true, 1900, 2010);
		return velde;
	}

}
