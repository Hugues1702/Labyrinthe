package iut.info1.labyrinthe.tests;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import iut.info1.labyrinthe.Labyrinthe;

class TestLabyrinthe {

	@BeforeEach
	void setUp() throws Exception {
		Labyrinthe labtest = new Labyrinthe(3, 5);
	}
		
	@Test
	void testLabyrinthe() {
		assertThrows(IllegalArgumentException.class,() -> new Labyrinthe(-8, 5));
	}

	@Test
	void testToString() {
		
	}

	@Test
	void testGetNbColonnes() {
		
	}

	@Test
	void testGetNbLignes() {
		
	}

	@Test
	void testGetTableau() {
		
	}

	@Test
	void testHashCode() {
		
	}

}
