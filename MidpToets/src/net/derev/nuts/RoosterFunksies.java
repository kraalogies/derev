package net.derev.nuts;

public class RoosterFunksies {
	public static boolean vergelyk(Object[] hierdieWaardes, Object[] waardes) {
		if (hierdieWaardes == waardes)
			return true;
		if (hierdieWaardes == null || waardes == null) 
			return false;
		if (waardes.length != hierdieWaardes.length) 
			return false;
		for (int waardePos = 0; waardePos < waardes.length; ++ waardePos) {
			Object hierdieWaarde = hierdieWaardes[waardePos];
			Object waarde = waardes[waardePos];
			if (waarde == hierdieWaarde)
				continue;
			if (waarde == null || hierdieWaarde == null)
				return false;
			if (!waarde.equals(hierdieWaarde))
				return false;
		}
		return true;
	}
}
