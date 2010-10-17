package platform.ui;

import i18n.Bevel;


public interface PrentjieVeld extends Kontrole {
	PrentjieVeld lees(String uri);
	PrentjieVeld laatFotoNeem(Bevel fotoBevel);
}
