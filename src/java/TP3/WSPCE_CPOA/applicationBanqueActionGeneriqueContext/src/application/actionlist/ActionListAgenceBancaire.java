package application.actionlist;

import java.util.ArrayList;
import java.util.List;

import action.Action;
import action.ActionList;
import application.ApplicationContextAgenceBancaire;
import application.action.GenericAction;

public class ActionListAgenceBancaire
	extends GenericAction
	implements ActionList<ApplicationContextAgenceBancaire> {

	private String title;
	private List<Action<ApplicationContextAgenceBancaire>> myMenu;
	
	public ActionListAgenceBancaire (String m, String c, String t) {
		super (m, c);
		this.title = t;
		this.myMenu = new ArrayList<>();
	}
	
	@Override
	public String listTitle() {
		return this.title;
	}

	@Override
	public boolean addAction(Action<ApplicationContextAgenceBancaire> ac, int index) {
		for (Action<ApplicationContextAgenceBancaire> myAc : this.myMenu) {
			if (ac == myAc) {
				return false;
			}
		}
		this.myMenu.add(index, ac);
		return true;
	}

	@Override
	public boolean addAction(Action<ApplicationContextAgenceBancaire> ac) {
		return this.addAction(ac, this.myMenu.size());
	}

	@Override
	public boolean removeAction(Action<ApplicationContextAgenceBancaire> ac) {
		for (int i=0; i<this.myMenu.size(); i++) {
			if (ac == this.myMenu.get(i)) {
				this.myMenu.remove (i);
				return true;
			}
		}
		return false;
	}

	@Override
	public String[] listOfActions() {
		ArrayList<String> res = new ArrayList<>();
		
		for (Action<ApplicationContextAgenceBancaire> myAc : this.myMenu) {
			res.add(myAc.actionMessage());
		}
		return res.toArray(new String[0]);
	}
	
	@Override
	public boolean removeAction(int index) {
		this.myMenu.remove(index);
		return true;
	}
	
	@Override
	public int size() {
		return this.myMenu.size();
	}

	@Override
	public void execute(ApplicationContextAgenceBancaire ac) throws Exception {
		int i;
		int choice;
		
		while (true) {
			for (i=0; i<5; i++)
				ac.getOutputStream().println();
			
			this.printTitle(ac);
			this.printMenu(ac);
					
			choice = this.readResponse(ac);
			
			if (choice == 0) {
				return ;
			}
			else {
				try {
					this.myMenu.get(choice -1).execute(ac);
				} catch (Exception e) {
					// To do
				}
				ac.getOutputStream().println("Fin de "+myMenu.get(choice -1).actionMessage());
				this.tempo(ac);
			}
		}
	}

	private int readResponse(ApplicationContextAgenceBancaire ac) {
		boolean ok;
		String rep;
		int choice;
		
		choice = -1;
		do {
			ok = false;
			ac.getOutputStream().println("Votre choix ? ");
			rep = ac.getInputScanner().next();
			try {
				choice = Integer.parseInt(rep);
				if (choice == 0)
					ok = true;
				if ((choice-1)>=0 && (choice -1)<this.myMenu.size())
					ok=true;
			} catch (NumberFormatException nfe) { ok = false; }
			if (! ok)
				ac.getOutputStream().println("Erreur, nouvel essai ...");
		} while (!ok);
		return choice;
	}

	private void printMenu(ApplicationContextAgenceBancaire ac) {
		ac.getOutputStream().println("\n\n"+dec+"Choisir : ");
		int i;
		i=0;
		for (Action<ApplicationContextAgenceBancaire> myAc : this.myMenu) {
			ac.getOutputStream().println(decTwice+(i+1)+numberOptSep+myAc.actionMessage());
			i++;
		}
		ac.getOutputStream().println("\n"+decTwice+"0"+numberOptSep+"Pour quitter ce menu");
	}

	private void printTitle(ApplicationContextAgenceBancaire ac) {
		ac.getOutputStream().println("------------------");
		ac.getOutputStream().println(dec+"Agence "+ac.getAgenceBancaire().getNomAgence()
				+" de "+ac.getAgenceBancaire().getLocAgence());
		ac.getOutputStream().println(dec + this.title);
		ac.getOutputStream().println("------------------");
	}
	
	private void tempo (ApplicationContextAgenceBancaire ac) {
		ac.getOutputStream().print("Tapper un car + return pour continuer ... ");
		ac.getInputScanner().next(); // Inutile à stocker mais ... 
	}
	private final String dec="  ";
	private final String decTwice=dec+dec;
	private final String numberOptSep=" - ";
}
