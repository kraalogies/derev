package midp1.ui;

import java.io.ByteArrayOutputStream;
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
import javax.microedition.lcdui.Item;
import javax.microedition.media.Manager;
import javax.microedition.media.Player;
import javax.microedition.media.PlayerListener;
import javax.microedition.media.control.VideoControl;
import javax.microedition.midlet.MIDlet;

import platform.Joernaal;
import platform.Sein;
import platform.ui.Kontrole;
import platform.ui.PrentjieVeld;
import platform.ui.Skerm;

public class Midp1PrentjieVeld implements PrentjieVeld, Runnable {
	private final Form vorm;
	private byte[] data;
	private final Joernaal joernaal;
	private boolean isBesigMetVideo = false;
	private final MIDlet mid;
	public Midp1PrentjieVeld(final MIDlet mid, Skerm skerm, Joernaal joernaal, Form vorm, String etiket) {
		this.mid = mid;
		this.vorm = vorm;
		this.joernaal = joernaal;
		final Midp1PrentjieVeld hierdie = this;
		skerm.voegbyBevel("Foto", new Sein() {
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
		});
	}

	public Kontrole aktiveer() {
		// TODO Auto-generated method stub
		return this;
	}

	public Kontrole deaktiveer() {
		// TODO Auto-generated method stub
		return this;
	}

	public Kontrole weg() {
		// TODO Auto-generated method stub
		return this;
	}

	public Kontrole wys() {
		// TODO Auto-generated method stub
		return null;
	}

	public byte[] kry() {
		return data;
	}

	public byte[] lees(InputStream in) throws IOException {
		final ByteArrayOutputStream uit = new ByteArrayOutputStream();
		try {
			byte[] buffer = new byte[1024];
			for (int n; (n = in.read(buffer)) > 0;)
				uit.write(buffer, 0, n);
			uit.flush();
			return uit.toByteArray();
		} finally {
			uit.close();
		}
	}
	private static Image maakPrentjie(byte[] data, Joernaal joernaal) throws Exception {
		try {
			return Image.createImage(data, 0, data.length - 1);
		} catch (Exception e) {
			joernaal.fout("Kan nie prentjie maak nie", e);
			throw e;
		}
	}
	public PrentjieVeld stel(byte[] data) throws Exception {
		this.data = data;
		vorm.append(maakPrentjie(data, joernaal));
		return this;
	}
	public PrentjieVeld stel(InputStream in) throws Exception {
		data = lees(in);
		return stel(data);
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
		joernaal.info("Kan nie formaat kry nie, vat 'video'");
		return "video";
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
			final Command neemFotoBevel = new Command("Neem foto", Command.SCREEN, 1);
			videoSeil.addCommand(neemFotoBevel);
			videoSeil.setCommandListener(new CommandListener() {
				public void commandAction(Command c, Displayable d) {
					try {
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

}
