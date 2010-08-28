// Copyright (C) 2009 Hans Malherbe
//
// This file is part of Derev.
//
// Derev is free software: you can redistribute it and/or modify
// it under the terms of the GNU General Public License as published by
// the Free Software Foundation, either version 3 of the License, or
// (at your option) any later version.
//
// Derev is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// GNU General Public License for more details.
//
// You should have received a copy of the GNU General Public License
// along with Derev.  If not, see <http://www.gnu.org/licenses/>.

package net.derev.nuts;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class Stroom {
	public static byte[] leesPresies(InputStream in, int aantal)
			throws IOException {
		if (in == null)
			throw new IOException();
		byte[] buffer = new byte[aantal];
		int aantalGelees = 0;
		while (aantalGelees < aantal)
			aantalGelees += in
					.read(buffer, aantalGelees, aantal - aantalGelees);
		return buffer;
	}

	public static String[] trekStringeUit(byte[] data) {
		if (data == null)
			return null;
		try {
			return trekStringeUitImpl(data);
		} catch (IOException e) {
			return null;
		}
	}
	
	private static String[] trekStringeUitImpl(byte[] data) throws IOException {
		final ByteArrayInputStream rou = new ByteArrayInputStream(data);
		try {
			final DataInputStream in = new DataInputStream(rou);
			try {
				int aantalStringe = in.read();
				String[] stringe = new String[aantalStringe];
				for (int stringPos = 0; stringPos < aantalStringe; ++stringPos) {
					stringe[stringPos] = in.readUTF();
				}
				return stringe;
			} finally {
				in.close();
			}
		} finally {
			rou.close();
		}
	}
	
	public static byte[] stootStringeIn(String[] stringe) {
		try {
			return stootStringeInImpl(stringe);
		} catch (IOException e) {
			return null;
		}
	}

	private static byte[] stootStringeInImpl(String[] stringe) throws IOException {
		if (stringe == null)
			return null;
		final ByteArrayOutputStream rou = new ByteArrayOutputStream();
		try {
			final DataOutputStream uit = new DataOutputStream(rou);
			try {
				uit.write(stringe.length);
				for (int stringPos = 0; stringPos < stringe.length; ++stringPos) {
					String s = stringe[stringPos];
					uit.writeUTF(s == null ? "" : s);
				}
				uit.flush();
			} finally {
				uit.close();
			}
			rou.flush();
			return rou.toByteArray();
		} finally {
			rou.close();
		}
	}

	public static byte[] leesAlles(DataInputStream in) throws IOException {
		final ByteArrayOutputStream uit = new ByteArrayOutputStream();
		try {
	        int ch;
	        while ((ch = in.read()) != -1)
	            uit.write(ch);
	        uit.flush();
	        return uit.toByteArray();
		} finally {
	        uit.close();
		}
	}
	
	public static byte[] leesAlles(InputStream in) throws IOException {
		if (in == null)
			throw new IOException();
		final ByteArrayOutputStream uit = new ByteArrayOutputStream();
		try {
	        int ch;
	        while ((ch = in.read()) != -1)
	            uit.write(ch);
	        uit.flush();
	        return uit.toByteArray();
		} finally {
	        uit.close();
		}
	}
	
	public static byte[] leesAlles2(InputStream in) throws IOException {
		if (in == null)
			throw new IOException();
		final ByteArrayOutputStream uit = new ByteArrayOutputStream();
		try {
			byte[] buffer = new byte[15000];
			for (int n; (n = in.read(buffer)) > 0;) {
				uit.write(buffer, 0, n);
				System.out.println("lees: " + Integer.toString(n));
				System.out.println("["+new String(buffer, 0, n, "UTF-8")+"]");
				if (n == 2) {
					System.out.println(Integer.toHexString(buffer[0]));
					System.out.println(Integer.toHexString(buffer[1]));
				}
			}
			uit.flush();
			return uit.toByteArray();
		} finally {
			uit.close();
		}
	}
}
