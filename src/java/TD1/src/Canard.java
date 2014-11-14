abstract public class Canard {

	protected ComportementVol comportementVol;
	protected ComportementCancan comportementCancan;

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
