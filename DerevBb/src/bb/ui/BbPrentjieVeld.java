
package bb.ui;

import i18n.Bevel;
import i18n.Etiket;
import i18n.Woordeboek;

import java.io.IOException;
import java.io.InputStream;

import javax.microedition.media.Manager;
import javax.microedition.media.MediaException;
import javax.microedition.media.Player;
import javax.microedition.media.PlayerListener;
import javax.microedition.media.control.VideoControl;

import net.rim.device.api.system.Bitmap;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.MenuItem;
import net.rim.device.api.ui.Screen;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.component.BitmapField;
import net.rim.device.api.ui.component.Menu;
import net.rim.device.api.ui.container.MainScreen;
import platform.Joernaal;
import platform.Sein;
import platform.ia.Skema;
import platform.nuts.StringFunksies;
import platform.ui.Kontrole;
import platform.ui.PrentjieVeld;
import platform.ui.Skerm;
import bb.ia.BbLeser;

public class BbPrentjieVeld implements PrentjieVeld, Runnable {
	private byte[] data;
	private final Joernaal joernaal;
	private boolean isBesigMetVideo = false;
	private boolean isAktief;
	private boolean isSigbaar;
	private Bitmap prentjie;
	private final Skerm skerm;
	private Bevel fotoBevel;
	private Sein fotoBevelOpdrag;
	private final UiApplication app;
	private final BitmapField prentjieVeld;
	public BbPrentjieVeld(MainScreen vorm, UiApplication app, Woordeboek woordeboek, Skerm skerm, Joernaal joernaal, Etiket etiket) {
		this.app = app;
		this.joernaal = joernaal;
		this.isAktief = true;
		this.isSigbaar = true;
		this.skerm = skerm;
		prentjieVeld = new BitmapField();
		vorm.add(prentjieVeld);
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
		prentjieVeld.setBitmap(new Bitmap(0, 0));
		return this;
	}

	public Kontrole wys() {
		if (isSigbaar)
			return this;
		isSigbaar = true;
		if (prentjie == null)
			return this;
		prentjieVeld.setBitmap(prentjie);
		return this;
	}

	private static Bitmap maakPrentjie(byte[] data, Joernaal joernaal) throws Exception {
		try {
			return Bitmap.createBitmapFromBytes(data, 0, data.length, 1);
		} catch (Exception e) {
			joernaal.fout("Kan nie prentjie maak nie", e);
			throw e;
		}
	}
	public void stel(byte[] data) throws Exception {
		this.data = data;
		prentjie = maakPrentjie(data, joernaal);
		joernaal.info("Prentjie gestel");
		if (isSigbaar)
			prentjieVeld.setBitmap(prentjie);
	}
	public void stel(InputStream in) throws Exception {
		data = BbLeser.kryData(in);
		stel(data);
	}
	
	private Screen wysVideoOpVorm(final VideoControl kontrole, final String fotoFormaat) {
		final Field item = (Field) kontrole.initDisplayMode(VideoControl.USE_GUI_PRIMITIVE, "net.rim.device.api.ui.Field");
		final Screen videoSkerm = new MainScreen() {
			protected void makeMenu(Menu menu, int instance) {
				final Screen skerm = this;
				menu.add(new MenuItem("UIT", 0, 0) {
					public void run() {
						app.popScreen(skerm); 
					}});
				menu.add(new MenuItem("Neem foto", 1, 1) {

					public void run() {
						try {
							stel(kontrole.getSnapshot(fotoFormaat));
							app.popScreen(skerm);
						} catch (Exception e) {
							joernaal.fout("Kan nie foto neem", e);
						}
					}
					
				});
			}
		};
		videoSkerm.add(item);
		return videoSkerm;
	}
	private String kryVideoFormaat() {
//		String[] tipes = Manager.getSupportedContentTypes("capture");
//		for (int i = 0; i < tipes.length; ++i) {
//			joernaal.info(tipes[i]);
//			if (tipes[i].startsWith("video") || tipes[i].startsWith("image"))
//				return tipes[i];
//		}
//		String formaat = "video";
//		joernaal.info(woordeboek.kry(BoodskapPar1.videoFormaatNieBeskikbaar(formaat)));
//		return formaat;
		return "video";
	}
	public void run() {
		try {
			final Player speler = Manager.createPlayer("capture://" + kryVideoFormaat());
			speler.realize();
			final VideoControl kontrole = (VideoControl) speler.getControl("VideoControl");
			final Screen videoSeil = wysVideoOpVorm(kontrole, kryFotoFormaat());
			try {
				kontrole.setDisplayFullScreen(true);
			} catch (Exception e) {
				joernaal.fout("Kan nie volskerm stel nie", e);
			}
//			final Command neemFotoBevel = new Command(woordeboek.kry(fotoBevel), Command.SCREEN, 1);
//			final Command uit = new Command(woordeboek.kry(Bevel.Uit), Command.EXIT, 1);
//			videoSeil.setCommandListener(new CommandListener() {
//				public void commandAction(Command c, Displayable d) {
//					try {
//						if (c.getCommandType() != Command.EXIT)
//							stel(kontrole.getSnapshot(null));
//					} catch (Exception e) {
//						joernaal.fout("Kon nie foto neem nie", e);
//					} finally {
//						app.popScreen(videoSeil);
//						Display.getDisplay(mid).setCurrent(vorm);
//					}
//				}
//			});
//			Display.getDisplay(mid).setCurrent(videoSeil);
			app.invokeLater(new Runnable() {

				public void run() {
					app.pushScreen(videoSeil);
				}
				
			});
			
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

	private String kryFotoFormaat() {
		String[] formate = StringFunksies.verdeel(System.getProperty("video.snapshot.encodings"), " ");
		for (int i = 0; i < formate.length; ++i) {
			if (formate[i].indexOf("jpeg") >= 0)
				return formate[i];
		}
		return formate[0];
	}

//	private Displayable wysVideoOpSeil(VideoControl kontrole) {
//		Canvas seil = new Canvas() {
//			protected void paint(Graphics arg0) {
//			}
//		};
//		kontrole.initDisplayMode(VideoControl.USE_DIRECT_VIDEO, seil);
//		return seil;
//	}

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
		final BbPrentjieVeld hierdie = this;
		
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
