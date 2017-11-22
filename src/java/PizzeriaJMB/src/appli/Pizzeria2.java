package appli;

/**
 * @author bruel (from O'Reilly Head-First series)
 * Second version, with Factory pattern
 */
public class Pizzeria2 {

	SimpleFabriqueDePizzas fabrique=null;
	
	public Pizzeria2(SimpleFabriqueDePizzas fabrique) { 
		this.fabrique = fabrique;
	}
	
	Pizza commanderPizza(String type) { 

		Pizza pizza;
	
		pizza = fabrique.creerPizza(type);
		pizza.preparer();
		pizza.cuire(); 
		pizza.couper(); 
		pizza.emballer(); 

		return pizza;
	}
}