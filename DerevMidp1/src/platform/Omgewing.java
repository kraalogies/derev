package platform;

import java.io.InputStream;

public interface Omgewing {
	Joernaal kryJoernaal();
	InputStream kryLokalePrentjie(String naam) throws Exception;
	boolean vergelykKasOnsensitief(String links, String regs);// sleutelNaam.equalsIgnoreCase(sleutels[sleutelPos]))
	int kryBlaaiGrootte();
}
