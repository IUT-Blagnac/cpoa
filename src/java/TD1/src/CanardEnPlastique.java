
public class CanardEnPlastique extends Canard {

	public CanardEnPlastique() {
		comportementCancan = new Cancan();
		comportementVol = new NePasVoler();
	}

	@Override
	public void afficher() {
		System.out.println("Je suis un CanardEnPlastique!");
	}

}
