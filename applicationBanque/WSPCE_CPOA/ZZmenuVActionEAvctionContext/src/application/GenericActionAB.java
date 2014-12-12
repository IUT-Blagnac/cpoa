package application;

import banque.AgenceBancaire;
import action.Action;
import action.ActionContext;

public abstract class GenericActionAB 
	implements Action<AgenceBancaire> {

	private String lineMessage, code; 

	public GenericActionAB (String m, String c) {
		this.lineMessage = m;
		this.code = c;
	}
	
	@Override
	public String message() {
		return this.lineMessage;
	}

	@Override
	public String stringCode() {
		return this.code;
	}

	@Override
	public void execute(ActionContext<AgenceBancaire> ac) {
		throw new UnsupportedOperationException();
	}
}
