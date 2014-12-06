
public class Pile {

	private Boolean vide;
	
	public Pile() {
		this.vide = true;
	}

	public Boolean est_vide() {
		return vide;
	}

	public void push(int i) {
		vide = false;
	}

}
