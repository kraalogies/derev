package net.derev.model;

public class Verwysing {
	private final String beskrywing;
	private final String[] lokaleVelde;
	private final String verwysdeEntiteit;
	private final boolean verpligtend;
	
	public Verwysing(String beskrywing, String lokaleVeld, String verwysdeEntiteit, boolean verpligtend) {
		this.beskrywing = beskrywing;
		lokaleVelde = new String[] { lokaleVeld };
		this.verwysdeEntiteit = verwysdeEntiteit;
		this.verpligtend = verpligtend;
	}

	public String geeBeskrywing() {
		return beskrywing;
	}

	public String geeEntiteit() {
		return verwysdeEntiteit;
	}

	public String[] geeLokaleSleutelName() {
		return lokaleVelde;
	}

	public boolean isVerpligtend() {
		return verpligtend;
	}

}
