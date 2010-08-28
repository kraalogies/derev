package standardbank;

import net.derev.i18n.Geld;
import net.derev.nuts.StringFunksies;

public class Balans {
	private final long balans;
	private final long beskikbaar;
	private final String rekeningNaam;
	private final String rekeningNommer;
	private final String rekeningTipe;
	public Balans(long balans, long beskikbaar, String rekeningNaam,
			String rekeningNommer, String rekeningTipe) {
		super();
		this.balans = balans;
		this.beskikbaar = beskikbaar;
		this.rekeningNaam = rekeningNaam;
		this.rekeningNommer = rekeningNommer;
		this.rekeningTipe = rekeningTipe;
	}
	public long geeBalans() {
		return balans;
	}
	public long geeBeskikbaar() {
		return beskikbaar;
	}
	public String geeRekeningNaam() {
		return rekeningNaam;
	}
	public String geeRekeningNommer() {
		return rekeningNommer;
	}
	public String geeRekeningTipe() {
		return rekeningTipe;
	}
	public String toString() {
		return StringFunksies.formatteer("{0} {1}", rekeningNaam, Geld.JPY.formateer(balans, ',', '.'));
	}
}
