package application;

import platform.Omgewing;
import platform.Sein;
import platform.ui.Merk;
import platform.ui.Teks;
import platform.ui.SkermOpwekker;
import platform.ui.Skerm;

public class Klient {
	private final Teks naam;
	private final Teks van;
	private final Merk isManlik;
	public Klient(SkermOpwekker skermOpwekker, final Omgewing omgewing) {
		Skerm venster = skermOpwekker.maak();
		naam = venster.voegbyTeks("Naam");
		van = venster.voegbyTeks("Van").stel("Malherbe");
		isManlik = venster.voegbyMerk("Manlik");
		van.deaktiveer();
		venster.voegbyBevel("Klik1", new Sein() {
			public void stuur() {
				String teks = naam.kry();
				omgewing.kryJoernaal().info("Teks:" + teks);
				naam.stel(teks + " - kkk");
				isManlik.merk(!isManlik.isGemerk());
			}
		});
		
		venster.voegbyBevel("Aktiveer", new Sein() {
			public void stuur() {
				naam.aktiveer();
				isManlik.aktiveer();
				omgewing.kryJoernaal().info("aktiveer");
			}
		});
		
		venster.voegbyBevel("Deaktiveer", new Sein() {
			public void stuur() {
				isManlik.deaktiveer();
				naam.deaktiveer();
			}
		});
		
		venster.voegbyBevel("Wys", new Sein() {
			public void stuur() {
				isManlik.wys();
				naam.wys();
			}
		});
		
		venster.voegbyBevel("Weg", new Sein() {
			public void stuur() {
				isManlik.weg();
				naam.weg();
			}
		});
		venster.wys();
	}

}
