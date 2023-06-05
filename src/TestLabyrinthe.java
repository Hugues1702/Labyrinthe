package iut.info1.labyrinthe.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static iut.info1.labyrinthe.Labyrinthe.NORD;
import static iut.info1.labyrinthe.Labyrinthe.SUD;
import static iut.info1.labyrinthe.Labyrinthe.EST;
import static iut.info1.labyrinthe.Labyrinthe.OUEST;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import iut.info1.labyrinthe.Labyrinthe;

/**
 * Tests unitaire de la classe Labyrinthe.
 * @author BAUDROIT Leïla
 * @author BERTRAND Hugues
 * @author BOYER Djedline
 * @author BRIOT Nael
 */
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
    void testHashCode() {
        assertEquals(correctes.get(0).hashCode(), correctes.get(0).hashCode());
        assertEquals(correctes.get(1).hashCode(), correctes.get(1).hashCode());
        assertEquals(correctes.get(2).hashCode(), correctes.get(2).hashCode());

    }

    @Test
    void testToString() {
        /* Le labyrinthe étant généré aléatoirement, il est difficile de
         * faire ce test.
         */
    }
    
    @Test
    void testResetMarques() {
        correctes.get(0).getSalle(0, 0).setMarque(1);
        correctes.get(0).getSalle(1, 2).setMarque(3);
        correctes.get(0).getSalle(2, 4).setMarque(16);
        correctes.get(0).resetMarques();
        assertEquals(0, correctes.get(0).getSalle(0, 0).getMarque());
        assertEquals(0, correctes.get(0).getSalle(1, 2).getMarque());
        assertEquals(0, correctes.get(0).getSalle(2, 4).getMarque());
    }
    
    @Test
    void testGetSalle() {
        assertThrows(IllegalArgumentException.class, () -> correctes.get(0).getSalle(-1, -1));
        assertThrows(IllegalArgumentException.class, () -> correctes.get(0).getSalle(-1, 0));
        assertThrows(IllegalArgumentException.class, () -> correctes.get(0).getSalle(0, -1));
        assertThrows(IllegalArgumentException.class, () -> correctes.get(0).getSalle(0, 5));
        assertThrows(IllegalArgumentException.class, () -> correctes.get(0).getSalle(3, 0));
        assertEquals(0, correctes.get(0).getSalle(0, 0).getIndex());
        assertEquals(14, correctes.get(0).getSalle(2, 4).getIndex());
    }
    
    @Test
    void testIsCoordonneesSalleValide() {
        assertTrue(correctes.get(0).isCoordonneesSalleValide(0, 0));
        assertTrue(correctes.get(0).isCoordonneesSalleValide(2, 4));
        assertTrue(correctes.get(0).isCoordonneesSalleValide(1, 2));
        assertFalse(correctes.get(0).isCoordonneesSalleValide(-1, 0));
        assertFalse(correctes.get(0).isCoordonneesSalleValide(0, -1));
        assertFalse(correctes.get(0).isCoordonneesSalleValide(3, 0));
        assertFalse(correctes.get(0).isCoordonneesSalleValide(0, 5));
    }
    
    @Test
    void testGetSalleAdjacente() {
        Labyrinthe aTester = correctes.get(0);
        
        // vérification des coordonnées
        assertThrows(IllegalArgumentException.class, () -> aTester.getSalleAdjacente(-1, -1, NORD));
        assertThrows(IllegalArgumentException.class, () -> aTester.getSalleAdjacente(-1, 0, NORD));
        assertThrows(IllegalArgumentException.class, () -> aTester.getSalleAdjacente(0, -1, NORD));
        assertThrows(IllegalArgumentException.class, () -> aTester.getSalleAdjacente(0, 5, NORD));
        assertThrows(IllegalArgumentException.class, () -> aTester.getSalleAdjacente(3, 0, NORD));
        
        // vérification de la direction
        assertThrows(IllegalArgumentException.class, () -> aTester.getSalleAdjacente(-1, -1, -1));
        assertThrows(IllegalArgumentException.class, () -> aTester.getSalleAdjacente(-1, -1, 4));
        assertThrows(IllegalArgumentException.class, () -> aTester.getSalleAdjacente(-1, -1, Integer.MAX_VALUE));
        
        // vérification des murs extérieurs
        assertThrows(IllegalArgumentException.class, () -> aTester.getSalleAdjacente(0, 0, NORD));
        assertThrows(IllegalArgumentException.class, () -> aTester.getSalleAdjacente(0, 2, NORD));
        assertThrows(IllegalArgumentException.class, () -> aTester.getSalleAdjacente(0, 0, OUEST));
        assertThrows(IllegalArgumentException.class, () -> aTester.getSalleAdjacente(2, 0, OUEST));
        assertThrows(IllegalArgumentException.class, () -> aTester.getSalleAdjacente(2, 4, SUD));
        assertThrows(IllegalArgumentException.class, () -> aTester.getSalleAdjacente(2, 2, SUD));
        assertThrows(IllegalArgumentException.class, () -> aTester.getSalleAdjacente(2, 4, EST));
        assertThrows(IllegalArgumentException.class, () -> aTester.getSalleAdjacente(1, 4, EST));
    }

}