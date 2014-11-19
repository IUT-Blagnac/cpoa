package simuaventure.comportements.arme.impl;

import simuaventure.comportements.arme.ComportementArme;

public class ComportementArc implements ComportementArme {

	public static int NBMAXCST=10;
	
	private int nbFleche = NBMAXCST;

	@Override
	public void attaque() {
		if (estOperationnelle()) {
			System.out.println("Je peux tirer " + nbFleche + " flèche(s) sur un total de " + NBMAXCST);
			nbFleche--;
		}
		else
			System.out.println("Je ne peux plus tirer de flèche!!");
	}

	@Override
	public boolean estOperationnelle() {
		// TODO Auto-generated method stub
		return nbFleche > 0;
	}

}
