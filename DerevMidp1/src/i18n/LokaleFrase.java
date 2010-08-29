package i18n;

import platform.data.GetalGids;

public class LokaleFrase {
	private final int indeks;

	public LokaleFrase(int indeks) {
		super();
		this.indeks = indeks;
	}
	
	public String kry(GetalGids gids) {
		return gids.kry(indeks);
	}
}
