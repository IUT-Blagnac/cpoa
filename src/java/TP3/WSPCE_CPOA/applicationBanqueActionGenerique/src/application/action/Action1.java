package application.action;

import banque.AgenceBancaire;
import banque.Compte;
import banque.exception.ABCompteDejaExistantException;
import banque.exception.ABCompteNullException;

public class Action1 extends GenericAction {

	public Action1(String m, String c) {
		super(m, c);
	}

	@Override
	public void execute(AgenceBancaire ab) throws Exception {
		
		System.out.println("\n\n\n");
		System.out.println(this.actionMessage());
		System.out.println("\n\n\n");

		System.out.println(ab.getNomAgence());
		System.out.println(ab.getLocAgence());
		ab.afficher();
		try {
			ab.addCompte(new Compte("888888", "BlaBla"));
		} catch (ABCompteNullException e) {
			
			e.printStackTrace();
		} catch (ABCompteDejaExistantException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
		ab.afficher();
		System.out.println("\n");
		System.out.println("\n\n\n");
		
		
	}
}
