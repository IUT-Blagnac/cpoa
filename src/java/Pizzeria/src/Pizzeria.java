/**
 * @author bruel taken from O'Reilly Head First Design Patterns
 *
 */
public class Pizzeria {

	public Pizza commanderPizza(String type) { 
		Pizza pizza = null;
		
		if (type.equals("fromage")) {
			pizza = new PizzaFromage();
		} else if (type.equals("grecque")) {
			pizza = new PizzaFromage();
		} else if (type.equals("poivrons")) {
			pizza = new PizzaPoivrons();
		}
		
		pizza.preparer(); 
		pizza.cuire(); 
		pizza.couper(); 
		pizza.emballer(); 
		return pizza;
	}

}
