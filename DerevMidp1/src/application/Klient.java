package application;

import i18n.Bevel;
import i18n.Etiket;
import i18n.Woordeboek;

import platform.Omgewing;
import platform.Sein;
import platform.ui.Merk;
import platform.ui.PrentjieVeld;
import platform.ui.Teks;
import platform.ui.SkermOpwekker;
import platform.ui.Skerm;

public class Klient {
	private final Teks naam;
	private final Teks van;
	private final Merk isManlik;
	private final PrentjieVeld foto;
	public Klient(SkermOpwekker skermOpwekker, final Omgewing omgewing) {
		Skerm venster = skermOpwekker.maak();
		Woordeboek w = omgewing.kryWoordeboek();
		naam = venster.voegbyTeks(Etiket.Naam);
		van = venster.voegbyTeks(Etiket.Van).stel("Malherbe");
		isManlik = venster.voegbyMerk(Etiket.Manlik);
		foto = venster.voegbyPrentjie(Etiket.Foto).lees("res:///abort.png").laatFotoNeem(Bevel.NeemFoto);
		
		van.deaktiveer();
		venster.voegbyBevel(Bevel.Klik, new Sein() {
			public void stuur() {
				String teks = naam.kry();
				omgewing.kryJoernaal().info("Teks:" + teks);
				naam.stel(teks + " - kkk");
				isManlik.merk(!isManlik.isGemerk());
			}
		});
		
		venster.voegbyBevel(Bevel.Aktiveer, new Sein() {
			public void stuur() {
				naam.aktiveer();
				isManlik.aktiveer();
				foto.aktiveer();
				omgewing.kryJoernaal().info("aktiveer");
			}
		});
		
		venster.voegbyBevel(Bevel.Deaktiveer, new Sein() {
			public void stuur() {
				isManlik.deaktiveer();
				naam.deaktiveer();
				foto.deaktiveer();
			}
		});
		
		venster.voegbyBevel(Bevel.Wys, new Sein() {
			public void stuur() {
				isManlik.wys();
				naam.wys();
				foto.wys();
			}
		});
		
		venster.voegbyBevel(Bevel.Steekweg, new Sein() {
			public void stuur() {
				isManlik.weg();
				naam.weg();
				foto.weg();
			}
		});
		venster.wys();
	}

}
