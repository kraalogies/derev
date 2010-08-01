package com.toets;

import net.rim.device.api.ui.component.Dialog;
import net.rim.device.api.ui.component.LabelField;
import net.rim.device.api.ui.component.RichTextField;
import net.rim.device.api.ui.container.MainScreen;

public class Skerm extends MainScreen {
	public Skerm() {
		setTitle(new LabelField("Toets", LabelField.ELLIPSIS | LabelField.USE_ALL_WIDTH));
		Bib bib = new Bib();
		add(new RichTextField(bib.getName()));
	}
	
	public boolean onClose() {
		Dialog.alert("Uit");
		System.exit(0);
		return true;
	}
}
