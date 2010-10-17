package platform;

import i18n.Woordeboek;

import java.io.InputStream;

public interface Omgewing {
	Joernaal kryJoernaal();
	boolean vergelykKasOnsensitief(String links, String regs);// sleutelNaam.equalsIgnoreCase(sleutels[sleutelPos]))
	int kryBlaaiGrootte();
	Woordeboek kryWoordeboek();
}
