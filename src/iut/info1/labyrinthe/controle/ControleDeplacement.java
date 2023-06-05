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

/** 
 * Permet de gérer le déplacement de l'utilisateur dans un labyrinthe
 * @author Djed
 *
 */
public class ControleDeplacement {
    

    /** 
     * Permet à l'utilisateur de se déplacer dans le labyrinthe.
     * @param salleAVerif 
     * @return la nouvelle position de l'utilisateur
     */
    public static int deplacement(Salle salleAVerif) {

        return 0;
    }
    
    /** 
     * Premet de savoir si un deplacement en haut est possible
     * et si true alors on deplace l'utilisateur
     * @param ligne la position du l'utilisateur dans les lignes du tableau
     * @param colonne la position du l'utilisateur dans les colonnes du tableau
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
    
    /** 
     * Premet de savoir si un deplacement en bas est possible
     * et si true alors on deplace l'utilisateur
     * @param ligne la position du l'utilisateur dans les lignes du tableau
     * @param colonne la position du l'utilisateur dans les colonnes du tableau
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
                joueur[1]--;
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
        labyrintheActuel.getSalle(joueur[0], joueur[1]).setSymbole(".");
    }

}
