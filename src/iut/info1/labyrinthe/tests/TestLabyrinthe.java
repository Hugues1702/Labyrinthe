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
        correctes.add( new Labyrinthe(4, 4));
        
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
        int[] RESULTAT = { 5, 8, 4 };
        for(int i = 0 ; i < correctes.size() ; i++) {
            assertEquals(RESULTAT[i], correctes.get(i).getNbColonnes());
        }
    }

    @Test
    void testGetNbLignes() {
        int[] RESULTAT = { 3, 8, 4 };
        for(int i = 0 ; i < correctes.size() ; i++) {
            assertEquals(RESULTAT[i], correctes.get(i).getNbLignes());
        }
    }

    @Test
    void testGetTableau() {
        int[][] RESULTAT = {
                {0, 1, 2},
                {3, 4, 5},
                {6, 7, 8}
        };
        assertArrayEquals(RESULTAT, new Labyrinthe(3, 3).getTableau());
    }

    @Test
    void testHashCode() {
        assertEquals(correctes.get(0).hashCode(), correctes.get(0).hashCode());
        assertEquals(correctes.get(1).hashCode(), correctes.get(1).hashCode());
        assertEquals(correctes.get(2).hashCode(), correctes.get(2).hashCode());

    }

    @Test
    void testToString() {
     String RESULTAT_ATTENDU = 
        " ----  ----  ----  ---- \n"
         + "|   0 |   1 |   2 |   3 |\n"
         + " ----  ----  ----  ---- \n"
         + "|   4 |   5 |   6 |   7 |\n"
         + " ----  ----  ----  ---- \n"
         + "|   8 |   9 |  10 |  11 |\n"
         + " ----  ----  ----  ---- \n"
         + "|  12 |  13 |  14 |  15 |\n"
         + " ----  ----  ----  ---- ";
     assertEquals(RESULTAT_ATTENDU,correctes.get(2).toString());
    }

}
