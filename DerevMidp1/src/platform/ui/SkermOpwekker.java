package platform.ui;

import platform.roep.GetalProsedure;

public interface SkermOpwekker {

	Skerm maak();
	ProtoSkerm maakLys(String[] items, GetalProsedure kies);
}
