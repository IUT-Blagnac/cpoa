package simuaventure.comportements.arme.impl;

import simuaventure.comportements.arme.ComportementArme;

public class ComportementEpee implements ComportementArme {

	private final static ComportementEpee uniqueInstance = new ComportementEpee();
	
	public final static  ComportementEpee getInstance() {
		return uniqueInstance;
	}

	@Override
	public void attaque() {

		System.out.println("Coup d'épée");

	}

	@Override
	public boolean estOperationnelle() {
		// TODO Auto-generated method stub
		return true;
	}

}
