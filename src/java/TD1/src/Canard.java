/**
 * @author bruel
 * @navassoc - - comportementVol ComportementVol
 * @navassoc - - comportementCancan ComportementCancan
 */
abstract public class Canard {

	/**
	 * @overrideAssoc
	 */
	protected ComportementVol comportementVol;
	/**
	 * @overrideAssoc
	 */
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
