package standardbank.kontrole.spesifikasie;

import java.io.IOException;

import net.derev.voorstelling.spesifikasie.SkermSpesifikasie;

public class ProfielSpesifikasie implements SkermSpesifikasie {
	private final String beskrywingSleutel;
	private final String kenmerk;
	private final String naam;
	
	public ProfielSpesifikasie(String beskrywingSleutel, String kenmerk,
			String naam) {
		super();
		this.beskrywingSleutel = beskrywingSleutel;
		this.kenmerk = kenmerk;
		this.naam = naam;
	}

	public void doen() throws IllegalAccessException, InstantiationException,
			NullPointerException, IOException, InterruptedException {
		
	}

	public String geeBeskrywingSleutel() {
		return beskrywingSleutel;
	}

	public Object geeKenmerk(String naam) {
		return kenmerk;
	}

	public String geeNaam() {
		return naam;
	}

}
