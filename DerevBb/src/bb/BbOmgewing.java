package bb;

import java.io.IOException;

import bb.ia.BbLeser;
import i18n.Woordeboek;
import platform.Joernaal;
import platform.Omgewing;

public class BbOmgewing implements Omgewing {
	private final Joernaal joernaal;
	private Woordeboek woordeboek;
	public BbOmgewing(long appGuid, String appNaam) throws IOException {
		joernaal = new BbJoernaal(appGuid, appNaam);
		woordeboek = new BbWoordeboek(new BbLeser(), joernaal);
	}
	public Joernaal kryJoernaal() {
		return joernaal;
	}
	public int kryBlaaiGrootte() {
		return 10;
	}
	public Woordeboek kryWoordeboek() {
		
		return woordeboek;
	}
	public boolean vergelykKasOnsensitief(String links, String regs) {
		return links.equalsIgnoreCase(regs);
	}

}
