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
		System.out.println(new Labyrinthe(3, 3));
		System.out.println(new Labyrinthe(4, 4));
		System.out.println(new Labyrinthe(5, 5));
		System.out.println(new Labyrinthe(6, 6));
	}

}