package application;

import java.io.IOException;
import java.io.InputStream;

import platform.Omgewing;
import platform.Sein;
import platform.data.Kleur;
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
		naam = venster.voegbyTeks("Naam");
		van = venster.voegbyTeks("Van").stel("Malherbe");
		isManlik = venster.voegbyMerk("Manlik");
		foto = venster.voegbyPrentjie("Foto");
		final InputStream in = omgewing.kryLokalePrentjie("/abort.png");
		try {
			foto.stel(in);
		} catch (IOException e) {
			omgewing.kryJoernaal().fout(e.toString());
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				omgewing.kryJoernaal().fout(e.toString());
			}
		}
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
				foto.aktiveer();
				omgewing.kryJoernaal().info("aktiveer");
			}
		});
		
		venster.voegbyBevel("Deaktiveer", new Sein() {
			public void stuur() {
				isManlik.deaktiveer();
				naam.deaktiveer();
				foto.deaktiveer();
			}
		});
		
		venster.voegbyBevel("Wys", new Sein() {
			public void stuur() {
				isManlik.wys();
				naam.wys();
				foto.wys();
			}
		});
		
		venster.voegbyBevel("Weg", new Sein() {
			public void stuur() {
				isManlik.weg();
				naam.weg();
				foto.weg();
			}
		});
		venster.wys();
	}

}
