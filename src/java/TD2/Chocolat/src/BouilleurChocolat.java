/**
<<<<<<< HEAD
 * @author bruel (taken from Design Pattren - Head First, O'Reilly, 09/2004)
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
			bouilli = false;
			// remplir le bouilleur du mélange lait/chocolat 
=======
 * @author bruel (taken from Design Pattern - Head First, O'Reilly, 09/2004)
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
			bouilli = false;
			// remplir le bouilleur du mélange lait/chocolat
>>>>>>> master
		}
	}

	public void vider() {
<<<<<<< HEAD
		if (!estVide() && estBouilli()) { 
=======
		if (!estVide() && estBouilli()) {
>>>>>>> master
			// vider le mélange
			vide = true;
		}
	}

	public void bouillir() {
		if (!estVide() && !estBouilli()) {
			// porter le contenu à ébullition
<<<<<<< HEAD
			bouilli = true; 
=======
			bouilli = true;
>>>>>>> master
		}
	}

	public boolean estVide() { return vide;}

<<<<<<< HEAD
	public boolean estBouilli() { return bouilli;} 
}
=======
	public boolean estBouilli() { return bouilli;}
}
>>>>>>> master
