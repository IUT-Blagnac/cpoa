/**
 * @author bruel (taken from Design Pattren - Head First, O'Reilly, 09/2004)
 *
 */
public class BouilleurSafeChocolat { 
	private boolean vide;
	private boolean bouilli; 
	private static BouilleurSafeChocolat uniqueInstance;
	
	private BouilleurSafeChocolat() {
		vide = true;
		bouilli = false; 
		}
	
	public static final BouilleurSafeChocolat getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new BouilleurSafeChocolat();
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