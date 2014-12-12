/**
 * @author bruel (from O'Reilly Head-First series)
 *
 */
public class Pizzeria implements Interface {

	public Pizza commanderPizza(String type) {

		Pizza pizza;

		if (type.equals("fromage")) {
			pizza = new PizzaFromage();
		} else if (type.equals("grecque") {
			pizza = new PizzaGrecque();
		} else if (type.equals("poivrons") {
			pizza = new PizzaPoivrons();
		}

		pizza.preparer();
		pizza.cuire();
		pizza.couper();
		pizza.emballer();

		return pizza;
		}

}
