
import action.Action;
import action.ActionList;
import application.AccesAgenceBancaire;
import application.action.Action1;
import application.action.Action2;
import application.action.Action3;
import application.action.Action4;
import application.action.Action5;
import application.action.Action6;
import application.actionlist.ActionListAgenceBancaire;
import banque.AgenceBancaire;


public class MainTest {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {

		AgenceBancaire ag = AccesAgenceBancaire.getAgenceBancaire();
		
		Action<AgenceBancaire> a1 = new Action1("Liste des comptes de l'agence", "L1");
		Action<AgenceBancaire> a2 = new Action2("Voir un compte (par son num�ro)", "V2");
		Action<AgenceBancaire>  a3 = new Action3("Ajouter un compte", "");
		Action<AgenceBancaire>  a4 = new Action4("Supprimer un compte", "");
		Action<AgenceBancaire>  a5 = new Action5("D�poser de l'argent sur un compte", "");
		Action<AgenceBancaire>  a6 = new Action6("Retirer de l'argent sur un compte", "");
		
		ActionList<AgenceBancaire> al1 = new ActionListAgenceBancaire("Menu G�n�ral", "1", "Menu G�n�ral");
		ActionList<AgenceBancaire> al2 = new ActionListAgenceBancaire("Menu gestion des comptes", "2", "Menu gestion des comptes");
		ActionList<AgenceBancaire> al3 = new ActionListAgenceBancaire("Menu op�rations sur comptes", "3", "Menu op�rations sur comptes");
		
		al1.addAction(a1);
		al1.addAction(a2);
		al1.addAction(al3);
		al1.addAction(al2);

		al2.addAction(a3);
		al2.addAction(a4);

		al3.addAction(a5);
		al3.addAction(a6);

		System.out.println("coucou!\n");
		al1.execute(ag);
	}

}
