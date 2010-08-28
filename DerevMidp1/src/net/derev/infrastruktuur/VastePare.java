package net.derev.infrastruktuur;

import java.util.Vector;

import platform.Omgewing;

import net.derev.nuts.StringFunksies;

public class VastePare {
	private final String[] sleutels;
	private final Object[] waardes;
	private final Omgewing omgewing;

	public static VastePare sorteer(Omgewing omgewing, String[] dubbels, boolean vanKleinNaGroot) {
		if (dubbels == null || dubbels.length < 2)
			return null;
		int aantalPare = dubbels.length / 2;
		Paar[] pare = new Paar[aantalPare];
		for (int paarPos = 0; paarPos < aantalPare; ++paarPos) {
			pare[paarPos] = new Paar(dubbels[paarPos*2], dubbels[paarPos*2 + 1]);
		}
		StringFunksies.quicksort(pare, 0, pare.length - 1, pare[0]);
		return new VastePare(omgewing, pare, vanKleinNaGroot);
	}
	public VastePare(Omgewing omgewing, Paar[] pare, boolean vanKleinNaGroot) {
		this.omgewing = omgewing;
		sleutels = new String[pare.length];
		waardes = new Object[pare.length];
		if (vanKleinNaGroot)
			for (int paarPos = 0; paarPos < pare.length; ++paarPos) {
				sleutels[paarPos] = pare[paarPos].geeNaam();
				waardes[paarPos] = pare[paarPos].geeWaarde();
			}
		else
			for (int paarPos = 0; paarPos < pare.length; ++paarPos) {
				sleutels[pare.length - 1 - paarPos] = pare[paarPos].geeNaam();
				waardes[pare.length - 1 - paarPos] = pare[paarPos].geeWaarde();
			}

	}
	public VastePare(Omgewing omgewing, String sleutel, Object waarde) {
		this(omgewing, new String[]{sleutel}, new Object[]{waarde});
	}
	
	public VastePare(Omgewing omgewing, String[] sleutels, Object[] waardes) {
		super();
		this.omgewing = omgewing;
		if (sleutels == null || waardes == null || sleutels.length != waardes.length)
			throw new IllegalArgumentException();
		this.sleutels = sleutels;
		this.waardes = waardes;
	}

	public VastePare(Omgewing omgewing, Vector dubbels) {
		this.omgewing = omgewing;
		int aantalPare = dubbels.size() / 2;
		sleutels = new String[aantalPare];
		waardes = new Object[aantalPare];
		for (int paarPos = 0; paarPos < aantalPare; ++paarPos) {
			sleutels[paarPos] = (String) dubbels.elementAt(paarPos*2);
			waardes[paarPos] = dubbels.elementAt(paarPos*2 + 1);
		}
	}

	public VastePare(Omgewing omgewing, String[] dubbels) {
		this.omgewing = omgewing;
		int aantalPare = dubbels.length / 2;
		sleutels = new String[aantalPare];
		waardes = new Object[aantalPare];
		for (int paarPos = 0; paarPos < aantalPare; ++paarPos) {
			sleutels[paarPos] = dubbels[paarPos*2];
			waardes[paarPos] = dubbels[paarPos*2 + 1];
		}
	}

	public int geeLengte() {
		return sleutels.length;
	}
	
	private boolean isOngeldig(int posisie) {
		return posisie < 0 || posisie >= sleutels.length;
	}

	public String geeSleutel(int posisie) {
		if (isOngeldig(posisie)) 
			return null;
		return sleutels[posisie];
	}
	
	public Object geeWaarde(int posisie) {
		if (isOngeldig(posisie)) 
			return null;
		return waardes[posisie];
	}
	
	public Object[] geeWaardes() {
		return waardes;
	}

	public boolean pasSleutel(int posisie, String naam) {
		if (isOngeldig(posisie))
			return false;
		String sleutel = sleutels[posisie];
		if (sleutel == null || naam == null)
			return false;
		return sleutel.equals(naam);
	}

	public Object soekWaarde(String sleutelNaam) {
		if (sleutelNaam == null)
			return null;
		for (int sleutelPos = 0; sleutelPos < sleutels.length; ++sleutelPos) {
			if (omgewing.vergelykKasOnsensitief(sleutelNaam, sleutels[sleutelPos]))
				return waardes[sleutelPos];
		}
		return null;
	}
	
	public boolean equals(VastePare pare) {
		if (pare == null)
			return false;
		if (pare == this)
			return true;
		if (waardes.length != pare.geeLengte())
			return false;
		for (int waardePos = 0; waardePos < waardes.length; ++waardePos) {
			if (!sleutels[waardePos].equals(pare.geeSleutel(waardePos)))
				return false;
			Object anderWaarde = pare.geeWaarde(waardePos);
			Object hierdieWaarde = waardes[waardePos];
			if (anderWaarde == hierdieWaarde)
				continue;
			if (anderWaarde == null || hierdieWaarde == null)
				return false;
			if (!anderWaarde.equals(hierdieWaarde))
				return false;
		}
		return true;
	}
	
	public boolean equals(Object objek) {
		if (objek == null)
			return false;
		if (objek == this)
			return true;
		try {
			VastePare pare = (VastePare) objek;
			return equals(pare);
		} catch (Throwable t) {
			if (waardes.length != 1) // As die tipe nie dieselfde is nie het ons nog een kans om gelyk te toes, nl. as daar net een lokale waarde is 
				return false;
			Object hierdieWaarde = waardes[0];
			if (hierdieWaarde == objek)
				return true;
			if (hierdieWaarde == null)
				return false;
			return hierdieWaarde.equals(objek);
		}
	}
	
}
