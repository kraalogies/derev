package standardbank.model;

import java.io.IOException;

import javax.microedition.rms.RecordStoreException;
import javax.microedition.rms.RecordStoreFullException;
import javax.microedition.rms.RecordStoreNotFoundException;

import net.derev.infrastruktuur.Rms;
import net.derev.model.MyProfiel;
import net.derev.nuts.Stroom;
import net.derev.sekuriteit.Wagwoord;

public class Profiel {
	private final byte[] sensitieweData;
	private final String taalKode;
	private int dekripsiePogings = 0;
	public Profiel(byte[] sensitieweData, String taalKode) {
		this.sensitieweData = sensitieweData;
		this.taalKode = taalKode;
	}
	public String geeTaalKode() {
		return taalKode;
	}
	public SensitieweProfiel geeSensitieweProfiel(String wagwoord) {
		++dekripsiePogings;
		byte[] skoonData = Wagwoord.dekripteerData(sensitieweData, wagwoord);
		if (skoonData == null)
			return null;
		dekripsiePogings = 0;
		String[] sensitieweStringe = Stroom.trekStringeUit(skoonData);
		if (sensitieweStringe == null || sensitieweStringe.length != 3)
			return null;
		
		String nommer = sensitieweStringe[0];
		String pin = sensitieweStringe[1];
		String bankWagwoord = sensitieweStringe[2];
		return new MyProfiel(nommer, pin, bankWagwoord);
	}
	public int geeDekripsiePogings() {
		return dekripsiePogings;
	}
	public static byte[] enkripteerSensitieweData(String wagwoord, String nommer, String pin, String bankWagwoord) {
		byte[] sensitieweData = Stroom.stootStringeIn(new String[] {nommer, pin, bankWagwoord});
		return Wagwoord.enkripteer(sensitieweData, wagwoord);
	}
	public void stoor() throws RecordStoreFullException, RecordStoreNotFoundException, RecordStoreException, IOException {
		Rms.stoorData("sensitief", sensitieweData);
		Rms.stoorStringe("profiel", new String[]{ taalKode });
	}
	public static Profiel lees() {
		try {
			byte[] sensitieweData = Rms.leesData("sensitief");
			String[] profielVelde = Rms.leesStringe("profiel");
			if (sensitieweData == null || profielVelde == null)
				return null;
			return new Profiel(sensitieweData, profielVelde[0]);
		} catch (RecordStoreException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}
