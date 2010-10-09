package platform.ia;

public class Skema {
	public static Skema Intern = new Skema("res");
	private final String voorvoegsel;
	public Skema(String voorvoegsel) {
		this.voorvoegsel = voorvoegsel + "://";
	}
	public boolean Pas(String uri) {
		return uri != null && uri.startsWith(voorvoegsel);
	}
	
	public String KryPad(String uri) {
		if (uri == null || !uri.startsWith(voorvoegsel))
			return null;
		return uri.substring(voorvoegsel.length());
	}
}
