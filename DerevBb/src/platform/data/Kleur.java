package platform.data;

public final class Kleur {
	private final int rooi;
	private final int groen;
	private final int blou;
	private Kleur(final int rooi, final int groen, final int blou) {
		this.rooi = rooi;
		this.groen = groen;
		this.blou = blou;
	}
	public static Kleur maakRgb(final int rooi, final int groen, final int blou) {
		return new Kleur(rooi, groen, blou);
	}
	public static Kleur ROOI = maakRgb(255, 0, 0);
	public int kryRooi() {
		return rooi;
	}
	public int kryGroen() {
		return groen;
	}
	public int kryBlou() {
		return blou;
	}
}
