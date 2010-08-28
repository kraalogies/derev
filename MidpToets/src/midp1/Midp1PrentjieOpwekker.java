package midp1;

import platform.data.Kleur;
import platform.data.Prentjie;
import platform.data.PrentjieOpwekker;

public class Midp1PrentjieOpwekker implements PrentjieOpwekker {

	public Prentjie maak(int wydte, int hoogte, Kleur kleur) {
		return new Midp1Prentjie(wydte, hoogte, kleur);
	}

}
