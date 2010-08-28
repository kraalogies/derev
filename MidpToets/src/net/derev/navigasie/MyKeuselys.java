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

package net.derev.navigasie;

import java.util.Hashtable;
import java.util.Vector;

import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;

import net.derev.infrastruktuur.Kultuur;
import net.derev.l10n.Globaal;
import net.derev.voorstelling.VertoonVenster;
import net.derev.voorstelling.spesifikasie.SkermSpesifikasie;

public class MyKeuselys implements Keuselys, CommandListener {
	private final VertoonVenster vertoonVenster;
	private Displayable huidigeSkerm;
	private final Hashtable skermBevele = new Hashtable();
	private Vector geskiedenis = new Vector();
	private Command uit;
	private Command terug;
	private final Kultuur kultuur;

	public MyKeuselys(VertoonVenster vertoonVenster, Kultuur kultuur) throws Exception  {
		super();
		this.vertoonVenster = vertoonVenster;
		this.kultuur = kultuur;
	}

	public void gooiBroodKrummel(SkermSpesifikasie spes) {
		geskiedenis.addElement(spes);
	}
	
	public void gaanTerug() throws Exception {
		if (geskiedenis.size() == 0)
			return;
		SkermSpesifikasie spes = (SkermSpesifikasie) geskiedenis.lastElement();

		geskiedenis.removeElementAt(geskiedenis.size() - 1); 
		spes.doen();
	}
	
	private void hanteerFout(Exception e) {
		e.printStackTrace();
		vertoonVenster.wys(new Alert("Fout", e.toString(), null, AlertType.ERROR));
	}
	
	public void commandAction(Command c, Displayable d) {
		if (c == uit) {
			vertoonVenster.vernietig();
			return;
		}
		if (c == terug) {
			try {
				gaanTerug();
			} catch (Exception e) {
				hanteerFout(e);
			}
			return;
		}
		
		Object bevel = skermBevele.get(c);
		if (bevel != null)
			try {
				((Aksie) bevel).doen(c);
			} catch (Exception e) {
				hanteerFout(e);
			}
	}

	public void registreer(Command bevel, Aksie aksie) {
		if (huidigeSkerm == null)
			return;
		huidigeSkerm.addCommand(bevel);
		skermBevele.put(bevel, aksie);
	}
	
	public void stelNuweSkermOp(Displayable skerm,
			SkermSpesifikasie spes) throws Exception {
		huidigeSkerm = skerm;
		skermBevele.clear();
		if (geskiedenis.size() == 0) {
			uit = new Command(kultuur.kryFrase(Globaal.UIT), Command.EXIT, 100000);
			skerm.addCommand(uit);
		} else {
			SkermSpesifikasie vorigeKontrole = (SkermSpesifikasie) geskiedenis.elementAt(geskiedenis.size() - 1);
			terug = new Command("<" + kultuur.kryFrase(vorigeKontrole.geeBeskrywingSleutel()), Command.BACK, 10000);
			skerm.addCommand(terug);
		}
		skerm.setCommandListener(this);
	}

	public void vergeetLaaste() {
		if (geskiedenis.size() == 0)
			return;
		geskiedenis.removeElementAt(geskiedenis.size() - 1);
	}

}
