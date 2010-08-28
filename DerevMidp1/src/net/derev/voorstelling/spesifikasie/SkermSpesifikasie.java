package net.derev.voorstelling.spesifikasie;

public interface SkermSpesifikasie {

	public void doen() throws Exception;

	public String geeNaam();

	public String geeBeskrywingSleutel();
	
	public Object geeKenmerk(String naam);

}