/*
 * ControleDeplacement.java                                    31 mai 2023
 * IUT Rodez, info1 2022-2023, pas de copyright ni "copyleft"
 */
package iut.info1.labyrinthe.controle;

import static iut.info1.labyrinthe.controle.MenuPrincipal.labyrintheActuel;
import static iut.info1.labyrinthe.controle.MenuPrincipal.joueur;
import static iut.info1.labyrinthe.controle.MenuPrincipal.nbLignes;
import static iut.info1.labyrinthe.controle.MenuPrincipal.nbColonnes;

/** 
 * Permet de gérer le déplacement de l'utilisateur dans un labyrinthe
 * @author Leïla Baudroit
 * @author Hugues Bertrand
 * @author Djedline Boyer
 * @author Nael Briot
 */
public class ControleDeplacement {

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
     * @throws IllegalArgumentException si deplacement impossible
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

    /** 
     * Permet de savoir si un deplacement à droite est possible
     * et si true alors on deplace l'utilisateur
     * @throws IllegalArgumentException si deplacement impossible
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

    /** 
     * Permet de savoir si un deplacement à gauche est possible
     * et si true alors on deplace l'utilisateur
     * @throws IllegalArgumentException si deplacement impossible
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

    /** 
     * Permet de marquer une salle que l'utilisateur vient de quitter
     * avec un caractère quelconque
     */
    public static void changementSalle() {
        labyrintheActuel.getSalle(joueur[0], joueur[1]).setSymbole(".");
    }

}
