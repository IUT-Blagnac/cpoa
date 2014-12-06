import static org.junit.Assert.*;

import org.junit.Test;

/**
 * [*] --> Vide
 * Vide --> Courant : push()
 * Vide --> Vide : pull()
 * Courant --> Plein : push() [size() = MAX-1]
 * Courant --> Courant : push()  [size() < MAX-1]
 * Plein --> Courant : pull()
 * Plein --> Plein : push()
 * Courant --> Courant : pull() [size() > 1]
 * Courant --> Vide : pull() [size() = 1]

 */

/**
 * @author bruel
 *
 */
public class StackTest {

	static final int TAILLE = 5;
	@Test
	public void test_taille() {
		Stack s = new Stack(TAILLE);
		assertEquals(5, s.size());
	}

	// [*] --> Vide
	@Test
	public void test_empty_at_first() {
		Stack s = new Stack(TAILLE);
		assertEquals(true, s.est_vide());
	}

	//  Vide --> Courant : push()
	@Test
	public void test_NOTempty_after_first_push() {
		Stack s = new Stack(TAILLE);
		s.push(666);
		assertEquals(false, s.est_vide());
		assertEquals(true, s.est_courant());
	}

	//  Courant --> Plein : push() [size() = MAX-1]
	@Test
	public void test_full_after_enough_push() {
		Stack s = new Stack(TAILLE);
		s.push(1);s.push(2);s.push(3);s.push(4);
		assertEquals(true, s.est_courant());
		s.push(5);
		assertEquals(true, s.est_vide());
	}

}
