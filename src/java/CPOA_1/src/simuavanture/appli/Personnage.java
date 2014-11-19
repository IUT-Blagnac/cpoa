package simuavanture.appli;

import simuaventure.comportements.arme.ComportementArme;
import simuaventure.comportements.deplacement.ComportementDeplacement;

public abstract class Personnage {
	private ComportementArme comportementArme;
	private ComportementDeplacement comportemeDeplacement;

	public void setArme(ComportementArme comportementArme) {
		this.comportementArme = comportementArme;
	}

	public void setDeplacement(ComportementDeplacement comportemeDeplacement) {

		this.comportemeDeplacement = comportemeDeplacement;
	}

	public Personnage(ComportementArme ca, ComportementDeplacement cd) {
		setArme(ca);
		setDeplacement(cd);
	}

	public void frapper() {
		this.comportementArme.attaque();
	}

	public void avancer() {
		this.comportemeDeplacement.avancer();
	}

	public void reculer() {
		this.comportemeDeplacement.reculer();
		;
	}
}
