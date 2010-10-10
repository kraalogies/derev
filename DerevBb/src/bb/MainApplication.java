package bb;

import platform.Omgewing;
import platform.ui.SkermOpwekker;
import bb.ui.BbSkermOpwekker;
import application.Klient;
import net.rim.device.api.ui.UiApplication;

public class MainApplication extends UiApplication {
	private final SkermOpwekker skermOpwekker;
	public static void main(String[] args) {
		MainApplication app = new MainApplication();
		app.enterEventDispatcher();
	}

	public MainApplication() {
		try {
			Omgewing omgewing = new BbOmgewing(0x2051fd67b72d11L, "DerevBb");
			skermOpwekker = new BbSkermOpwekker(this, omgewing.kryWoordeboek(), omgewing.kryJoernaal());
			System.out.println("Hallo BB");
			new Klient(skermOpwekker, omgewing);
		} catch (Exception e) {
			e.printStackTrace();
			throw new IllegalArgumentException();
		}
	}

}
