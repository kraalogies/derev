package net.derev.sekuriteit;

import java.io.UnsupportedEncodingException;

import net.derev.nuts.Stroom;

import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.BufferedBlockCipher;
import org.bouncycastle.crypto.CryptoException;
import org.bouncycastle.crypto.digests.MD5Digest;
import org.bouncycastle.crypto.engines.AESLightEngine;
import org.bouncycastle.crypto.generators.PKCS5S1ParametersGenerator;
import org.bouncycastle.crypto.modes.CBCBlockCipher;
import org.bouncycastle.crypto.paddings.PaddedBufferedBlockCipher;
import org.bouncycastle.crypto.params.KeyParameter;

public class Wagwoord {
	public static byte[] enkripteer(String boodskap, String wagwoord)
			throws UnsupportedEncodingException {
		if (boodskap == null)
			return null;
		return enkripteer(Stroom.stootStringeIn(new String[]{boodskap}), wagwoord);
	}

	private static BlockCipher geeEnjin() {
		return new AESLightEngine();
        //return new DESedeEngine();
        //return new IDEAEngine();
        //return new RijndaelEngine();
        //return new TwofishEngine();
        //return new DESEngine();
	}
	
    public static byte[] enkripteer(byte[] boodskap, String wagwoord) {
		if (boodskap == null || wagwoord == null)
			return null;
		KeyParameter sleutel = krySleutel(wagwoord);
        BufferedBlockCipher kripper = geeKripper();

        kripper.init(true, sleutel);

        byte[] rv = new byte[kripper.getOutputSize(boodskap.length)];

        int afvoerLengte = kripper.processBytes(boodskap, 0, boodskap.length, rv, 0);
        try
        {
            kripper.doFinal(rv, afvoerLengte);
        }
        catch (CryptoException ce)
        {
        	return null;
        }
        return rv;
		/*AESLightEngine blokKripper = new AESLightEngine();
		StreamCipher stroomKripper = new StreamBlockCipher(new CFBBlockCipher(blokKripper, 8));

		byte[] iv = new byte[blokKripper.getBlockSize()];
		SecureRandom ewe = new SecureRandom();
		ewe.nextBytes(iv);
		
		// Las init vektor en boodskap aanmekaar
		byte[] skoonteks = new byte[boodskap.length + blokKripper.getBlockSize()];
		System.arraycopy(iv, 0, skoonteks, 0, iv.length);
		System.arraycopy(boodskap, 0, skoonteks, iv.length,
				boodskap.length);

		// Enkripteer
		byte[] kripteks = new byte[skoonteks.length];
		stroomKripper.init(true, sleutel);
		stroomKripper
				.processBytes(skoonteks, 0, skoonteks.length, kripteks, 0);
		return kripteks;*/
	}

	private static BufferedBlockCipher geeKripper() {
		return new PaddedBufferedBlockCipher(new CBCBlockCipher(geeEnjin()));
	}

	private static byte[] krySout() {
		byte[] sout = { (byte) 0x12, (byte) 0x8f, (byte) 0xdf, (byte) 0x78,
				(byte) 0x7c, (byte) 0xf5, (byte) 0xe1, (byte) 0x85 };
		return sout;
	}

	private static KeyParameter krySleutel(String wagwoord) {
		 PKCS5S1ParametersGenerator sleutelOpwekker = new PKCS5S1ParametersGenerator(new MD5Digest()); 
		 byte[] wagwoordWaardes = PKCS5S1ParametersGenerator.PKCS5PasswordToBytes(wagwoord.toCharArray());
		 sleutelOpwekker.init(wagwoordWaardes, krySout(), 1000); 
		 return (KeyParameter) sleutelOpwekker.generateDerivedParameters(128); 
	}

	public static byte[] dekripteerData(byte[] kripteks, String wagwoord) {
		KeyParameter sleutel = krySleutel(wagwoord);
        BufferedBlockCipher kripper = geeKripper();

        kripper.init(false, sleutel);

        byte[] rv = new byte[kripper.getOutputSize(kripteks.length)];

        int afvoerLengte = kripper.processBytes(kripteks, 0, kripteks.length, rv, 0);
        try
        {
            kripper.doFinal(rv, afvoerLengte);
        }
        catch (CryptoException ce)
        {
        	return null;
        }
        return rv;
		/*AESLightEngine blokKripper = new AESLightEngine();
		StreamCipher stroomKripper = new StreamBlockCipher(new CFBBlockCipher(blokKripper, 8));

		// Dekripteer
		byte[] skoonteks = new byte[kripteks.length];
		stroomKripper.init(false, sleutel);
		stroomKripper.processBytes(kripteks, 0, kripteks.length, skoonteks,
				0);

		// Ignoreer init vektor aan begin
		int ivLengte = blokKripper.getBlockSize();
		int boodskapLengte = skoonteks.length - ivLengte;
		
		byte[] boodskap = new byte[boodskapLengte];
		System.arraycopy(skoonteks, ivLengte, boodskap, 0, boodskapLengte);
		return boodskap;*/
	}
	
	public static String dekripteer(byte[] kripteks, String wagwoord)
			throws UnsupportedEncodingException {
		byte[] skoonTeks = dekripteerData(kripteks, wagwoord);
		if (skoonTeks == null)
			return null;
		return Stroom.trekStringeUit(skoonTeks)[0];
	}
}
