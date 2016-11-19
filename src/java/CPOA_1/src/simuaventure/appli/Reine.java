package simuaventure.appli;

import simuaventure.comportements.arme.impl.ComportementEpee;
import simuaventure.comportements.deplacement.impl.ComportementCourir;
import simuaventure.comportements.deplacement.impl.ComportementMarcher;

public class Reine extends Personnage {

	
	public Reine() {
		super ( new ComportementEpee(),new ComportementMarcher() );
	}
	
}
