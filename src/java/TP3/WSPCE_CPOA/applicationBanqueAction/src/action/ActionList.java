package action;

/**
 * An ActionList is an object that implements a end-user menu.<BR>
 * It is defined by a title (printed on top of the menu).<BR>
 * It is also defined by a list of different action objects that the menu manages.<BR>
 * It is attended to :<BR>
 * - display the end-user menu numbered from 1 (list of messages of actions).<BR>
 * - display a quit option (0).<BR>
 * - wait for some user response.<BR>
 * - launch the requested action.<BR>
 */
public interface ActionList extends Action {
	
	/**
	 * Title of the list of actions (menu).
	 * 
	 * @return the title of the action list 
	 */
	public String listTitle();

	/**
	 * The number of actions in the action list.
	 * 
	 * @return number of actions in the action list.
	 */
	public int size();
	
	/**
	 * Add an action at the end of the list action if it does not yet exists.
	 * 
	 * @param ac the action to add
	 * @return true if action is added, else false
	 */
	public boolean addAction(Action ac);
	
	/**
	 * Add an action in the list action at the specified index if it does not yet exists.
	 * 
	 * @param ac the action to add
	 * @param index index to add the action
	 * @return true if action is added, else false
	 * @throws IndexOutOfBoundsException if (index < 0) || (index > size())
	 */
	public boolean addAction(Action ac, int index);
	
	/**
	 * Remove an action from the list action at the specified index.
	 * 
	 * @param index index to remove the action
	 * @return true
	 * @throws IndexOutOfBoundsException if (index < 0) || (index > size())
	 */
	public boolean removeAction(int index);
	
	/**
	 * Remove an action from the list action.
	 * 
	 * @param ac the action to remove
	 * @return true if action is removed (found), else false
	 */
	public boolean removeAction(Action ac);
	
	/**
	 * List of the messages of actions contained in the action list
	 * 
	 * @return an array of messages of the list action
	 */
	public String[] listOfActions() ;
	
}
