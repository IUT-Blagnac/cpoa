/**
 * @author bruel (taken from Design Pattern - Head First, O'Reilly, 09/2004)
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
			bouilli = false;
			// Remplir le bouilleur du mélange lait/chocolat
		}
	}

	public void vider() {
		if (!estVide() && estBouilli()) {
			// Vider le mélange
			vide = true;
			bouilli = false;
		}
	}

	public void bouillir() {
		if (!estVide() && !estBouilli()) {
			// Porter le contenu à ébullition
			bouilli = true;
		}
	}

	public boolean estVide() { return vide;}
	public boolean estBouilli() { return bouilli;}
}
