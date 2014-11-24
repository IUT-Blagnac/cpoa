
import action.Action;
import action.ActionContext;
import action.ActionList;
import application.ActionContextAB;
import application.ActionListAB;
import application.action.Action1;
import application.action.Action2;
import application.action.Action3;
import application.action.Action4;
import banque.AgenceBancaire;


public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		AgenceBancaire ag = new AgenceBancaire("Cep", "Pibrac");
		
		ActionContext<AgenceBancaire> acag = new ActionContextAB(ag);
		
		Action<AgenceBancaire> a1 = new Action1("Action 1", "Action 1");
		Action<AgenceBancaire> a2 = new Action2("Action 2", "Action 2");
		Action<AgenceBancaire>  a3 = new Action3("Action 3", "Action 3");
		Action<AgenceBancaire>  a4 = new Action4("Action 4", "Action 4");
		
		ActionList<AgenceBancaire> al1 = new ActionListAB("Menu 1", "1", "Menu 1");
		ActionList<AgenceBancaire> al2 = new ActionListAB("Menu 2", "2", "Menu 2");
		
		al1.addAction(a1);
		al1.addAction(a2);
		al1.addAction(al2);

		al2.addAction(a3);
		al2.addAction(a4);

		al1.execute(acag);
	}

}
