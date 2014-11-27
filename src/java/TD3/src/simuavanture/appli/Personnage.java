package simuavanture.appli;

import simuaventure.comportements.arme.ComportementArme;
import simuaventure.comportements.deplacement.ComportementDeplacement;


public abstract class Personnage {
	private ComportementArme comportementArme;
	private ComportementDeplacement comportemeDeplacement;
	private String nom;
	
	public Personnage(String nom, ComportementArme ca, ComportementDeplacement cd) {
		// Manque vérification de (null, null)
		setNom(nom);
		setArme(ca);
		setDeplacement(cd);
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public void setArme(ComportementArme comportementArme) {
		this.comportementArme = comportementArme;
	}

	public void setDeplacement(ComportementDeplacement comportemeDeplacement) {

		this.comportemeDeplacement = comportemeDeplacement;
	}

	public void frapper() {
		System.out.print(nom + " frappe().");
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
