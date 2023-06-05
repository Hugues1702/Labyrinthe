package iut.info1.labyrinthe.parcours;

import static iut.info1.labyrinthe.Labyrinthe.EST;
import static iut.info1.labyrinthe.Labyrinthe.NORD;
import static iut.info1.labyrinthe.Labyrinthe.OUEST;
import static iut.info1.labyrinthe.Labyrinthe.SUD;

import java.util.Arrays;

import iut.info1.labyrinthe.Labyrinthe;
import iut.info1.labyrinthe.Salle;
import iut.info1.sdd.Pile;

/**
 * Algorithme de parcours du labyrinthe en profondeur
 * @author BAUDROIT Leïla
 * @author BERTRAND Hugues
 * @author BOYER Djedline
 * @author BRIOT Nael
 */
public class ParcoursProfondeur {

    /** La marque attribuée par cet algorithme aux sommets parcourus.*/
    public static final int PARCOURU = 1;

    /**
     * Calcule le chemin de parcours optimal du labyrinthe de l'entrée en haut
     * à gauche à la sortie en bas à droite.
     * @param laby la labyrinthe que l'on souhaite parcourir.
     * @return une pile d'{@code iut.info1.labyrinthe.Salle} dans lequel 
     * le sommet est la dernière salle avant l'entrée et le premier 
     * élément l'entrée. Chaque élément de la pile est une étape du 
     * chemin optimal de parcours.
     */
    public static Pile parcours(Labyrinthe laby) {
        Pile pileSalles = new Pile();
        Salle entree = laby.getSalle(0, 0);
        Salle sortie = laby.getSalle(laby.getNbColonnes() - 1, 
                laby.getNbLignes() - 1);

        laby.resetMarques();
        entree.setMarque(PARCOURU);
        int ligneCourante = 0;
        int colonneCourante = 0;

        pileSalles.empiler(entree);
        Salle sommetCourant = (Salle) pileSalles.sommet();

        do {
            Salle[] accessibles = sallesAccessiblesNonParcourues(laby, 
                    ligneCourante, colonneCourante);
            if (accessibles.length == 0) {
                if (!pileSalles.estVide()) {
                    pileSalles.depiler();
                }
            } else {
                int nombreHasard = (int) 
                        Math.random() * accessibles.length;
                Salle sommetHasard = accessibles[nombreHasard];
                sommetHasard.setMarque(PARCOURU);
                pileSalles.empiler(sommetHasard);
            }

            // changement du sommetCourant
            if (!pileSalles.estVide()) {
                sommetCourant = (Salle) pileSalles.sommet();
            }
            ligneCourante = laby.getLigneSalle(sommetCourant);
            colonneCourante = laby.getColonneSalle(sommetCourant);
        } while (sommetCourant != sortie);
        laby.resetMarques();
        return pileSalles;
    }

    /**
     * Affiche la pile de Salle résultat d'un parcours en profondeur.
     * Dépile entièrement la Pile passée en paramètre.
     * @param laby 
     * @param cheminOptimal 
     * @param arriveeEnDernier 
     */
    public static void afficherAvecDepilage(Labyrinthe laby, Pile cheminOptimal,
            boolean arriveeEnDernier) {
        if (arriveeEnDernier) {
            System.out.println("Etapes à reculons de l'arrivée :");
        } else {
            System.out.println("Etapes depuis le départ :");
        }
        do {
            Salle salleAAfficher = (Salle) cheminOptimal.sommet();
            System.out.printf("[%d;%d]", 
                    laby.getLigneSalle(salleAAfficher),
                    laby.getColonneSalle(salleAAfficher));
            cheminOptimal.depiler();
            if (!cheminOptimal.estVide()) {
                System.out.print(", ");
            }
        } while(!cheminOptimal.estVide());
    }


    /**
     * @param laby le tableau dans lequel on cherche les salles
     * @param ligne la ligne de la salle dont on veut les salles 
     *          adjacentes
     * @param colonne la colonne de la salle dont on veut les salles 
     *          adjacentes
     * @return un tableau de salles accessibles et non parcourues 
     *          adjacentes à la salle
     */
    private static Salle[] sallesAccessiblesNonParcourues(Labyrinthe laby, int ligne, int colonne) {
        Salle[] accessibles = new Salle[4];
        int i = 0;

        Salle salleCourante = laby.getSalle(ligne, colonne);

        Salle salleNord;
        Salle salleOuest;
        Salle salleEst;
        Salle salleSud;

        if (laby.hasSalleAdjacente(ligne, colonne, NORD)) {
            salleNord = laby.getSalleAdjacente(ligne, colonne, NORD);
            if (salleCourante.isPorteNord() && !isParcouru(salleNord)) {
                accessibles[i] = salleNord;
                i++;
            }
        }

        if (laby.hasSalleAdjacente(ligne, colonne, OUEST)) {
            salleOuest = laby.getSalleAdjacente(ligne, colonne, OUEST);
            if (salleCourante.isPorteOuest() && !isParcouru(salleOuest)) {
                accessibles[i] = salleOuest;
                i++;
            }
        }

        if (laby.hasSalleAdjacente(ligne, colonne, EST)) {
            salleEst = laby.getSalleAdjacente(ligne, colonne, EST);
            if (salleEst.isPorteOuest() && !isParcouru(salleEst)) {
                accessibles[i] = salleEst;
                i++;
            }
        }

        if (laby.hasSalleAdjacente(ligne, colonne, SUD)) {
            salleSud = laby.getSalleAdjacente(ligne, colonne, SUD);
            if (salleSud.isPorteNord() && !isParcouru(salleSud)) {
                accessibles[i] = salleSud;
                i++;
            }
        }
        return Arrays.copyOf(accessibles, i);
    }

    /**
     * Vérifie la marque d'une salle.
     * @param salle la salle à vérifier
     * @return true si la marque de la salle vaut {@code ParcoursProfondeur#PARCOURU}
     */
    private static boolean isParcouru(Salle salle) {
        return salle.getMarque() == PARCOURU;
    }

}