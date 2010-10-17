package android;

import java.io.IOException;

import i18n.Woordeboek;
import platform.Joernaal;
import platform.Omgewing;

public class AndroidOmgewing implements Omgewing {
	private final Joernaal joernaal;
	private final Woordeboek woordeboek;
	
	public AndroidOmgewing(Woordeboek woordeboek, Joernaal joernaal) {
		this.joernaal = joernaal;
		this.woordeboek = woordeboek;
	}
	@Override
	public Joernaal kryJoernaal() {
		return joernaal;
	}
	@Override
	public int kryBlaaiGrootte() {
		return 10;
	}
	@Override
	public Woordeboek kryWoordeboek() {
		return woordeboek;
	}
	@Override
	public boolean vergelykKasOnsensitief(String links, String regs) {
		return links.compareToIgnoreCase(regs) == 0;
	}

}
