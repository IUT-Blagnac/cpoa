/**
 * @author bruel
 * @depend - new - Pizza
 */
public class SimpleFabriqueDePizzas {

	public Pizza creerPizza(String type) {
		Pizza pizza;
		
		if (type.equals("fromage")) { 
			pizza = new PizzaFromage();
		} else if (type.equals("saumon")) { 
			pizza = new PizzaSaumon();
		} else if (type.equals("royale")) { 
			pizza = new PizzaRoyale();
		} else { 
			pizza = new PizzaVegetarienne();
		}

		return pizza;
	}

}
