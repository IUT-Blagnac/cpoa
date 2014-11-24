package application.action;

import banque.AgenceBancaire;
import action.ActionContext;
import application.GenericActionAB;

public class Action2 extends GenericActionAB {

	public Action2(String m, String c) {
		super(m, c);
	}

	@Override
	public void execute(ActionContext<AgenceBancaire> ac) {
		
		System.out.println("\n\n\n");
		System.out.println(this.message());
		System.out.println("\n\n\n");
	}
}
