package application;

import banque.AgenceBancaire;
import action.ActionContext;

public class ActionContextAB
	implements ActionContext<AgenceBancaire> {
	
	private AgenceBancaire theAg;

	public ActionContextAB (AgenceBancaire ag) {
		this.theAg = ag; 
	}

	@Override
	public AgenceBancaire getContext() {
		return this.theAg;
	}

}
