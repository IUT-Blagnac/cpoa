package application.action;

import application.ApplicationContextAgenceBancaire;

public class Action5 extends GenericAction  {

	public Action5(String m, String c) {
		super(m, c);
	}

	@Override
	public void execute(ApplicationContextAgenceBancaire ac) throws Exception {
		ac.getOutputStream().println("\n\n\n");
		ac.getOutputStream().println(this.actionMessage());
		ac.getOutputStream().println("\n\n\n");
	}
}
