package platform.ui;

import java.io.IOException;
import java.io.InputStream;

public interface PrentjieVeld extends Kontrole {
	byte[] kry();
	PrentjieVeld stel(InputStream in) throws Exception;
}
