package application;

import java.io.PrintStream;
import java.util.Locale;
import java.util.Scanner;

import banque.AgenceBancaire;

public class ApplicationContextAgenceBancaire {

	public static ApplicationContextAgenceBancaire getApplicationContext () {
		return new ApplicationContextAgenceBancaire ();
	}
	
	private AgenceBancaire ag;
	private Scanner theScanner;
	
	private ApplicationContextAgenceBancaire () {
		this.ag = AccesAgenceBancaire.getAgenceBancaire();
		this.theScanner = new Scanner(System.in);
		this.theScanner.useLocale(Locale.US);
	}
	
	public Scanner getInputScanner () {
		return this.theScanner;
	}
	
	public PrintStream getOutputStream () {
		return System.out;
	}
	
	public AgenceBancaire getAgenceBancaire () {
		return this.ag;
	}
}
