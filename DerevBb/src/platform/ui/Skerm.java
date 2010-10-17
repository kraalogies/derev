package platform.ui;

import i18n.Bevel;
import i18n.Etiket;
import platform.Sein;

public interface Skerm extends ProtoSkerm {
	Teks voegbyTeks(Etiket etiket);
	Merk voegbyMerk(Etiket etiket);
	PrentjieVeld voegbyPrentjie(Etiket etiket);
}
