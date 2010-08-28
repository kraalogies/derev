package net.derev.voorstelling.spesifikasie;

import net.derev.model.Sleutel;
import net.derev.model.VasteSleutels;
import net.derev.voorstelling.bestemming.EntiteitIdBestemming;
import net.derev.voorstelling.bestemming.SkermBestemming;

public interface LysSpesifikasie extends SkermSpesifikasie {

	public abstract EntiteitIdBestemming geeBestemming();

	public abstract int geeBlaai();

	public abstract boolean isVanKleinNaGroot();

	public abstract VasteSleutels geeFilters();

	public abstract LysSpesifikasie geeLysSpes();

	public abstract boolean isBesigOmTerugTeBlaai();

	public abstract Sleutel geeEersteKeuse();

	public abstract LysSpesifikasie kloonMetBlaai(int veranderBlaai,
			Sleutel kryGeselekteerdeId) throws Exception;

	public abstract LysSpesifikasie kloonSorteerAnders(
			Sleutel kryGeselekteerdeId) throws Exception;

	public abstract SkermSpesifikasie kloon(Sleutel kryGeselekteerdeId) throws Exception;

	public abstract LysSpesifikasie kloonMetBestemming(
			SkermBestemming skermBestemming) throws Exception;

	public abstract LysSpesifikasie kloonMetId(Sleutel sleutel) throws Exception;

}