
import action.Action;
import action.ActionList;
import application.ApplicationContextAgenceBancaire;
import application.action.Action1;
import application.action.Action2;
import application.action.Action3;
import application.action.Action4;
import application.action.Action5;
import application.action.Action6;
import application.actionlist.ActionListAgenceBancaire;

public class MainTest {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {

		ApplicationContextAgenceBancaire ac = ApplicationContextAgenceBancaire.getApplicationContext();
		
		Action<ApplicationContextAgenceBancaire> a1 = new Action1("Liste des comptes de l'agence", "L1");
		Action<ApplicationContextAgenceBancaire> a2 = new Action2("Voir un compte (par son numéro)", "V2");
		Action<ApplicationContextAgenceBancaire>  a3 = new Action3("Ajouter un compte", "");
		Action<ApplicationContextAgenceBancaire>  a4 = new Action4("Supprimer un compte", "");
		Action<ApplicationContextAgenceBancaire>  a5 = new Action5("Déposer de l'argent sur un compte", "");
		Action<ApplicationContextAgenceBancaire>  a6 = new Action6("Retirer de l'argent sur un compte", "");
		
		ActionList<ApplicationContextAgenceBancaire> al1 = new ActionListAgenceBancaire("Menu Général", "1", "Menu Général");
		ActionList<ApplicationContextAgenceBancaire> al2 = new ActionListAgenceBancaire("Menu gestion des comptes", "2", "Menu gestion des comptes");
		ActionList<ApplicationContextAgenceBancaire> al3 = new ActionListAgenceBancaire("Menu opérations sur comptes", "3", "Menu opérations sur comptes");
		
		al1.addAction(a1);
		al1.addAction(a2);
		al1.addAction(al3);
		al1.addAction(al2);
		
		al2.addAction(a3);
		al2.addAction(a4);

		al3.addAction(a5);
		al3.addAction(a6);
		
		al1.execute(ac);
	}

}
