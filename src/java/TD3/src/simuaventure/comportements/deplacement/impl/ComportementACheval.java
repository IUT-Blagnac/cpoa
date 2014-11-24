package simuaventure.comportements.deplacement.impl;

import simuaventure.comportements.deplacement.ComportementDeplacement;

public class ComportementACheval implements ComportementDeplacement {

	private static ComportementACheval uniqueInstance = new ComportementACheval();
	
	public static ComportementACheval getInstance() {
		return uniqueInstance;
	}
	
	@Override
	public void avancer() {
		System.out.println("Je galope");

	}

	@Override
	public void reculer() {
		
		System.out.println("Je recule � petits pas");
	}

}
