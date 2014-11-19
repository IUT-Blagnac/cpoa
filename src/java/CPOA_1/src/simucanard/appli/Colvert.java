package simucanard.appli;

import simucanard.comportements.cancaner.impl.Cancan;
import simucanard.comportements.voler.impl.VolerAvecDesAiles;

public class Colvert extends Canard {

	public Colvert() {
		super(new Cancan(), new VolerAvecDesAiles());
	}

	public void afficher() {
		System.out.println("Je suis un Colvert");
	}

}
