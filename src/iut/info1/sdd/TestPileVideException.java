package iut.info1.sdd;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class TestPileVideException {

	@Test
	void testPileVideException() {
		assertThrows(PileVideException.class, () -> {
			throw new PileVideException();
		});
	}

	@Test
	void testPileVideExceptionString() {
		assertThrows(PileVideException.class, () -> {
			throw new PileVideException("Sandwich");
		});
	}

}
