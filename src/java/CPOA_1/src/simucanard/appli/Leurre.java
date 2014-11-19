package simucanard.appli;

import simucanard.comportements.cancaner.impl.CanardMuet;
import simucanard.comportements.voler.impl.NePasVoler;

public class Leurre extends Canard {

	
	public Leurre(){
		
		super(new CanardMuet(),new NePasVoler());
	}

	@Override
	public void afficher() {
		
		System.out.println("Je suis un Leurre");

	}

}
