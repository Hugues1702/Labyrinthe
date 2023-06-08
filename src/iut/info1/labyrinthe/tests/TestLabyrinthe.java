package iut.info1.labyrinthe.tests;

import static iut.info1.labyrinthe.Labyrinthe.EST;
import static iut.info1.labyrinthe.Labyrinthe.NORD;
import static iut.info1.labyrinthe.Labyrinthe.OUEST;
import static iut.info1.labyrinthe.Labyrinthe.SUD;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import iut.info1.labyrinthe.Labyrinthe;
import iut.info1.labyrinthe.Salle;

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
        
        // renvoie la bonne valeur si valide
        assertEquals(aTester.getSalle(0, 0), aTester.getSalleAdjacente(1, 0, NORD));
        assertEquals(aTester.getSalle(0, 0), aTester.getSalleAdjacente(0, 1, OUEST));
    }
    
    @Test
    void testGetLigneSalle() {
        Salle salleIndep = new Salle(100);
        Labyrinthe laby = correctes.get(1);
        assertThrows(IllegalArgumentException.class, 
                        () -> laby.getLigneSalle(salleIndep));
        Salle salleExtraite;
        salleExtraite = laby.getSalle(1, 1);
        assertEquals(1, laby.getLigneSalle(salleExtraite));
        
        salleExtraite = laby.getSalle(2, 0);
        assertEquals(2, laby.getLigneSalle(salleExtraite));
    }
    
    @Test
    void testGetColonneSalle() {
        Salle salleIndep = new Salle(100);
        Labyrinthe laby = correctes.get(1);
        assertThrows(IllegalArgumentException.class, 
                        () -> laby.getColonneSalle(salleIndep));
        Salle salleExtraite;
        salleExtraite = laby.getSalle(1, 1);
        assertEquals(1, laby.getColonneSalle(salleExtraite));
        
        salleExtraite = laby.getSalle(2, 0);
        assertEquals(0, laby.getColonneSalle(salleExtraite));
    } 
    
    @Test
    void testIsPorteAdjacente() {
        for (Labyrinthe laby : correctes) {
                assertThrows(IllegalArgumentException.class, 
                                () -> laby.isPorteAdjacente(1, -1, NORD));
                assertThrows(IllegalArgumentException.class, 
                                () -> laby.isPorteAdjacente(-1, 1, NORD));
                assertThrows(IllegalArgumentException.class, 
                                () -> laby.isPorteAdjacente(1, 1, 6));
                                
                int ligneMax = laby.getNbLignes() - 1;
                int colonneMax = laby.getNbColonnes() - 1;
                assertFalse(laby.isPorteAdjacente(0, 0, NORD));
                assertFalse(laby.isPorteAdjacente(0, 0, OUEST));  
                
                assertFalse(laby.isPorteAdjacente(0, colonneMax, NORD));
                assertFalse(laby.isPorteAdjacente(0, colonneMax, EST));
                
                assertFalse(laby.isPorteAdjacente(ligneMax, colonneMax, SUD));
                assertFalse(laby.isPorteAdjacente(ligneMax, colonneMax, EST));
                
                assertFalse(laby.isPorteAdjacente(ligneMax, 0, SUD));
                assertFalse(laby.isPorteAdjacente(ligneMax, 0, OUEST));
                
                assertTrue(laby.isPorteAdjacente(0, 0, EST) 
                                || laby.isPorteAdjacente(0, 0, SUD));
                
                assertTrue(laby.isPorteAdjacente(ligneMax, colonneMax, NORD) 
                                || laby.isPorteAdjacente(ligneMax, colonneMax, OUEST));
        }
    }
    
    @Test
    void testHasSalleAdjacente() {
        for (Labyrinthe laby : correctes) {
                assertThrows(IllegalArgumentException.class, 
                                () -> laby.isPorteAdjacente(1, -1, NORD));
                assertThrows(IllegalArgumentException.class, 
                                () -> laby.isPorteAdjacente(-1, 1, NORD));
                assertThrows(IllegalArgumentException.class, 
                                () -> laby.isPorteAdjacente(1, 1, 6));
                
                int ligneMax = laby.getNbLignes() - 1;
                        int colonneMax = laby.getNbColonnes() - 1;
                        
                assertFalse(laby.hasSalleAdjacente(0, 0, NORD));
                assertFalse(laby.hasSalleAdjacente(0, 0, OUEST));
                
                assertFalse(laby.hasSalleAdjacente(0, colonneMax, NORD));
                assertFalse(laby.hasSalleAdjacente(0, colonneMax, EST));
                
                assertFalse(laby.hasSalleAdjacente(ligneMax, colonneMax, SUD));
                assertFalse(laby.hasSalleAdjacente(ligneMax, colonneMax, EST));
                
                assertFalse(laby.hasSalleAdjacente(ligneMax, 0, SUD));
                assertFalse(laby.hasSalleAdjacente(ligneMax, 0, OUEST));
                
                assertTrue(laby.hasSalleAdjacente(1, 1, NORD));
                assertTrue(laby.hasSalleAdjacente(1, 1, OUEST));
                assertTrue(laby.hasSalleAdjacente(1, 1, SUD));
                assertTrue(laby.hasSalleAdjacente(1, 1, EST));
        }
    }

}