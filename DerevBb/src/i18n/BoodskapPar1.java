package i18n;

import platform.data.GetalGids;
import platform.nuts.StringFunksies;

public class BoodskapPar1 extends Boodskap {
	public static BoodskapPar1 videoFormaatNieBeskikbaar(String formaat) {
		return new BoodskapPar1(1, formaat);
	}
	private final String par1;
	public BoodskapPar1(int indeks, String par1) {
		super(indeks);
		this.par1 = par1;
	}

	public String kry(GetalGids gids) {
		String boodskap = super.kry(gids);
		return StringFunksies.formatteer(boodskap, new String[]{par1});
	}
}
