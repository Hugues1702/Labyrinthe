/*
 * Labyrinthe.java                                          11/05/2023
 * Ni copyright, ni copyleft
 */

package iut.info1.labyrinthe;

import java.io.Serializable;

/** 
 * Représente un labyrinthe rectangulaire, constitué de pièces 
 * originellement fermée par des murs.
 * @author BAUDROIT Leïla
 * @author BERTRAND Hugues
 * @author BOYER Djedline
 * @author BRIOT Nael
 *
 */
public class Labyrinthe implements Serializable {


    /** Entier représentant la direction nord */
    public static final int NORD = 0;

    /** Entier représentant la direction ouest*/
    public static final int OUEST = 1;

    /** Entier représentant la direction sud*/
    public static final int SUD = 2;

    /** Entier représentant la direction est*/
    public static final int EST = 3;

    /** nombre de colonne dans le labyrinthe */
    private int nbColonnes;
    
    /** nombre de ligne dans le labyrinthe */
    private int nbLignes;
    
    /** Contient toutes les salles et sera converti en labyrinthe */
    private Salle[][] tableau;

    /**
     * Crée un labyrinthe en fonction d'un nombre de salles en longueur
     * et en hauteur.
     * @param lignes le nombre de lignes pour le créer le labyrinthe
     * @param colonnes le nombre de colonnes pour le créer le labyrinthe
     */
    public Labyrinthe(int lignes, int colonnes) {
        if (!isValide(lignes, colonnes)) {
            throw new IllegalArgumentException("Un labyrinthe de ces "
                        + "dimensions ne peut exister");
        }
        this.nbColonnes = colonnes;
        this.nbLignes = lignes;
        this.tableau = new Salle[nbLignes][nbColonnes];
        int index = 0;

        for (int ligne = 0 ; ligne < tableau.length ; ligne++) {
            for (int colonne = 0 ; colonne < tableau[ligne].length ; 
                        colonne++) {
                tableau[ligne][colonne] = new Salle(index);
                index++;
            }
        }
        generationChaineAscendante();
    }

    /**
     * Vérifie que le labyrinthe soit valide.
     * @param lignes du labyrinthe
     * @param colonnes du labyrinthe
     * @return true si le labyrinthe peut être crée 
     *         false sinon
     */
    private static boolean isValide(int lignes, int colonnes) {
        return lignes >= 3 && colonnes >= 3;
    }

    /**
     * Perce des portes à la place des murs du labyrinthe au hasard.
     */
    private void generationChaineAscendante() {
        final int NB_OPERATIONS_MAX
        = (getNbLignes() * getNbColonnes()) - 1;

        Salle salleRandom;
        Salle salleAdjacente;
        int marque = 1;
        int ligneHasard;
        int colonneHasard;
        boolean murNord;

        for (int nbOperations = 0 ; nbOperations < NB_OPERATIONS_MAX ; 
                nbOperations++) {
            do {
                do {
                    ligneHasard = (int) (Math.random() * getNbLignes());
                    colonneHasard = (int) (Math.random()
                                                  * getNbColonnes());
                    murNord = (int) Math.round(Math.random()) == 0;
                    salleRandom = tableau[ligneHasard][colonneHasard];
                } while (isMurExterieur(ligneHasard, colonneHasard, 
                                                                murNord)
                        || isMurDejaPerce(salleRandom, murNord));
                if (murNord) {
                    salleAdjacente = tableau[ligneHasard - 1]
                            [colonneHasard];
                } else {
                    salleAdjacente = tableau[ligneHasard]
                            [colonneHasard - 1];
                }
            } while (  salleRandom.getMarque()
                        == salleAdjacente.getMarque()
                    && salleRandom.getMarque() != 0);
            if (murNord) {
                salleRandom.setPorteNord(true);
            } else {
                salleRandom.setPorteOuest(true);
            }
            marque = changerMarques(salleRandom, salleAdjacente, marque);
        }
    }

    /**
     * Remet à 0 les marques de toutes les salles du labyrinthe.
     */
    public void resetMarques() {
        for (int ligne = 0 ; ligne < tableau.length ; ligne++) {
            for (int colonne = 0 ; colonne < tableau[ligne].length ; 
                        colonne++) {
                tableau[ligne][colonne].setMarque(0);
            }
        }
    }

