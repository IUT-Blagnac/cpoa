package action;

public interface Action<E> {

	public String message();
	public String stringCode ();
	public void execute(ActionContext<E> ac);
}
