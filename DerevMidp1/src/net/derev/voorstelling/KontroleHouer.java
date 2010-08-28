package net.derev.voorstelling;

public interface KontroleHouer {


	void registreerKontroleKlas(String naam, Class kontroleKlas);

	void registreerEnkelingKontrole(String naam, Kontrole kontrole);

	void registreerKontroleKlas(String naam, Class kontroleKlas,
			boolean verstekKontrole);

	DetailKontrole geeDetail(String naam) throws Exception;

	StoorKontrole geeStoor(String naam) throws Exception;

	Kontrole kry(String naam) throws Exception;

	LysKontrole kryLys(String naam) throws Exception;

	NuweRekordKontrole geeNuwe(String naam) throws Exception;

	void registreerEnkelingLys(String naam, LysKontrole navigasie);

	void registreerKlas(String naam, Class koppelvlak, boolean verstekKontrole);

	void registreerKlas(String naam, Class koppelvlak);


}