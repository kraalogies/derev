package application;

import platform.Sein;
import platform.ui.Teks;
import platform.ui.SkermOpwekker;
import platform.ui.Skerm;

public class Klient {
	private final Teks naam;
	private final Teks van;
	public Klient(SkermOpwekker skermOpwekker) {
		Skerm venster = skermOpwekker.maak();
		naam = venster.voegbyTeks("Naam: ");
		van = venster.voegbyTeks("Van: ").stel("Malherbe");
		van.deaktiveer();
		venster.voegbyBevel("Klik1", new Sein() {
			public void stuur() {
				String teks = naam.kry();
				System.out.println("Teks:" + teks);
				naam.stel(teks + " - kkk");
			}
		});
		
		venster.voegbyBevel("Aktiveer", new Sein() {
			public void stuur() {
				naam.aktiveer();
			}
		});
		
		venster.voegbyBevel("Deaktiveer", new Sein() {
			public void stuur() {
				naam.deaktiveer();
			}
		});
		
		venster.voegbyBevel("Wys", new Sein() {
			public void stuur() {
				naam.wys();
			}
		});
		
		venster.voegbyBevel("Weg", new Sein() {
			public void stuur() {
				naam.weg();
			}
		});
		venster.wys();
	}

}
