/**
 * @author bruel (taken from Design Pattren - Head First, O'Reilly, 09/2004)
 *
 */
public class BouilleurCptChocolat { 
	private boolean vide;
	private boolean bouilli; 
	private static int nbInstance = 0;

	public BouilleurCptChocolat() {
		vide = true;
		bouilli = false;
		if (nbInstance == 0) {
			nbInstance = 1;
			return this;
		}
		else {
			return null;
		}

	}

	public BouilleurCptChocolat getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new BouilleurCptChocolat();
		}
		return uniqueInstance;
	}

	public void remplir() { 
		if (estVide()) { 
			vide = false;
			bouilli = false;
			// remplir le bouilleur du mélange lait/chocolat 
		}
	}

	public void vider() {
		if (!estVide() && estBouilli()) { 
			// vider le mélange
			vide = true;
		}
	}

	public void bouillir() {
		if (!estVide() && !estBouilli()) {
			// porter le contenu à ébullition
			bouilli = true; 
		}
	}

	public boolean estVide() { return vide;}

	public boolean estBouilli() { return bouilli;} 
}