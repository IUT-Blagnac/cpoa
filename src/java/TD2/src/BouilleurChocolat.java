/**
 * 
 */

/**
 * @author bruel, taken from O'Reilly
 *
 */
public class BouilleurChocolat { 
	private boolean vide;
	private boolean bouilli; 

	public BouilleurChocolat() {
		vide = true;
		bouilli = false; 
	}
	public void remplir() { 
		if (estVide()) { 
			vide = false;
		}
		bouilli = false;
	// remplir le bouilleur du mélange lait/chocolat }
	}
	public void vider() {
		if (!estVide() && estBouilli()) { // vider le mélange
			vide = true;
		}
	}
	public void bouillir() {
		if (!estVide() && !estBouilli()) {
			// porter le contenu à ébullition
			bouilli = true; 
			}
	}
	public boolean estVide() { return vide;}
	public boolean estBouilli() { return bouilli;} 
}