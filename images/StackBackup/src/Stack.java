
public class Stack {
	
	Toto stack;
	
	private enum Etat {
		Vide,Courant,Plein
	}
	
	private int taille;
	private int cpt;
	private Etat etat;
	
	public Stack(int t) {
		this.setTaille(t);
		this.setCpt(0);
		etat = Etat.Vide;
	}

	public int size() {
		return taille;
	}

	public void setTaille(int taille) {
		this.taille = taille;
	}

	public int cpt() {
		return cpt;
	}

	public void setCpt(int cpt) {
		this.cpt = cpt;
	}

	public Boolean est_vide() {
		return (etat == Etat.Vide);
	}

	public void push(int i) {
		if (cpt() < taille-1) {
			etat = Etat.Courant;
			cpt++;
		} else if (cpt() == taille-1) {
			etat = Etat.Plein;
			cpt++;
		} else {
			etat = Etat.Plein;
		}
		System.out.println("pushing " + i + " cpt = "+cpt());
	}

	public Boolean est_courant() {
		return (etat == Etat.Courant);
	}

	public Boolean est_plein() {
		return (etat == Etat.Plein);
	}

}
