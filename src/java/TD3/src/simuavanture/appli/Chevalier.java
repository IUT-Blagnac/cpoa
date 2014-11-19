package simuavanture.appli;

import simuaventure.comportements.arme.ComportementArme;
import simuaventure.comportements.arme.impl.ComportementEpee;
import simuaventure.comportements.deplacement.ComportementDeplacement;
import simuaventure.comportements.deplacement.impl.ComportementACheval;

public class Chevalier extends Personnage {

	protected Chevalier(String nom) {
		this(nom, ComportementEpee.getInstance(), ComportementACheval.getInstance());
	}	

	public Chevalier() {
		this("John Doe");
	}
	
	protected Chevalier(String nom, ComportementArme ca, ComportementDeplacement cd) {
		super(nom, ca, cd);
	}	

}
