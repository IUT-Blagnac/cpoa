import static org.junit.Assert.*;

import org.junit.Test;

/**
[*] --> Vide
Vide --> Courant : push()
Vide --> Vide : pull()
Courant --> Plein : push() [size() = MAX-1]
Courant --> Courant : push()  [size() < MAX-1]
Plein --> Courant : pull()
Plein --> Plein : push()
Courant --> Courant : pull() [size() > 1]
Courant --> Vide : pull() [size() = 1]
@enduml
*/

public class TestPile {

	//[*] --> Vide
    @Test
	public void test_nouvelle_pile_vide_au_depart() {
		Pile p = new Pile();
		assertEquals(true, p.est_vide());
	}

	@Test
	public void test_pile_vide_plus_vide_apres_push() {
		Pile p = new Pile();
		assertEquals(true, p.est_vide());
		p.push(1);
		assertEquals(false,p.est_vide());
	}
}
