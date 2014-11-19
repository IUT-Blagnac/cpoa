public class SousType extends Type { 
	void m() {
		System.out.println ("SousType"); 
	} 
	void autreM(){
		System.out.println ("Spécifique SousType");
	}

	public static void main(String[] args) {
		Type a = new Type(); 
		a.m(); // "Type"

		a = new SousType(); 
		a.m(); // "SousType"
		// Statique : a est un Type (à la compil)
		// Dynamique : a est un SousType au runtime.

		// D'où :
		a = new SousType(); 
		a.autreM(); 
		// NOK car type statique == A => autreM() n'existe pas à la compilation

	}
}