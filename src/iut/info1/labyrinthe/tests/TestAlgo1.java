package iut.info1.labyrinthe.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import iut.info1.labyrinthe.Labyrinthe;
import iut.info1.labyrinthe.parcours.ParcoursProfondeur;

class TestAlgo1 {

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void testLabyrinthe() {
		Labyrinthe test = new Labyrinthe(4,4);
		System.out.println(test);
		ParcoursProfondeur.parcours(test);
	}

}