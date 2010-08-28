package net.derev.voorstelling.spesifikasie;

import net.derev.infrastruktuur.VastePare;
import net.derev.model.Sleutel;
import net.derev.model.VasteSleutels;
import net.derev.voorstelling.KontroleHouer;
import net.derev.voorstelling.LysKontrole;
import net.derev.voorstelling.bestemming.EntiteitIdBestemming;
import net.derev.voorstelling.bestemming.SkermBestemming;

public class MyLysSpesifikasie implements LysSpesifikasie {
	private final String naam;
	private final String beskrywing;
	private final int blaai;
	private final boolean vanKleinNaGroot;
	private final VasteSleutels filters;
	private final LysSpesifikasie vorigeLys;
	private final EntiteitIdBestemming bestemming;
	private final Sleutel eersteKeuse;
	private final VastePare kenmerke;
	private final KontroleHouer kontroleHouer;
	public EntiteitIdBestemming geeBestemming() {
		return bestemming;
	}

	public int geeBlaai() {
		return blaai;
	}

	public boolean isVanKleinNaGroot() {
		return vanKleinNaGroot;
	}

	public VasteSleutels geeFilters() {
		return filters;
	}

	public MyLysSpesifikasie(	
			KontroleHouer kontroleHouer,
			String naam, 
			String beskrywing, 
			EntiteitIdBestemming bestemming,
			LysSpesifikasie vorigeLys,
			int blaai, 
			boolean vanKleinNaGroot,
			VasteSleutels filters,
			Sleutel eersteKeuse,
			VastePare kenmerke) throws Exception {
		super();
		this.naam = naam;
		this.beskrywing = beskrywing;
		this.bestemming = bestemming;
		this.vorigeLys = vorigeLys;
		this.blaai = blaai;
		this.vanKleinNaGroot = vanKleinNaGroot;
		this.filters = filters;
		this.eersteKeuse = eersteKeuse;
		this.kenmerke = kenmerke;
		this.kontroleHouer = kontroleHouer;
	}
	
	public static LysSpesifikasie maakBasies(KontroleHouer kontroleHouer, String naam, String beskrywing) throws Exception {
		return new MyLysSpesifikasie(kontroleHouer, naam, beskrywing, null, null, 0, true, null, null, null);
	}

	public LysSpesifikasie geeLysSpes() {
		return vorigeLys;
	}

	public boolean isBesigOmTerugTeBlaai() {
		return vorigeLys != null && vorigeLys.geeBlaai() > geeBlaai();
	}

	public void doen() throws Exception {
		LysKontrole kontrole = kontroleHouer.kryLys(naam); 
		kontrole.lys(this);
	}

	public String geeBeskrywingSleutel() {
		return beskrywing;
	}

	public String geeNaam() {
		return naam;
	}

	public Sleutel geeEersteKeuse() {
		return eersteKeuse;
	}

	public LysSpesifikasie kloonMetBlaai(int veranderBlaai,
			Sleutel geselekteerdeId) throws Exception {
		return new MyLysSpesifikasie(kontroleHouer, naam, beskrywing, bestemming, this,
				blaai + veranderBlaai,
				vanKleinNaGroot, filters,
				geselekteerdeId, kenmerke);
	}

	public LysSpesifikasie kloonSorteerAnders(Sleutel geselekteerdeId) throws Exception {
		return new MyLysSpesifikasie(kontroleHouer, naam, beskrywing, bestemming, this, blaai, !vanKleinNaGroot, filters, geselekteerdeId, kenmerke);
	}

	public SkermSpesifikasie kloon(Sleutel geselekteerdeId) throws Exception {
		return new MyLysSpesifikasie(kontroleHouer, naam, beskrywing, bestemming, this, blaai, vanKleinNaGroot, filters, geselekteerdeId, kenmerke);
	}

	public Object geeKenmerk(String naam) {
		if (kenmerke == null)
			return null;
		return kenmerke.soekWaarde(naam);
	}

	public static MyLysSpesifikasie maakKenmerke(KontroleHouer kontroleHouer, String entiteitNaam,
			String entiteitBeskrywing, VastePare kenmerke) throws Exception {
		return new MyLysSpesifikasie(kontroleHouer, entiteitNaam, entiteitBeskrywing, null, null, 0, true, null, null, kenmerke);
	}

	public LysSpesifikasie kloonMetBestemming(SkermBestemming skermBestemming) throws Exception {
		return new MyLysSpesifikasie(kontroleHouer, naam, beskrywing, skermBestemming, vorigeLys, blaai, vanKleinNaGroot, filters, eersteKeuse, kenmerke);
	}

	public LysSpesifikasie kloonMetId(Sleutel sleutel) throws Exception {
		return new MyLysSpesifikasie(kontroleHouer, naam, beskrywing, bestemming, vorigeLys, blaai, vanKleinNaGroot, filters, sleutel, kenmerke);
	}

}
