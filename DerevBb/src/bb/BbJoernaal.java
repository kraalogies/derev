package bb;

import net.rim.device.api.system.EventLogger;
import platform.Joernaal;
import platform.roep.StringFunk;

public class BbJoernaal implements Joernaal {
	private final long guid;
	public BbJoernaal(long guid, String applikasieNaam) {
		this.guid = guid;
		EventLogger.register(guid, applikasieNaam, EventLogger.VIEWER_STRING);
		EventLogger.setMinimumLevel(EventLogger.INFORMATION);
	}
	public void fout(String teks) {
		System.out.println(teks);
		EventLogger.logEvent(guid, teks.getBytes(), EventLogger.ERROR);
	}

	public void fout(StringFunk teks) {
		fout(teks.roep());
	}

	public void info(String teks) {
		System.out.println(teks);
		EventLogger.logEvent(guid, teks.getBytes(), EventLogger.INFORMATION);
	}

	public void info(StringFunk teks) {
		info(teks.roep());
	}

}
