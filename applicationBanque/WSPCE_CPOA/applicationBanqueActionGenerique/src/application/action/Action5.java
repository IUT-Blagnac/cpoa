package application.action;

import banque.AgenceBancaire;

public class Action5 extends GenericAction  {

	public Action5(String m, String c) {
		super(m, c);
	}

	@Override
	public void execute(AgenceBancaire ab) throws Exception {
		
		System.out.println("\n\n\n");
		System.out.println(this.actionMessage());
		System.out.println("\n\n\n");
	}
}
