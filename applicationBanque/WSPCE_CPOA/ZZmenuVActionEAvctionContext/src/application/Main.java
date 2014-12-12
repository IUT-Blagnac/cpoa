package application;
import action.ActionContext;
import application.action.Action1;
import application.action.Action2;
import application.action.Action3;
import banque.AgenceBancaire;


public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		AgenceBancaire ag = new AgenceBancaire("Cep", "Pibrac");
		ActionContext<AgenceBancaire> acag = new ActionContextAB(ag);
		
		Action1 a1 = new Action1("Action 1", "7");
		Action2 a2 = new Action2("Action 2", "8");
		Action3 a3 = new Action3("Action 3", "9");
		
		ActionListAB al1 = new ActionListAB("Menu 1", "1", "Menu 1");
		ActionListAB al2 = new ActionListAB("Menu 2", "2", "Menu 2");
		
		al1.addAction(a1);
		al1.addAction(a2);
		al1.addAction(a3);
		al1.addAction(al2);

		al2.addAction(a1);
		al2.addAction(a2);
		al2.addAction(a3);

		al1.execute(acag);
	}

}
