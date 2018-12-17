package appli;

/**
 * @author bruel (from O'Reilly Head-First series)
 * First version, without pattern
 */
public class Pizzeria {

	public Pizza commanderPizza(String type) {

		Pizza pizza=null;

		if (type.equals("fromage")) {
			pizza = new PizzaFromage();
		} else if (type.equals("grecque")) {
			pizza = new PizzaGrecque();
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
