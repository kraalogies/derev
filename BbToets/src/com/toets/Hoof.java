package com.toets;

import net.rim.device.api.ui.UiApplication;

public class Hoof extends UiApplication {

	public Hoof() {
		pushScreen(new Skerm());
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Hoof hoof = new Hoof();
		hoof.enterEventDispatcher();
	}

}
