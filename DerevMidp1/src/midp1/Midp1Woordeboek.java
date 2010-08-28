package midp1;

import java.io.IOException;
import java.util.Hashtable;

import platform.Joernaal;
import platform.data.GetalGids;
import platform.ia.Leser;
import platform.nuts.StringFunksies;

import i18n.Woordeboek;

public class Midp1Woordeboek implements Woordeboek {
	private final GetalGids foute = new GetalGids();
	private final GetalGids etikette = new GetalGids();
	public Midp1Woordeboek(Leser leser, Joernaal joernaal) throws IOException {
		super();
		bouGids(leser, foute, "foute", "af");
		bouGids(leser, etikette, "etikette", "af");
	}

	private static void bouGids(Leser leser, GetalGids gids, String gidsNaam, String taalKode) throws IOException {
		String pad = "jar:///woordeboek/" + gidsNaam;
		String inhoud = leser.lees(pad);
		String[] lyne = StringFunksies.verdeel(inhoud, "\r\n");
		bouFraseKaart(gids, lyne);
		pad = "jar:///woordeboek/" + taalKode + "/" + gidsNaam;
		inhoud = leser.lees(pad);
		lyne = StringFunksies.verdeel(inhoud, "\r\n");
		bouFraseKaart(gids, lyne);
	}

	public String fout(int kode) {
		return foute.kry(kode);
	}

	public String fout(int kode, String parameter) {
		return StringFunksies.formatteer(fout(kode), new String[]{parameter});
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

}
