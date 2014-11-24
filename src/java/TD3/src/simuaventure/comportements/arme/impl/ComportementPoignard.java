package simuaventure.comportements.arme.impl;

import simuaventure.comportements.arme.ComportementArme;

public class ComportementPoignard implements ComportementArme {

	@Override
	public void attaque() {
		System.out.println("Coup de poignard");

	}

	@Override
	public boolean estOperationnelle() {
		// TODO Auto-generated method stub
		return true;
	}

}