    /**
     * Permet de décider du remplacement des marques pour l'algorithme
     * de génération.
     * @param premiereSalle la salle sélectionnée
     * @param deuxiemeSalle la salle adjacente à la première
     * @param marqueActuelle la marque à appliquer en cas de salles 
     *            sans marques
     * @return un entier représentant la valeur de la marque
     */
    private int changerMarques(Salle premiereSalle, Salle deuxiemeSalle,
            int marqueActuelle){
        int marqueSalle = premiereSalle.getMarque();
        int marqueAdjacent = deuxiemeSalle.getMarque();

        if (marqueAdjacent == marqueSalle && marqueSalle == 0) {
            premiereSalle.setMarque(marqueActuelle);
            deuxiemeSalle.setMarque(marqueActuelle);
            return ++marqueActuelle;
        } else {
            if (marqueSalle == 0 && marqueAdjacent != 0) {
                premiereSalle.setMarque(marqueAdjacent);
            } else if (marqueAdjacent == 0 && marqueAdjacent == 0){
                deuxiemeSalle.setMarque(marqueSalle);
            } else {
                remplacerMarque(marqueAdjacent, marqueSalle);
            }
        }
        return marqueActuelle;
    }

    /**
     * Remplace les marques de tous les sommets du tableau portant 
     * marqueDepart par marqueFinale
     * @param marqueInitiale la marque de départ
     * @param marqueFinale la marque finale
     */
    private void remplacerMarque(int marqueInitiale, int marqueFinale) {
        for (int ligne = 0 ; ligne < tableau.length ; ligne++) {
            for (int colonne = 0 ; colonne < tableau[ligne].length ;
                        colonne++) {
                if (tableau[ligne][colonne].getMarque() 
                                == marqueInitiale) {
                    tableau[ligne][colonne].setMarque(marqueFinale);
                }
            }
        }
    }

    /**
     * Permet de reconnaître un mur comme mur extérieur.
     * @param ligne la ligne de la case concernée
     * @param colonne la colonne de la case concernée
     */
    private static boolean isMurExterieur(int ligne, int colonne,
            boolean murNord) {
        return (murNord && ligne == 0) || (!murNord && colonne == 0);
    }

    /**
     * Permet de savoir si le mur nord ou ouest d'une salle est une 
     * porte ou non.
     * @param salle la salle dont on veut connaitre les états de mur
     * @param murNord un booleen représentant le mur du nord si true
     *              ou le mur de l'ouest autrement
     * @return
     */
    private static boolean isMurDejaPerce(Salle salle, boolean murNord) {
        if (murNord) {
            return salle.isPorteNord();
        }
        return salle.isPorteNord();
    }
    /**
     * Permet de connaitre le nombre de ligne d'un labyrinthe
     * @return le nombre de lignes
     */
    public int getNbLignes() {
        return nbLignes;
    }

    /**
     * Permet de connaitre le nombre de colonne d'un labyrinthe
     * @return le nombre de colonnes
     */
    public int getNbColonnes() {
        return nbColonnes;
    }

    /**
     * Permet d'afficher la ligne où se trouve une salle
     * @param salleRecherchee la salle dont on veut la ligne
     * @return la ligne de cette salle
     */
    public int getLigneSalle(Salle salleRecherchee) {
        for (int ligne = 0 ; ligne < tableau.length ; ligne++) {
            for (int colonne = 0 ; colonne < tableau[ligne].length ; 
                        colonne++) {
                if (getSalle(ligne, colonne) == salleRecherchee) {
                    return ligne;
                }
            }
        }
        throw new IllegalArgumentException("Cette salle n'existe pas "
                        + "dans ce labyrinthe");
    }

    /**
     * Permet d'afficher la colonne où se trouve une salle
     * @param salleRecherchee la salle dont on veut la colonne
     * @return la colonne de cette salle
     */
    public int getColonneSalle(Salle salleRecherchee) {
        for (int ligne = 0 ; ligne < tableau.length ; ligne++) {
            for (int colonne = 0 ; colonne < tableau[ligne].length ; 
                        colonne++) {
                if (getSalle(ligne, colonne) == salleRecherchee) {
                    return colonne;
                }
            }
        }
        throw new IllegalArgumentException("Cette salle n'existe pas "
                        + "dans ce labyrinthe");
    }

