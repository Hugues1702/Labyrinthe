/*
 * ControleDeplacement.java                                    31 mai 2023
 * IUT Rodez, info1 2022-2023, pas de copyright ni "copyleft"
 */
package iut.info1.labyrinthe.controle;

import iut.info1.labyrinthe.Salle;
import static iut.info1.labyrinthe.controle.Controleur.labyrintheActuel;
import static iut.info1.labyrinthe.controle.Controleur.joueur;
import static iut.info1.labyrinthe.controle.Controleur.nbLignes;
import static iut.info1.labyrinthe.controle.Controleur.nbColonnes;

/** TODO comment class responsibility (SRP)
 * @author Djed
 *
 */
public class ControleDeplacement {
    
    private static final int nbLigne = 0;
    private final char HAUT ='H';
    private final char BAS ='B';
    private final char DROITE ='D';
    private final char GAUCHE ='G';

    /** 
     * Permet à l'utilisateur de se déplacer dans le labyrinthe.
     * @param salleAVerif 
     * @return la nouvelle position de l'utilisateur
     */
    public static int deplacement(Salle salleAVerif) {

        return 0;
    }
    
    /** TODO comment method role
     * @param ligne
     * @param colonne
     */
    public void deplacementHaut(int ligne, int colonne) {
        int coordonneeAModif = joueur[0];
        boolean deplacementPossible;
        if (deplacementHorizontale(coordonneeAModif = joueur[0])) {
            deplacementPossible = labyrintheActuel.getSalle(joueur[0], joueur[1]).isPorteNord();
            if (deplacementPossible) {
                joueur[0]++;
            }
        }
    }
    
    /** 
     * VérifPorte
     * @param coordonneeAVerif 
     * @return true si deplacement possible
     *         false si non
     */
    public boolean deplacementHorizontale(int coordonneeAVerif) {
        //salleActuel
        return joueur[0] >= 0 && joueur[0] <= nbLigne;
    }
}
