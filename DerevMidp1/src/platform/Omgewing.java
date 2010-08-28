package platform;

import i18n.Woordeboek;

import java.io.InputStream;

public interface Omgewing {
	Joernaal kryJoernaal();
	InputStream kryLokalePrentjie(String naam) throws Exception;
	boolean vergelykKasOnsensitief(String links, String regs);// sleutelNaam.equalsIgnoreCase(sleutels[sleutelPos]))
	int kryBlaaiGrootte();
	Woordeboek kryWoordeboek();
}
