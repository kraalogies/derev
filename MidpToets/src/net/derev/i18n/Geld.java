package net.derev.i18n;

import net.derev.nuts.StringFunksies;

public class Geld {
	private final String simbool;
	private final int desimaleFaktor;
	private final int desimale;
	private final String isoKode;
	public String getBeskrywing() {
		return beskrywing;
	}
	private final String beskrywing;
	public Geld(String simbool, int desimaleFaktor, int desimale, String isoKode, String beskrywing) {
		super();
		this.simbool = simbool;
		this.desimaleFaktor = desimaleFaktor;
		this.desimale = desimale;
		this.isoKode = isoKode;
		this.beskrywing = beskrywing;
	}
	public String getSimbool() {
		return simbool;
	}
	public String getIsoKode() {
		return isoKode;
	}
	public final static Geld ZAR = new Geld("R", 100, 2, "ZAR", "South African rand");
	public final static Geld USD = new Geld("$", 100, 2, "USD", "United States dollar");
	public final static Geld AUD = new Geld("$", 100, 2, "AUD", "Australian dollar");
	public final static Geld EUR = new Geld("€", 100, 2, "EUR", "Euro");
	public final static Geld BWP = new Geld("P", 100, 2, "BWP", "Botswana pula");
	public final static Geld BRL = new Geld("R$", 100, 2, "BRL", "Brazilian real");
	public final static Geld CAD = new Geld("$", 100, 2, "CAD", "Canadian dollar");
	public final static Geld CNY = new Geld("元", 100, 2, "CNY", "C	hinese yuan");
	public final static Geld DKK = new Geld("kr", 100, 2, "DKK", "Danish krone");
	public final static Geld INR = new Geld("Rs", 100, 2, "INR", "Indian rupee");
	public final static Geld GBP = new Geld("£", 100, 2, "GBP", "British pound");
	public final static Geld ILS = new Geld("₪", 100, 2, "ILS", "Israeli new sheqel");
	public final static Geld JPY = new Geld("¥", 100, 2, "JPY", "Japanese yen");
	public final static Geld TWD = new Geld("$", 100, 2, "TWD", "New Taiwan dollar");
	public final static Geld VND = new Geld("₫", 10, 1, "VND", "Vietnamese đồng");
	public String formateer(long bedrag, char duisende, char desimaal) {
		String teken = bedrag < 0 ? "-" : "";
		bedrag = Math.abs(bedrag);
		long regterkant = bedrag % desimaleFaktor;

		StringBuffer buffer = new StringBuffer(desimaal + StringFunksies.lvul(Long.toString(regterkant), '0', desimale));
		bedrag /= desimaleFaktor;
		
		while (bedrag >= 1000) {
			regterkant = bedrag % 1000;
			buffer.insert(0, duisende + StringFunksies.lvul(Long.toString(regterkant), '0', 3));
			bedrag /= 1000;
		}
		buffer.insert(0, simbool + " " + teken + Long.toString(bedrag));
		return buffer.toString();
	}
}
