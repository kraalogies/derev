package net.derev.model;

import standardbank.model.SensitieweProfiel;

public class MyProfiel implements SensitieweProfiel {
	private final String rekeningNommer;
	private final String pin;
	private final String wagwoord;
	public MyProfiel(String nommer, String pin, String wagwoord) {
		this.rekeningNommer = nommer;
		this.pin = pin;
		this.wagwoord = wagwoord;
	}

	public String geePin() {
		return pin;
	}

	public String geeRekeningNommer() {
		return rekeningNommer;
	}

	public String geeWagwoord() {
		return wagwoord;
	}

}
