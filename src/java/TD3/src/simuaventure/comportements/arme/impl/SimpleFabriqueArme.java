/**
 * 
 */
package simuaventure.comportements.arme.impl;

import simuaventure.comportements.arme.ComportementArme;

/**
 * @author bruel
 *
 */
public class SimpleFabriqueArme {
	public ComportementArme creerComportementArme(String type) {
		ComportementArme cpt = null;
		if (type.equals("Epee")) { 
			cpt = new ComportementEpee();
		} 
		else if (type.equals("Arc")) { 
			cpt = new ComportementArc();
		} 
		return cpt; 
	}
}
