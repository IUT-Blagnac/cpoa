package appli;

/**
 * @author bruel
 *
 */
public class PizzeriaTest {
	public static void main(String[] args) {
		Pizzeria2 boutiqueBrest = new PizzeriaBrest(new SimpleFabriqueDePizzasBrest());
		Pizzeria2 boutiqueStrasbourg = new PizzeriaStrasbourg(new SimpleFabriqueDePizzasStrasbourg());
		
		Pizza pizza = boutiqueBrest.commanderPizza("fromage"); 
		System.out.println("JMB a commandé une " + pizza.getNom() + "\n");
		
		pizza = boutiqueStrasbourg.commanderPizza("fromage");
		System.out.println("JMI a commandé une " + pizza.getNom() + "\n"); 
	}
}
