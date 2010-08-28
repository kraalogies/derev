package net.derev.infrastruktuur;

import java.io.IOException;

import javax.microedition.rms.RecordEnumeration;
import javax.microedition.rms.RecordStore;
import javax.microedition.rms.RecordStoreException;
import javax.microedition.rms.RecordStoreFullException;
import javax.microedition.rms.RecordStoreNotFoundException;

import net.derev.nuts.Stroom;

public class Rms {
	public static void stoorData(String stoorNaam, byte[] data)
			throws RecordStoreFullException, RecordStoreNotFoundException,
			RecordStoreException {
		try {
			RecordStore.deleteRecordStore(stoorNaam);
		} catch (RecordStoreNotFoundException e1) {
		}
		if (data == null)
			return;
		final RecordStore stoor = RecordStore.openRecordStore(stoorNaam, true);
		try {
			stoor.addRecord(data, 0, data.length);
		} finally {
			stoor.closeRecordStore();
		}
	}

	public static void stoorStringe(String stoorNaam, String[] stringe)
			throws RecordStoreException, IOException {
		if (stringe == null)
			return;
		stoorData(stoorNaam, Stroom.stootStringeIn(stringe));
	}

	public static byte[] leesData(String stoorNaam) throws RecordStoreException {
		try {
			final RecordStore stoor = RecordStore.openRecordStore(stoorNaam,
					false);
			try {
				RecordEnumeration rekordPos = stoor.enumerateRecords(null,
						null, false);
				if (!rekordPos.hasNextElement())
					return null;
				return rekordPos.nextRecord();
			} finally {
				stoor.closeRecordStore();
			}
		} catch (RecordStoreNotFoundException e) {
			return null;
		} catch (RecordStoreFullException e) {
			return null;
		}
	}

	public static String[] leesStringe(String stoorNaam) throws IOException,
			RecordStoreException {
		byte[] data = leesData(stoorNaam);
		if (data == null)
			return null;
		return Stroom.trekStringeUit(data);
	}

}
