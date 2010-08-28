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

package net.derev.model.entiteit;

import javax.microedition.lcdui.Font;

import net.derev.model.LettertipeModel;


public class Lettertipe {
	private final LettertipeModel model;
	public Lettertipe(LettertipeModel lettertipeModel) {
		model = lettertipeModel;
	}

	public String geeNaam() {
		return model.geeHuidigeNaam();
	}

	public String geeGrootte() {
		return model.geeHuidigeGrootteBeskrywing();
	}

	public String geeStyl() {
		return model.geeHuidigeStylBeskrywing();
	}

	public Font gee() {
		return Font.getFont(model.geeHuidigeFamilie(), model.geeHuidigeStyl(), model.geeHuidigeGrootte());
	}

}
