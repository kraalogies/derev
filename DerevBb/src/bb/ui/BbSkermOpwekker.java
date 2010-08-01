package bb.ui;

import net.rim.device.api.ui.UiApplication;
import platform.ui.SkermOpwekker;
import platform.ui.Skerm;

public class BbSkermOpwekker implements SkermOpwekker {
	private final UiApplication app;
	public BbSkermOpwekker(UiApplication app) {
		this.app = app;
	}
	public Skerm maak() {
		return new KonkreteSkerm(app);
	}

}
