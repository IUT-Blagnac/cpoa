package application.action;

import action.Action;
import application.ApplicationContextAgenceBancaire;

public abstract class GenericAction 
	implements Action<ApplicationContextAgenceBancaire> {

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
