package net.derev.model;

import java.util.Vector;

import platform.Omgewing;

import net.derev.entiteit.Motor;
import net.derev.entiteit.MotorVervaardiger;
import net.derev.infrastruktuur.Pare;
import net.derev.infrastruktuur.VastePare;
public class DetailMotorModel {
	private final Omgewing omgewing;
	public VastePare geeDetail(Sleutel sleutel) {
		Vector motors = MotorModel.motors;
		for (int motorPos = 0; motorPos < motors.size(); ++motorPos) {
			Motor motor = (Motor) motors.elementAt(motorPos);
			if (!sleutel.vergelyk(motor.geeId()))
				continue;
			
			Pare motorDetail = new Pare();
			motorDetail.voegby("Id", new Integer(motor.geeId()));
			motorDetail.voegby("Jaar", new Integer(motor.geeJaar()));
			motorDetail.voegby("Spesifikasie", motor.geeSpesifikasie());
			motorDetail.voegby("Maak", motor.geeMaak().geeNaam());
			return motorDetail.vries(omgewing);
		}
		return null;
	}

	public DetailMotorModel(Omgewing omgewing) {
		super();
		this.omgewing = omgewing;
	}

	public void stoor(VastePare invoer) {
		MotorVervaardiger maak = kryMaak(invoer);
		int id = ((Integer) invoer.soekWaarde("Id")).intValue();
		int jaar = ((Integer) invoer.soekWaarde("Jaar")).intValue();
		String spes = (String) invoer.soekWaarde("Spesifikasie");
		Motor nuweMotor = new Motor(id, jaar, spes, maak);
		MotorModel.motors.addElement(nuweMotor);
	}

	private MotorVervaardiger kryMaak(VastePare invoer) {
		Object maakId = invoer.soekWaarde("MaakId");
		if (maakId == null)
			return null;
		return MotorMaakModel.geeMaak(((Integer) maakId).intValue());
	}

}
