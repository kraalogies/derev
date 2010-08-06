package platform;

import platform.roep.StringFunk;

public interface Joernaal {
	void fout(final String teks);
	void info(final String teks);
	void fout(final StringFunk teks);
	void info(final StringFunk teks);
}
