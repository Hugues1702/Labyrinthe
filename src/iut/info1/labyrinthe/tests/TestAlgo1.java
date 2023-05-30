package iut.info1.labyrinthe.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import iut.info1.labyrinthe.Labyrinthe;

class TestAlgo1 {

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void testLabyrinthe() {
		new Labyrinthe(3,3);
		new Labyrinthe(4,4);
		new Labyrinthe(5,5);
		new Labyrinthe(6,6);
	}

}