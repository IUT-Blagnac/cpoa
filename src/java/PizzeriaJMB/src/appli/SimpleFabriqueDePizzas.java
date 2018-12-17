package appli;

public abstract class SimpleFabriqueDePizzas {

	protected Pizza creerPizza(String type) {
		// TODO Auto-generated method stub
		Pizza pizza=null;
		if (type.equals("fromage")) {
			pizza = new PizzaFromage();
		} else if (type.equals("grecque")) {
			pizza = new PizzaGrecque();
		} else if (type.equals("poivrons")) {
			pizza = new PizzaPoivrons();
		}
		return pizza;
	}
}
