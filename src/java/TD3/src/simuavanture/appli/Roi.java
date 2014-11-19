package simuavanture.appli;

import simuaventure.comportements.arme.impl.ComportementEpee;
import simuaventure.comportements.deplacement.impl.ComportementCourir;

public class Roi extends Personnage {

	public Roi(){
		super ("", new ComportementEpee(),new ComportementCourir() );
	}
}
