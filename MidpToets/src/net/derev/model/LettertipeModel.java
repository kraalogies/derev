// Copyright (C) 2009 Hans Malherbe
//
// This file is part of Derev.
//
// Derev is free software: you can redistribute it and/or modify
// it under the terms of the GNU General Public License as published by
// the Free Software Foundation, either version 3 of the License, or
// (at your option) any later version.
//
// Derev is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// GNU General Public License for more details.
//
// You should have received a copy of the GNU General Public License
// along with Derev.  If not, see <http://www.gnu.org/licenses/>.

package net.derev.model;

import javax.microedition.lcdui.Font;

import net.derev.model.entiteit.Lettertipe;


public class LettertipeModel {
    private final int[] familie = new int[]{ Font.FACE_MONOSPACE, Font.FACE_PROPORTIONAL, Font.FACE_SYSTEM };
    private final String[] familieName = new String[]{ "Mono", "Proporsioneel", "Stelsel" };
    private final int[] groottes = new int[] { Font.SIZE_LARGE, Font.SIZE_SMALL, Font.SIZE_MEDIUM };
    private final String[] grootteName = new String[] { "Groot", "Klein", "Middel" };
    private final int[] style = new int[] { Font.STYLE_BOLD, Font.STYLE_ITALIC, Font.STYLE_PLAIN, Font.STYLE_UNDERLINED };
    private final String[] stylName = new String[] { "Vet", "Skuins", "Gewoon", "Onderstreep" };
    private final Lettertipe letterTipe;
    private int familiePosisie;
    private int groottePosisie;
    private int stylPosisie;

	public LettertipeModel() {
		super();
		this.letterTipe = new Lettertipe(this);
	}

	public Lettertipe geeEerste() {
		familiePosisie = 0;
		groottePosisie = 0;
		stylPosisie = 0;
		return letterTipe;
	}

	private boolean stelVolgendePosisie() {
		if (familiePosisie < familie.length - 1) {
			++familiePosisie;
			return true;
		}
		familiePosisie = 0;
		if (groottePosisie < groottes.length - 1) {
			++groottePosisie;
			return true;
		}
		groottePosisie = 0;
		if (stylPosisie < style.length - 1) {
			++stylPosisie;
			return true;
		}
		stylPosisie = 0;
		return false;
	}
	
	public Lettertipe geeVolgende() {
		if (stelVolgendePosisie())
			return letterTipe;
		return null;
	}

	public String geeHuidigeNaam() {
		return familieName[familiePosisie];
	}

	public String geeHuidigeGrootteBeskrywing() {
		return grootteName[groottePosisie];
	}

	public String geeHuidigeStylBeskrywing() {
		return stylName[stylPosisie];
	}

	public int geeHuidigeFamilie() {
		return familie[familiePosisie];
	}

	public int geeHuidigeStyl() {
		return style[stylPosisie];
	}

	public int geeHuidigeGrootte() {
		return groottes[groottePosisie];
	}

}
