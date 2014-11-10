abstract public class Canard {

	ComportementVol comportementVol;
	ComportementCancan comportementCancan;

	public void effectuerCancan() {
		comportementCancan.cancaner();
	}

	public void nager() {
		System.out.println("Je nage comme un Canard!");
	}

	abstract public void afficher();

	public void effectuerVol() {
		comportementVol.voler();
	}

}
