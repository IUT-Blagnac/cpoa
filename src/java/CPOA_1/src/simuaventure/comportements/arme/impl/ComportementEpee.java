package simuaventure.comportements.arme.impl;

import simuaventure.comportements.arme.ComportementArme;

public class ComportementEpee implements ComportementArme {

	@Override
	public void attaque() {

		System.out.println("Coup d'epée");

	}

	@Override
	public boolean estOperationnelle() {
		// TODO Auto-generated method stub
		return true;
	}

}
