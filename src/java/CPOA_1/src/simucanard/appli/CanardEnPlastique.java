package simucanard.appli;

import simucanard.comportements.cancaner.impl.Coincoin;
import simucanard.comportements.voler.impl.NePasVoler;


public class CanardEnPlastique extends Canard {

	public CanardEnPlastique() {
	super (new Coincoin(),new NePasVoler());	// TODO Auto-generated constructor stub
	}
	
	@Override
	public void afficher() {
		System.out.println("Je suis un CanardEnPlastique!");
	}


}
