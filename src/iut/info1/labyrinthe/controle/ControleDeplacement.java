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
    public static void deplacementHaut() {
        boolean deplacementPossible = joueur[0] >= 0;
        if (deplacementPossible) {
            deplacementPossible = labyrintheActuel.getSalle(joueur[0], joueur[1]).isPorteNord();
            if (deplacementPossible) {
                changementSalle();
                joueur[0]--;
            }
        }
        if (!deplacementPossible) {
            throw new IllegalArgumentException();
         }
    }
    
    /** TODO comment method role
     * @param ligne
     * @param colonne
     * @throws si deplacement impossible
     */
    public static void deplacementBas() {
        boolean deplacementPossible = joueur[0]+1 < nbLignes;
        System.out.println(deplacementPossible);
        if (deplacementPossible) {
            deplacementPossible = labyrintheActuel.getSalle(joueur[0]+1, joueur[1]).isPorteNord();
            if (deplacementPossible) {
                changementSalle();
                joueur[0]++;
            }
        }
        if (!deplacementPossible) {
            throw new IllegalArgumentException();
         }
    }
    
    /** TODO comment method role
     * @param ligne
     * @param colonne
     */
    public static void deplacementDroite() {
        boolean deplacementPossible = joueur[1]+1 < nbColonnes;
        if (deplacementPossible) {
            deplacementPossible = labyrintheActuel.getSalle(joueur[0], joueur[1]+1).isPorteOuest();
            if (deplacementPossible) {
                changementSalle();
                joueur[1]++;
            }
        }
        if (!deplacementPossible) {
            throw new IllegalArgumentException();
         }
    }
    
    /** TODO comment method role
     * @param ligne
     * @param colonne
     */
    public static void deplacementGauche() {
        boolean deplacementPossible = joueur[1]>= 0;
        if (deplacementPossible) {
            deplacementPossible = labyrintheActuel.getSalle(joueur[0], joueur[1]).isPorteOuest();
            if (deplacementPossible) {
                changementSalle();
                joueur[0]--;
            }
        }
        if (!deplacementPossible) {
            throw new IllegalArgumentException();
         }
    }
    
    /** TODO comment method role
     * 
     */
    public static void changementSalle() {
        labyrintheActuel.getSalle(joueur[0], joueur[1]).setPresenceJoueur(false);
    }

}
