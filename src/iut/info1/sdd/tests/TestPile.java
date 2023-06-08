package iut.info1.sdd.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import iut.info1.sdd.Pile;
import iut.info1.sdd.PileVideException;

/**
 * Tests unitaires de la class Pile.
 * @author BAUDROIT Leïla
 * @author BERTRAND Hugues
 * @author BOYER Djedline
 * @author BRIOT Nael
 */
class TestPile {
	
	Pile pileVide;
	Pile pileString;

	@BeforeEach
	void setUp() throws Exception {
		pileVide = new Pile();
		pileString = new Pile();
	}

	@Test
	void testPile() {
		new Pile();
	}

	@Test
	void testEmpiler() {
		pileString.empiler("Chat");
		assertEquals("Chat", pileString.sommet());
		pileString.empiler("Chien");
		pileString.empiler("Loup");
		pileString.empiler("Tortue");
		pileString.empiler("Pitohui");
		pileString.empiler("Fou du Cap");
		pileString.empiler("Lynx");
		pileString.empiler("Pika");
		pileString.empiler("Loutre");
		pileString.empiler("Fouine");
		pileString.empiler("Cocodile");
	}
	

	@Test
	void testDepiler() {
		pileString.empiler("A garder");
		pileString.empiler("A depiler");
		pileString.depiler();
		assertEquals("A garder", pileString.sommet());
		
		pileVide.empiler("A dépiler");
		assertTrue(pileVide.depiler().estVide());
		
		assertThrows(PileVideException.class, () -> {
			pileVide.depiler();
		});
	}

	@Test
	void testSommet() {
		assertThrows(PileVideException.class, () -> {
			pileVide.sommet();
		});
	}

	@Test
	void testEstVide() {
		assertTrue(pileVide.estVide());
		pileString.empiler("Chat");
		assertFalse(pileString.estVide());
	}
}