    /**
     * Prédicat vérifiant qu'un nombre correspond bien à une direction.
     * @param direction l'entier à vérifier
     * @return true si la direction vaut une des constantes NORD, SUD,
     *         OUEST, EST
     */
    private boolean isDirectionValide(int direction) {
        return direction == NORD || direction == SUD 
                        || direction == OUEST || direction == EST;
    }

    /**
     * Pré-condition vérifiant la validité d'une direction avec les 
     * constantes de direction de la classe
     * @param direction la direction à vérifier
     * @throws IllegalArgumentException si la direction ne correspond
     * pas aux constantes de classe
     */
    private void preconditionDirectionValide(int direction) {
        if (!isDirectionValide(direction)) {
            throw new IllegalArgumentException(
                    String.format("Direction invalide : %d ne "
                                + "correspond à aucune direction", 
                                direction));
        }
    }

    /**
     * Requête renvoyant le booléen représentant la porte dans la 
     * direction indiquée par rapport à la salle.
     * @param ligne la ligne de la salle concernée
     * @param colonne la colonne de la salle concernée
     * @param direction la direction parmi les constantes NORD, OUEST,
     *        SUD, EST
     * @return true si le mur est une porte, false sinon
     * @throws IllegalArgumentException si les coordonnées ou la 
     *             direction sont invalides
     */
    public boolean isPorteAdjacente(int ligne, int colonne, 
                                                                int direction) {
        preconditionCoordonneesValides(ligne, colonne);
        preconditionDirectionValide(direction);
        boolean resultat = false;
        switch(direction) {
        case NORD -> {
            resultat = tableau[ligne][colonne].isPorteNord();
        }
        case OUEST -> {
            resultat = tableau[ligne][colonne].isPorteOuest();
        }
        case EST -> {
            if (colonne != getNbColonnes() - 1) {
                resultat = getSalleAdjacente(ligne, colonne, EST)
                                .isPorteOuest();
            }
        }
        case SUD -> {
            if (ligne != getNbLignes() - 1) {
                resultat = getSalleAdjacente(ligne, colonne, SUD)
                                .isPorteNord();
            }
        }
        default -> throw new IllegalArgumentException("Direction"
                        + " invalide  : " + direction);
        }
        return resultat;
    }

    /**
     * Prédicat de vérification de coordonnées.
     * @param ligne la ligne de la salle dont on veut vérifier les 
     *            coordonnées
     * @param colonne la colonne de la salle dont on veut vérifier les
     *        coordonnées
     * @return true si les coordonnées sont valides pour ce labyrinthe,
     *         false sinon
     */
    public boolean isCoordonneesSalleValide(int ligne, int colonne) {
        return       ligne >= 0 &&   ligne < getNbLignes() 
                && colonne >= 0 && colonne < getNbColonnes();
    }

    /**
     * Pré-condition vérifiant la validité de coordonnées dans le 
     * labyrinthe.
     * @param ligne la ligne de la salle dont on veut vérifier 
     *            l'existence
     * @param colonne la colonne de la salle dont on veut vérifier 
     *        l'existence
     * @throws IllegalArgumentException si les coordonnées ne 
     *             correspondent pas à une salle du labyrinthe.
     */
    private void preconditionCoordonneesValides(int ligne, int colonne) {
        if (!isCoordonneesSalleValide(ligne, colonne)) {
            throw new IllegalArgumentException(
                    String.format("Coordonnées invalides : [%d;%d]",
                            ligne, colonne));
        }
    }

    /**
     * Renvoie une salle selon sa ligne et sa colonne dans le labyrinthe.
     * La méthode isCoordonneesSalleValide peut être utilisée pour 
     * vérifier la validité des coordonnées.
     * @param ligne un nombre compris entre 0 et getNbLignes()-1
     * @param colonne un nombre compris entre 0 et getNbColonnes()-1
     * @return la Salle située à ces coordonnées.
     * @throws IllegalArgumentException si les coordonnées sont 
     *             invalides
     */
    public Salle getSalle(int ligne, int colonne) {
        preconditionCoordonneesValides(ligne, colonne);
        return tableau[ligne][colonne];
    }

