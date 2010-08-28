package net.derev.model;

import platform.Omgewing;
import net.derev.infrastruktuur.VasteStringe;

public class MyEntiteitModel implements EntiteitModel {
	private static final String[] tale = new String[] { "English", "en", "Afrikaans", "af" };
	private final String[] beskrywings;
	private final Sleutel[] ids;
	public MyEntiteitModel(Omgewing omgewing, String naam, int blaai, int blaaiGrootte,
			boolean vanKleinNaGroot, VasteSleutels filters) {
		VasteStringe data = VasteStringe.sorteer(omgewing, tale, vanKleinNaGroot);
		beskrywings = new String[data.geeLengte()];
		ids = new Sleutel[data.geeLengte()];
		for (int pos = 0; pos < data.geeLengte(); ++pos) {
			beskrywings[pos] = data.geeSleutel(pos);
			ids[pos] = new StringSleutel(data.geeWaarde(pos));
		}
	}

	public int geeMaksBlaaie() {
		return 1;
	}

	public String[] geeMoontlikeSiwwe() {
		return null;
	}

	public String[] kryBeskrywings() {
		return beskrywings;
	}

	public Sleutel[] kryIds() {
		return ids;
	}
}
