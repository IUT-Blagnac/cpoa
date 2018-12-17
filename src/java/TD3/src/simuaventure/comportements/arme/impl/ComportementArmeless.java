package simuaventure.comportements.arme.impl;

import simuaventure.comportements.arme.ComportementArme;

public class ComportementArmeless implements ComportementArme {

	@Override
	public void attaque() {
		System.out.println("Je suis sans arme!");
	}

	@Override
	public boolean estOperationnelle() {
		return false;
	}

}
