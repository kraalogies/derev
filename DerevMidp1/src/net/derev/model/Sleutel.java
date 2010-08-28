package net.derev.model;

public interface Sleutel {

	boolean vergelyk(int id);

	boolean vergelyk(Sleutel sleutel);

	Object[] geeWaardes();

	boolean vergelyk(String waarde);

}
