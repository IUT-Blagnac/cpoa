package appli;

public class SimpleFabriqueDePizzasStrasbourg extends SimpleFabriqueDePizzas {

	protected Pizza creerPizza(String type) {
		// TODO Auto-generated method stub
		Pizza pizza=null;
		if (type.equals("fromage")) {
			pizza = new PizzaFromageStyleStrasbourg();
		} else if (type.equals("grecque")) {
			pizza = new PizzaGrecque();
		} else if (type.equals("poivrons")) {
			pizza = new PizzaPoivrons();
		}
		return pizza;
	}

}
