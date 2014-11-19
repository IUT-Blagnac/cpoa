package simucanard.comportements.voler.impl;

import simucanard.comportements.voler.ComportementVol;

public class NePasVoler implements ComportementVol {

	@Override
	public void voler() {
		System.out.println("Je ne vole pas");

	}

}
