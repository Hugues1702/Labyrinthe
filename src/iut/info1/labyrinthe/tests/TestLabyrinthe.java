package iut.info1.labyrinthe.tests;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import iut.info1.labyrinthe.Labyrinthe;

class TestLabyrinthe {

    List<Labyrinthe> correctes = new ArrayList<Labyrinthe>();

    @BeforeEach
    void setUp() throws Exception {
        correctes.clear();
        correctes.add( new Labyrinthe(3, 5));
        correctes.add( new Labyrinthe(8, 8));
    }

    @Test
    void testLabyrinthe() {
        assertThrows(IllegalArgumentException.class,() -> new Labyrinthe(-8, 5));
        assertThrows(IllegalArgumentException.class,() -> new Labyrinthe(8, -5));
        assertThrows(IllegalArgumentException.class,() -> new Labyrinthe(-8, -5));
        assertThrows(IllegalArgumentException.class,() -> new Labyrinthe(2, 2));
    }

    @Test
    void testGetNbColonnes() {
        int[] RESULTAT = { 5, 8 };
        for(int i = 0 ; i < correctes.size() ; i++) {
            assertEquals(RESULTAT[i], correctes.get(i).getNbColonnes());
        }
    }

    @Test
    void testGetNbLignes() {
        int[] RESULTAT = { 3, 8 };
        for(int i = 0 ; i < correctes.size() ; i++) {
            assertEquals(RESULTAT[i], correctes.get(i).getNbLignes());
        }
    }

    @Test
    void testGetTableau() {
        int[][] RESULTAT = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        assertArrayEquals(RESULTAT, new Labyrinthe(3, 3).getTableau());
    }

    @Test
    void testHashCode() {
        assertEquals(correctes.get(0).hashCode(), correctes.get(0).hashCode());
        assertEquals(correctes.get(1).hashCode(), correctes.get(1).hashCode());

    }

    @Test
    void testToString() {
    
    }

}
