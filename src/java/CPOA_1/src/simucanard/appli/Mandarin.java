package simucanard.appli;

import simucanard.comportements.cancaner.impl.Cancan;
import simucanard.comportements.voler.impl.VolerAvecDesAiles;

public class Mandarin extends Canard {

	
	
	public Mandarin (){
		
		super(new Cancan(),new VolerAvecDesAiles());
	}
	
	public void afficher() {
		System.out.println("Je suis un Mandarin");

	}

}
