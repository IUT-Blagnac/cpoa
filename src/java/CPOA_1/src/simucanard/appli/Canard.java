package simucanard.appli;

import simucanard.comportements.cancaner.ComportementCancan;
import simucanard.comportements.voler.ComportementVol;

public abstract class Canard {
	protected ComportementCancan comportementCancan;
	protected ComportementVol comportementVol;

	Canard(ComportementCancan cc, ComportementVol cv) {
		comportementCancan = cc;
		comportementVol = cv;

	};

	public void nager() {
		System.out.println("Je nage comme un Canard!");
	}

	abstract public void afficher();

	public final void effectuerCancan() {
		comportementCancan.cancaner();
	}

	public final void effectuerVol() {
		comportementVol.voler();
	}

}
