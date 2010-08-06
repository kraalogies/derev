package bb;

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
		skermOpwekker = new BbSkermOpwekker(this);
		System.out.println("Hallo BB");
		new Klient(skermOpwekker, new BbOmgewing(0x2051fd67b72d11L, "DerevBb"));
	}

}
