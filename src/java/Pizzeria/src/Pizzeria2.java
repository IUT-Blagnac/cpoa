/**
 * @author bruel (from O'Reilly Head-First series)
 */
public class Pizzeria2 {

	SimpleFabriqueDePizzas fabrique;
	
	public Pizzeria(SimpleFabriqueDePizzas fabrique) { 
		this.fabrique = fabrique;
	}
	
	Pizza commanderPizza(String type) { 

		Pizza pizza;
		￼￼
		pizza = fabrique.creerPizza(type);
		
		￼pizza.preparer(); 
		pizza.cuire(); 
		pizza.couper(); 
		pizza.emballer(); 

		return pizza;
	}

}
}