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
		ComportementArme compAdequat = null;
		if (type.equals("Epee")) {
			compAdequat = new ComportementEpee();
		}
		else if (type.equals("Arc")) {
			compAdequat = new ComportementArc();
		}
		else compAdequat = new ComportementArmeless();
		return compAdequat;
	}
}
