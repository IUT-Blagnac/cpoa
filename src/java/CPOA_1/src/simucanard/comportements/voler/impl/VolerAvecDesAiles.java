package simucanard.comportements.voler.impl;

import simucanard.comportements.voler.ComportementVol;

public class VolerAvecDesAiles implements ComportementVol {

	@Override
	public void voler() {
		System.out.println("Je vole avec des ailes");
	}

}
