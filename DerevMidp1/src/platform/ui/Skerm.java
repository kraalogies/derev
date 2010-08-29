package platform.ui;

import i18n.Bevel;
import i18n.Etiket;
import platform.Sein;

public interface Skerm {

	Teks voegbyTeks(Etiket etiket);

	void voegbyBevel(Bevel bevel, Sein sein);

	void wys();

	Merk voegbyMerk(Etiket etiket);

	PrentjieVeld voegbyPrentjie(Etiket etiket);

}
