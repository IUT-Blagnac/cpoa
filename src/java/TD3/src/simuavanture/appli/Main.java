package simuavanture.appli;

import simuaventure.comportements.arme.impl.*;

public class Main {

	public static void main(String[] args) {
		
		String choix = "Epee";
		Chevalier ch = new Chevalier();
		Chevalier ch2 = new Chevalier("JMB");
		Chevalier perso = new Chevalier("JMI");
//		Roi artur = new Roi();
//		Reine margaux = new Reine();

		ch.avancer();
		ch.frapper();

		ComportementArc.NBMAXCST = 5;
		ch.setArme(new ComportementArc());
		ch2.frapper();
		ch.frapper();
		ch2.frapper();
		ch.frapper();
		ch.frapper();
		ch.frapper();
		ch.frapper();
		
		// test de factory
		SimpleFabriqueArme fabrique = new SimpleFabriqueArme();

		perso.setArme(fabrique.creerComportementArme("Epee"));
		perso.frapper();
	}

}
