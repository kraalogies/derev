package net.derev.model;

import net.derev.nuts.GetalleFunksies;

public class Veld {
	public static final int TIPE_STRING = 1;
	public static final int TIPE_HEELGETAL = 2;
	public static final int TIPE_SYFER = 3;
	private final String naam;
	private final int tipe;
	private final String beskrywing;
	private final int maksLengte;
	private final int minLengte;
	private final Object ondergrens;
	private final Object bogrens;
	private final boolean sensitief;
	private final boolean wagwoord;
	
	private Veld(
			String naam, 
			String beskrywing, 
			int tipe, 
			int minLengte, 
			int maksLengte, 
			Object ondergrens, 
			Object bogrens,
			boolean sensitief,
			boolean wagwoord) {
		this.naam = naam;
		this.beskrywing = beskrywing;
		this.tipe = tipe;
		this.minLengte = minLengte;
		this.maksLengte = maksLengte;
		this.ondergrens = ondergrens;
		this.bogrens = bogrens;
		this.sensitief = sensitief;
		this.wagwoord = wagwoord;
	}
	public static Veld maakWagwoordVeld(String naam, String beskrywing, int minLengte, int maksLengte) {
		return new Veld(naam, beskrywing, TIPE_STRING, minLengte, maksLengte, null, null, false, true);
	}
	public static Veld maakSyferVeld(String naam, String beskrywing, int lengte, boolean sensitief) {
		return new Veld(naam, beskrywing, TIPE_SYFER, lengte, lengte, null, null, sensitief, false);
	}
	public static Veld maakPinVeld(String naam, String beskrywing, int lengte) {
		return new Veld(naam, beskrywing, TIPE_SYFER, lengte, lengte, null, null, false, true);
	}
	public static Veld maakStringVeld(String naam, String beskrywing, int minLengte, int maksLengte, boolean sensitief) {
		return new Veld(naam, beskrywing, TIPE_STRING, minLengte, maksLengte, null, null, sensitief, false);
	}
	public static Veld maakGetalVeld(String naam, String beskrywing, boolean verpligtend, int ondergrens, int bogrens) {
		int aantalSyfers = Math.max(GetalleFunksies.kryAantalKarakters(bogrens), GetalleFunksies.kryAantalKarakters(ondergrens));
		// Tel een by vir die teken
		return new Veld(naam, beskrywing, TIPE_HEELGETAL, verpligtend ? 1 : 0, aantalSyfers, new Integer(ondergrens), new Integer(bogrens), false, false);
	}
	public int geeTipe() {
		return tipe;
	}
	public String geeNaam() {
		return naam;
	}
	public String geeBeskrywing() {
		return beskrywing;
	}
	public int geeMaksLengte() {
		return maksLengte;
	}
	public Object geeOndergrens() {
		return ondergrens;
	}
	public Object geeBogrens() {
		return bogrens;
	}
	public int geeMinLengte() {
		return minLengte;
	}
	public boolean isSensitief() {
		return sensitief;
	}
	public boolean isWagwoord() {
		return wagwoord;
	}
}
