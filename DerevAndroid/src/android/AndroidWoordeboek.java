package android;

import java.io.IOException;

import platform.Joernaal;
import platform.data.GetalGids;
import platform.ia.Leser;
import platform.nuts.StringFunksies;
import i18n.Bevel;
import i18n.Boodskap;
import i18n.Etiket;
import i18n.Woordeboek;

public class AndroidWoordeboek implements Woordeboek {
	private final GetalGids boodskappe = new GetalGids();
	private final GetalGids etikette = new GetalGids();
	private final GetalGids bevele = new GetalGids();
	public AndroidWoordeboek(Leser leser, Joernaal joernaal) throws Exception {
		super();
		bouGids(leser, boodskappe, "boodskappe", "af");
		bouGids(leser, etikette, "etikette", "af");
		bouGids(leser, bevele, "bevele", "af");
	}

	private static void bouGids(Leser leser, GetalGids gids, String gidsNaam, String taalKode) throws Exception {
		String pad = "res:///woordeboek/" + gidsNaam;
		String inhoud = leser.lees(pad);
		String[] lyne = StringFunksies.verdeel(inhoud, "\r\n");
		bouFraseKaart(gids, lyne);
		pad = "res:///woordeboek/" + taalKode + "/" + gidsNaam;
		inhoud = leser.lees(pad);
		lyne = StringFunksies.verdeel(inhoud, "\r\n");
		bouFraseKaart(gids, lyne);
	}

	public static void bouFraseKaart(GetalGids frases, String[] lyne) {
		if (lyne == null)
			return;
		for (int lynTeller = 0; lynTeller < lyne.length; ++lynTeller) {
			String lyn = lyne[lynTeller];
			if (lyn == null)
				continue;
			String sleutelWoord = StringFunksies.veld(lyn, '=', 0);
			if (sleutelWoord == null)
				continue;
			String frase = StringFunksies.stert(
					lyn, '=', 1);
			if (frase == null)
				continue;
			frases.stoor(Integer.parseInt(sleutelWoord), frase);
		}
	}

	public String etiket(int kode) {
		return etikette.kry(kode);
	}

	@Override
	public String kry(Boodskap boodskap) {
		return boodskap.kry(boodskappe);
	}

	@Override
	public String kry(Etiket etiket) {
		return etiket.kry(etikette);
	}

	@Override
	public String kry(Bevel bevel) {
		return bevel.kry(bevele);
	}

}
