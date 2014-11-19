package simuavanture.appli;

import simuaventure.comportements.arme.ComportementArme;
import simuaventure.comportements.arme.impl.ComportementEpee;
import simuaventure.comportements.arme.impl.ComportementPoignard;
import simuaventure.comportements.deplacement.ComportementDeplacement;
import simuaventure.comportements.deplacement.impl.ComportementCourir;

public class Troll extends Personnage {

	public Troll() {
		super ("", new ComportementPoignard(),new ComportementCourir() );
		// TODO Auto-generated constructor stub
	}
	

}
