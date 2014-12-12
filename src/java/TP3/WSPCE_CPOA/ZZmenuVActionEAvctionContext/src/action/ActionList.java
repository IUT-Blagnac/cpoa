package action;

public interface ActionList<E> extends Action<E>{
	
	public String title();
	
	public boolean addAction(Action<E> ac);
	public boolean removeAction(Action<E> ac);
	
	public int listSize();
	public Action<E> getAction (int index);	
}
