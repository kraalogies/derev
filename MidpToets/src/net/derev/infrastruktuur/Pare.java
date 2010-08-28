package net.derev.infrastruktuur;

import java.util.Vector;

import platform.Omgewing;

public class Pare {
	private final Vector sleutels = new Vector();
	private final Vector waardes = new Vector();

	public Pare(VastePare vastePare) {
		for (int sleutelPos = 0; sleutelPos < vastePare.geeLengte(); ++sleutelPos) {
			voegby(vastePare.geeSleutel(sleutelPos), vastePare.geeWaarde(sleutelPos));
		}
	}

	public Pare() {
	}

	public void voegby(String sleutel, Object waarde) {
		if (sleutel == null)
			throw new NullPointerException();
		sleutels.addElement(sleutel);
		waardes.addElement(waarde);
	}

	public Object[] vriesWaardes() {
		if (sleutels.size() == 0)
			return null;
		Object[] nuweWaardes = new Object[waardes.size()];
		waardes.copyInto(nuweWaardes);
		return nuweWaardes;
	}
	
	public String[] vriesSleutels() {
		if (sleutels.size() == 0)
			return null;
		String[] nuweSleutels = new String[sleutels.size()];
		sleutels.copyInto(nuweSleutels);
		return nuweSleutels;
	}
	
	public VastePare vries(Omgewing omgewing) {
		String[] nuweSleutels = vriesSleutels();
		if (nuweSleutels == null)
			return null;
		
		Object[] nuweWaardes = vriesWaardes();
		return new VastePare(omgewing, nuweSleutels, nuweWaardes);
	}

	public void veeEersteUit(String sleutelNaam) {
		for (int sleutelPos = 0; sleutelPos < sleutels.size(); ++sleutelPos) {
			if (sleutelNaam.equals(sleutels.elementAt(sleutelPos))) {
				sleutels.removeElementAt(sleutelPos);
				waardes.removeElementAt(sleutelPos);
				return;
			}
		}
	}

}
