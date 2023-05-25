package iut.info1.labyrinthe.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import iut.info1.labyrinthe.Salle;

class TestSalle {
	
	Salle NO;
	Salle NE;
	Salle SO;
	Salle SE;

	@BeforeEach
	void setUp() throws Exception {
		NO = new Salle(0);
		NE = new Salle(1);
		SO = new Salle(2);
		SE = new Salle(3);
	}

	@Test
	void testSalle() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Salle(-1);
		});
		assertThrows(IllegalArgumentException.class, () -> {
			new Salle(Integer.MIN_VALUE);
		});
		assertThrows(IllegalArgumentException.class, () -> {
			new Salle(-3000);
		});
	}

	@Test
	void testIsPorteNord() {
		assertFalse(NO.isPorteNord());
	}

	@Test
	void testSetPorteNord() {
		NO.setPorteNord(true);
		assertTrue(NO.isPorteNord());
		NO.setPorteNord(false);
		assertFalse(NO.isPorteNord());
	}

	@Test
	void testIsPorteOuest() {
		assertFalse(NO.isPorteOuest());
	}

	@Test
	void testSetPorteOuest() {
		NO.setPorteOuest(true);
		assertTrue(NO.isPorteOuest());
		NO.setPorteOuest(false);
		assertFalse(NO.isPorteOuest());
	}

	@Test
	void testGetIndex() {
		assertEquals(0, NO.getIndex());
		assertEquals(1, NE.getIndex());
		assertEquals(2, SO.getIndex());
		assertEquals(3, SE.getIndex());
	}

	@Test
	void testHashCode() {
		assertEquals(NO.hashCode(), NO.hashCode());
		assertEquals(NE.hashCode(), NE.hashCode());
		assertEquals(SO.hashCode(), SO.hashCode());
		assertEquals(SE.hashCode(), SE.hashCode());
	}

	@Test
	void testEqualsObject() {
		assertTrue(NO.equals(NO));
		assertFalse(NO.equals(NE));
	}
	
	@Test
	void testGetMarque() {
		assertEquals(0, NO.getMarque());
	}
	
	@Test
	void testSetMarque() {
		NO.setMarque(1);
		assertEquals(1, NO.getMarque());
	}
}
