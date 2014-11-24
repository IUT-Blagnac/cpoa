package simuavanture.appli;

import simuaventure.comportements.arme.ComportementArme;
import simuaventure.comportements.arme.impl.ComportementEpee;
import simuaventure.comportements.deplacement.ComportementDeplacement;
import simuaventure.comportements.deplacement.impl.ComportementCourir;
import simuaventure.comportements.deplacement.impl.ComportementMarcher;

public class Reine extends Personnage {

	public Reine() {
		super ("Unknown", new ComportementEpee(),new ComportementMarcher() );
	}
	
}
