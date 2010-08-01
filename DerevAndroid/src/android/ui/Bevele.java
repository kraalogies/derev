package android.ui;

import java.util.Hashtable;

import platform.Dubbel;
import platform.Sein;

public class Bevele {
	private final Hashtable<Integer, Dubbel>  bevele = new Hashtable<Integer, Dubbel>();
	private int laasteIndeks = 0;
	public void voegby(String naam, Sein sein) {
		bevele.put(laasteIndeks, new Dubbel(naam, sein));
		++laasteIndeks;
	}
	public int kryAantal() {
		return bevele.size();
	}
	public String kryNaam(int indeks) {
		Dubbel paar = bevele.get(indeks);
		return (String) paar.kryEerste();
	}
	public Sein krySein(int indeks) {
		Dubbel paar = bevele.get(indeks);
		return (Sein) paar.kryTweede();
	}

	
}
