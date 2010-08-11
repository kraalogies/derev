package midp1;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

import platform.data.Kleur;
import platform.data.Prentjie;

public class Midp1Prentjie implements Prentjie {
	private final Image prentjie;
	public Midp1Prentjie(int wydte, int hoogte, Kleur kleur) {
		prentjie = Image.createImage(wydte, hoogte);
		Graphics g = prentjie.getGraphics();
		g.setColor(kleur.kryRooi(), kleur.kryBlou(), kleur.kryGroen());
		g.fillRect(0, 0, wydte, hoogte);
	}

}
