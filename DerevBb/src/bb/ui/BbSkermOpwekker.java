package bb.ui;

import i18n.Woordeboek;
import net.rim.device.api.ui.UiApplication;
import platform.Joernaal;
import platform.ui.SkermOpwekker;
import platform.ui.Skerm;

public class BbSkermOpwekker implements SkermOpwekker {
	private final UiApplication app;
	private final Woordeboek woordeboek;
	private final Joernaal joernaal;
	public BbSkermOpwekker(UiApplication app, Woordeboek woordeboek, Joernaal joernaal) {
		this.app = app;
		this.woordeboek = woordeboek;
		this.joernaal = joernaal;
	}
	public Skerm maak() {
		return new KonkreteSkerm(app, woordeboek, joernaal);
	}

}
