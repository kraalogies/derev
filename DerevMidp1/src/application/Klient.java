package application;

import platform.Sein;
import platform.ui.Invoer;
import platform.ui.SkermOpwekker;
import platform.ui.Skerm;

public class Klient {
	private final Invoer naam;
	private final Invoer van;
	public Klient(SkermOpwekker skermOpwekker) {
		Skerm venster = skermOpwekker.maak();
		naam = venster.voegbyInvoer("Naam: ");
		van = venster.voegbyInvoer("Van: ");
		venster.voegbyBevel("Klik1", new Sein() {
			public void stuur() {
				String teks = naam.kryTeks();
				System.out.println("Teks:" + teks);
				naam.stelTeks(teks + " - kkk");
			}
		});
		
		venster.voegbyBevel("nog", new Sein() {
			public void stuur() {
				naam.stelTeks("x");
				van.stelTeks("y");
			}
		});
		
		venster.wys();
	}

}
