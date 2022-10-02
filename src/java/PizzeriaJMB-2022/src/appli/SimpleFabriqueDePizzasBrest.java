package appli;

public class SimpleFabriqueDePizzasBrest extends SimpleFabriqueDePizzas {

	protected Pizza creerPizza(String type) {
		// TODO Auto-generated method stub
		Pizza pizza=null;
		if (type.equals("fromage")) {
			pizza = new PizzaFromageStyleBrest();
		} else if (type.equals("grecque")) {
			pizza = new PizzaGrecque();
		} else if (type.equals("poivrons")) {
			pizza = new PizzaPoivrons();
		}
		return pizza;
	}

}
