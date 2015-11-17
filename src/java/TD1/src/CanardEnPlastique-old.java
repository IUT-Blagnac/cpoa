
public class CanardEnPlastique extends Canard {

	@Override
	public void cancaner() {
		System.out.println("Je couine puisque je suis en plastique!");
	}

	@Override
	public void nager() {
		System.out.println("Je flotte comme du plastique!");
	}

	@Override
	public void voler() {
		System.out.println("Je ne vole pas, je suis en plastique!!!");
	};

	@Override
	public void afficher() {
		System.out.println("Je suis un CanardEnPlastique!");
	}

}
