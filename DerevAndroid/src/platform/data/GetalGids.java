package platform.data;

import java.util.Hashtable;

public class GetalGids {
	private final Hashtable gids = new Hashtable();
	public void stoor(int sleutel, String waarde) {
		gids.put(new Integer(sleutel), waarde);
	}
	public String kry(int sleutel) {
		Integer toetsSleutel = new Integer(sleutel);
		if (!gids.containsKey(toetsSleutel))
			return null;
		return (String) gids.get(toetsSleutel);
	}
}
