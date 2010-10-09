
package midp1.ui;

import i18n.Bevel;
import i18n.Etiket;
import i18n.BoodskapPar1;
import i18n.Woordeboek;

import java.io.IOException;
import java.io.InputStream;

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.ImageItem;
import javax.microedition.lcdui.Item;
import javax.microedition.lcdui.StringItem;
import javax.microedition.media.Manager;
import javax.microedition.media.Player;
import javax.microedition.media.PlayerListener;
import javax.microedition.media.control.VideoControl;
import javax.microedition.midlet.MIDlet;

import midp1.ia.Midp1Leser;
import platform.Joernaal;
import platform.Sein;
import platform.ia.Skema;
import platform.ui.Kontrole;
import platform.ui.PrentjieVeld;
import platform.ui.Skerm;

public class Midp1PrentjieVeld implements PrentjieVeld, Runnable {
	private final Form vorm;
	private byte[] data;
	private final Joernaal joernaal;
	private boolean isBesigMetVideo = false;
	private final MIDlet mid;
	private final Woordeboek woordeboek;
	private boolean isAktief;
	private boolean isSigbaar;
	private Image prentjie;
	private int plek;
	private final Skerm skerm;
	private Bevel fotoBevel;
	private Sein fotoBevelOpdrag;
	public Midp1PrentjieVeld(Woordeboek woordeboek, final MIDlet mid, Skerm skerm, Joernaal joernaal, Form vorm, Etiket etiket) {
		this.woordeboek = woordeboek;
		this.mid = mid;
		this.vorm = vorm;
		this.joernaal = joernaal;
		this.isAktief = true;
		this.isSigbaar = true;
		plek = vorm.append("");
		this.skerm = skerm;
	}

	public Kontrole aktiveer() {
		if (isAktief)
			return this;
		isAktief = true;
		if (fotoBevel != null && fotoBevelOpdrag != null)
			skerm.voegbyBevel(fotoBevel, fotoBevelOpdrag);
		return this;
	}

	public Kontrole deaktiveer() {
		if (!isAktief)
			return this;
		isAktief = false;
		if (fotoBevel != null)
			skerm.verwyderBevel(fotoBevel);
		return this;
	}

	public Kontrole weg() {
		if (!isSigbaar)
			return this;
		isSigbaar = false;
		if (prentjie == null)
			return this;
		vorm.set(plek, new StringItem(null, null));
		return this;
	}

	public Kontrole wys() {
		if (isSigbaar)
			return this;
		isSigbaar = true;
		if (prentjie == null)
			return this;
		vorm.set(plek, new ImageItem(null, prentjie, 0, null));
		return this;
	}

	public byte[] kry() {
		return data;
	}

	private static Image maakPrentjie(byte[] data, Joernaal joernaal) throws Exception {
		try {
			return Image.createImage(data, 0, data.length - 1);
		} catch (Exception e) {
			joernaal.fout("Kan nie prentjie maak nie", e);
			throw e;
		}
	}
	public void stel(byte[] data) throws Exception {
		this.data = data;
		prentjie = maakPrentjie(data, joernaal);
		if (isSigbaar)
			vorm.set(plek, new ImageItem(null, prentjie, 0, null));
	}
	public void stel(InputStream in) throws Exception {
		data = Midp1Leser.kryData(in);
		stel(data);
	}
	
	private Displayable wysVideoOpVorm(final VideoControl kontrole) {
		final Item item = (Item) kontrole.initDisplayMode(VideoControl.USE_DIRECT_VIDEO, null);
		final Form videoVorm = new Form("Foto");
		videoVorm.append(item);
		return videoVorm;
	}
	private String kryVideoFormaat() {
		String[] tipes = Manager.getSupportedContentTypes("capture");
		for (int i = 0; i < tipes.length; ++i) {
			joernaal.info(tipes[i]);
			if (tipes[i].startsWith("video") || tipes[i].startsWith("image"))
				return tipes[i];
		}
		String formaat = "video";
		joernaal.info(woordeboek.kry(BoodskapPar1.videoFormaatNieBeskikbaar(formaat)));
		return formaat;
	}
	public void run() {
		try {
			final Player speler = Manager.createPlayer("capture://" + kryVideoFormaat());
			speler.realize();
			final VideoControl kontrole = (VideoControl) speler.getControl("VideoControl");
			Displayable videoSeil = wysVideoOpSeil(kontrole);
			try {
				kontrole.setDisplayFullScreen(true);
			} catch (Exception e) {
				joernaal.fout("Kan nie volskerm stel nie", e);
			}
			final Command neemFotoBevel = new Command(woordeboek.kry(fotoBevel), Command.SCREEN, 1);
			final Command uit = new Command(woordeboek.kry(Bevel.Uit), Command.EXIT, 1);
			videoSeil.addCommand(neemFotoBevel);
			videoSeil.addCommand(uit);
			videoSeil.setCommandListener(new CommandListener() {
				public void commandAction(Command c, Displayable d) {
					try {
						if (c.getCommandType() != Command.EXIT)
							stel(kontrole.getSnapshot(null));
					} catch (Exception e) {
						joernaal.fout("Kon nie foto neem nie", e);
					} finally {
						Display.getDisplay(mid).setCurrent(vorm);
					}
				}
			});
			Display.getDisplay(mid).setCurrent(videoSeil);
			
			speler.addPlayerListener(new PlayerListener() {

				public void playerUpdate(Player player, String event,
						Object eventData) {
					joernaal.info(event);
				}
				
			});
			kontrole.setVisible(true);
			speler.start();
		} catch (Exception e) {
			joernaal.fout(e);
		} finally {
			isBesigMetVideo = false;
		}
	}

	private Displayable wysVideoOpSeil(VideoControl kontrole) {
		Canvas seil = new Canvas() {
			protected void paint(Graphics arg0) {
			}
		};
		kontrole.initDisplayMode(VideoControl.USE_DIRECT_VIDEO, seil);
		return seil;
	}

	private InputStream kryStroom(String uri) {
		if (Skema.Intern.Pas(uri))
			return getClass().getResourceAsStream(Skema.Intern.KryPad(uri));
		throw new IllegalArgumentException();
	}
	public PrentjieVeld lees(String uri) {
		try {
			final InputStream in = kryStroom(uri);
			try {
				stel(in);
			} catch (IOException e) {
				joernaal.fout("Kon nie foto stel nie", e);
			} finally {
				try {
					in.close();
				} catch (IOException e) {
					joernaal.fout("Kon nie foto stroom toemaak nie", e);
				}
			}
			
		} catch (Exception e) {
			joernaal.fout("Kon nie foto lees nie", e);
		}
		return this;
	}

	public PrentjieVeld laatFotoNeem(Bevel fotoBevel) {
		this.fotoBevel = fotoBevel;
		final Midp1PrentjieVeld hierdie = this;
		
		fotoBevelOpdrag = new Sein() {
			public void stuur() {
				synchronized(this) {
					if (isBesigMetVideo)
						return;
					isBesigMetVideo = true;
					try {
						Thread t = new Thread(hierdie);
						t.start();
					} catch (Exception e) {
						hierdie.joernaal.fout(e);
						isBesigMetVideo = false;
					}
				}
			}
		};
		if (isAktief)
			skerm.voegbyBevel(fotoBevel, fotoBevelOpdrag);
		return this;
	}

}
