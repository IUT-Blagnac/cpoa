package simuaventure.comportements.deplacement.impl;

import simuaventure.comportements.deplacement.ComportementDeplacement;

public class ComportementACheval implements ComportementDeplacement {

	@Override
	public void avancer() {
		System.out.println("Je galope");

	}

	@Override
	public void reculer() {
		
		System.out.println("Je recule à petits pas");
	}

}
