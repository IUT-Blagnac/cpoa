package application.action;


import banque.Compte;
import banque.exception.ABCompteDejaExistantException;
import banque.exception.ABCompteNullException;
import application.ApplicationContextAgenceBancaire;

public class Action1 extends GenericAction {

	public Action1(String m, String c) {
		super(m, c);
	}

	@Override
	public void execute(ApplicationContextAgenceBancaire ac) throws Exception {
		ac.getOutputStream().println("\n\n\n");
		ac.getOutputStream().println(this.actionMessage());
		ac.getOutputStream().println(ac.getAgenceBancaire().getNomAgence());
		ac.getOutputStream().println(ac.getAgenceBancaire().getLocAgence());
		ac.getAgenceBancaire().afficher();
		try {
			ac.getAgenceBancaire().addCompte(new Compte("888888", "BlaBla"));
		} catch (ABCompteNullException e) {
			
			e.printStackTrace();
		} catch (ABCompteDejaExistantException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		ac.getAgenceBancaire().afficher();
		ac.getOutputStream().println("\n");
		ac.getOutputStream().println("\n\n\n");
	}
}
