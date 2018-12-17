package application;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;

import banque.AgenceBancaire;
import action.Action;
import action.ActionContext;
import action.ActionList;

public class ActionListAB
	extends GenericActionAB
	implements ActionList<AgenceBancaire> {

	private String title;
	private List<Action<AgenceBancaire>> myMenu;
	
	public ActionListAB (String m, String c, String t) {
		super (m, c);
		this.title = t;
		this.myMenu = new ArrayList<>();
	}
	
	@Override
	public String title() {
		return this.title;
	}

	@Override
	public boolean addAction(Action<AgenceBancaire> ac) {
		
		for (Action<AgenceBancaire> myAc : this.myMenu)
			if (ac == myAc)
				return false;
		this.myMenu.add(ac);
		return true;
	}

	@Override
	public boolean removeAction(Action<AgenceBancaire> ac) {
		
		ListIterator<Action<AgenceBancaire>> lit = this.myMenu.listIterator();
		while (lit.hasNext()) {
			Action<AgenceBancaire> myAc = lit.next();
			if (ac == myAc) {
				lit.remove();
				return true;
			}
		}
		return false;
	}

	@Override
	public int listSize() {
		return this.myMenu.size();
	}

	@Override
	public Action<AgenceBancaire> getAction(int index) {
		return this.myMenu.get(index);
	}

	@Override
	public void execute(ActionContext<AgenceBancaire> ac) {
		int i;
		int choice;
		
		while (true) {
			for (i=0; i<5; i++)
				System.out.println();
			
			this.printTitle(ac.getContext());
			this.printMenu();
					
			choice = this.readResponse();
			
			if (choice == 0) {
				return ;
			}
			else {
				this.myMenu.get(choice -1).execute(ac);
				System.out.println("End of "+myMenu.get(choice -1).message());
				this.tempo();
			}
		}
	}

	private int readResponse() {
		Scanner lect ;
		boolean ok;
		String rep;
		int choice;
		
		choice = -1;
		lect = new Scanner (System.in);
		do {
			ok = false;
			System.out.println("Your choice ? ");
			rep = lect.next();
			try {
				choice = Integer.parseInt(rep);
				if (choice == 0)
					ok = true;
				if ((choice-1)>=0 && (choice -1)<this.myMenu.size())
					ok=true;
			} catch (NumberFormatException nfe) { ok = false; }
			if (! ok)
				System.out.println("Bad choice, try again ...");
		} while (!ok);
		return choice;
	}

	private void printMenu() {
		System.out.println("\n\n"+dec+"Choose : ");
		int i;
		i=0;
		for (Action<AgenceBancaire> myAc : this.myMenu) {
			System.out.println(decTwice+(i+1)+numberOptSep+myAc.message());
			i++;
		}
		System.out.println("\n"+decTwice+"0"+numberOptSep+"To Quit this menu");
	}

	private void printTitle(AgenceBancaire ac) {
		System.out.println("------------------");
		System.out.println(dec+"Agence "+ac.getNomAgence()
				+" de "+ac.getLocAgence());
		System.out.println(dec + this.title);
		System.out.println("------------------");
	}
	
	private void tempo () {
		Scanner lect ;
		
		lect = new Scanner (System.in );
		
		System.out.print("Tapper un car + return pour continuer ... ");
		lect.next(); // Inutile à stocker mais ... 
	}
	private final String dec="  ";
	private final String decTwice=dec+dec;
	private final String numberOptSep=" - ";
}