    /**
     * Prédicat vérifiant la présence de salla adjacente à une autre 
     * dans une direction donné. Cette présence correspond à la 
     * recherche de murs extérieurs.
     * @param ligne la ligne de la salle recherchée
     * @param colonne la colonne de la salle recherchée
     * @param direction la direction dans laquelle on veut la salle 
     *        adjacente
     * @return true si il existe une salle dans cette direction pour 
     *         cette salle false sinon
     */
    public boolean hasSalleAdjacente(int ligne, int colonne, 
                                         int direction) {
        preconditionDirectionValide(direction);
        preconditionCoordonneesValides(ligne, colonne);
        if (direction == NORD) {
            return ligne != 0;
        } else if (direction == OUEST) {
            return colonne != 0;
        } else if (direction == SUD) {
            return ligne != (getNbLignes() - 1);
        } else if (direction == EST){
            return colonne != (getNbColonnes() - 1);
        }
        throw new IllegalArgumentException(); 
        //pour faire plaisir au compilateur
    }

    /**
     * Pré-condition vérifiant la validité de coordonnées dans le 
     * labyrinthe.
     * @param ligne la ligne de la salle dont on veut vérifier 
     *        l'existence
     * @param colonne la colonne de la salle dont on veut vérifier 
     *        l'existence
     * @throws IllegalArgumentException si les coordonnées ne 
     *         correspondent pas à une salle du labyrinthe.
     */
    private void preconditionSalleAdjacenteValide(int ligne, 
                int colonne, int direction) {
        if (!hasSalleAdjacente(ligne, colonne, direction)) {
            throw new IllegalArgumentException(
                    String.format("Pas de salle adjacente dans la"
                                + " direction %d : [%d;%d]",
                            direction, ligne, colonne));
        }
    }

    /**
     * Permet d'obtenir la salle adjacente à une autre avec ses 
     * coordonnées et la direction.
     * @param ligne la ligne de la salle valide dont on veut la salle 
     *        adjacente
     * @param colonne la colonne de la salle valide dont ont veut la 
     *        salle adjacente
     * @param direction la direction parmi les constantes NORD, OUEST,
     *        SUD, EST
     * @return la salle située à côté
     * @throws IllegalArgumentException si les coordonnées ou la 
     *         direction sont invalides, ou si les coordonnées de la 
     *         salle obtenue sont invalides
     */
    public Salle getSalleAdjacente(int ligne, int colonne, 
                                                           int direction) {
        preconditionSalleAdjacenteValide(ligne, colonne, direction);
        preconditionCoordonneesValides(ligne, colonne);
        preconditionDirectionValide(direction);
        int ligneAdjacente = ligne;
        int colonneAdjacente = colonne;
        if (direction == NORD) {
            ligneAdjacente--;
        } else if (direction == SUD) {
            ligneAdjacente++;
        } else if (direction == OUEST) {
            colonneAdjacente--;
        } else {
            colonneAdjacente++;
        }
        return tableau[ligneAdjacente][colonneAdjacente];
    }

    @Override
    public int hashCode() {
        return this.nbColonnes * 10 + this.nbLignes;
    }

    @Override
    public String toString() {
        String resultat = "";
        for (int ligne = 0 ; ligne < tableau.length ; ligne++) {
            for (int colonne = 0 ; colonne < tableau[ligne].length ; 
                        colonne++) {
                resultat += "+";
                resultat += tableau[ligne][colonne].isPorteNord() ?
                        "     " : "-----";
            }
            resultat += "+\n";
            for (int colonne = 0 ; colonne < tableau[ligne].length ; 
                        colonne++) {
                String murVertical = tableau[ligne][colonne]
                                .isPorteOuest() ? 
                        " ":"|";
                resultat += String.format(murVertical + "  " +
                        tableau[ligne][colonne].getPresenceJoueur()
                        + "  ");
            }
            resultat += "|\n";
        }
        for (int colonne = 0 ; colonne < nbColonnes ; colonne++) {
            resultat += "+-----";
        }
        resultat += "+\n";
        return resultat;
    }
}