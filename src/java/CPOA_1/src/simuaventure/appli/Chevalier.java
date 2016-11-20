package simuaventure.appli;

import simuaventure.comportements.arme.impl.ComportementEpee;
import simuaventure.comportements.deplacement.impl.ComportementACheval;

public class Chevalier extends Personnage {

	public Chevalier() {
		super(new ComportementEpee(), new ComportementACheval());
	}
}
