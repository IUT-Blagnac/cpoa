package application.action;

import banque.AgenceBancaire;
import action.Action;

public abstract class GenericAction 
	implements Action<AgenceBancaire> {

	private String lineMessage, code; 

	public GenericAction (String m, String c) {
		this.lineMessage = m;
		this.code = c;
	}
	
	@Override
	public String actionMessage() {
		return this.lineMessage;
	}

	@Override
	public String actionCode() {
		return this.code;
	}
}
