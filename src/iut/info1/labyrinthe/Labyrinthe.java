/*
 * Labyrinthe.java                                          11/05/2023
 * Ni copyright, ni 
 */

package iut.info1.labyrinthe;

/** 
 * Représente un labyrinthe rectangulaire, constitué de pièces originellement
 * fermée par des murs.
 * @author BAUDROIT Leïla
 * @author BERTRAND Hugues
 * @author BOYER Djedline
 * @author BRIOT Nael
 *
 */
public class Labyrinthe {

        private int nbColonnes;
        private int nbLignes;
        private Salle[][] tableau;

        /**
         * Crée un labyrinthe en fonction d'un nombre de salles en longueur
         * et en hauteur.
         * @param lignes le nombre de lignes de salles présentes
         * @param colonnes le nombre de colonnes de salles présentes
         */
        public Labyrinthe(int lignes, int colonnes) {
            if (!isValide(lignes, colonnes)) {
                    throw new IllegalArgumentException();
            }
            this.nbColonnes = colonnes;
            this.nbLignes = lignes;
            this.tableau = new Salle[nbLignes][nbColonnes];
            int index = 0;

            for (int ligne = 0 ; ligne < tableau.length ; ligne++) {
                    for (int colonne = 0 ; colonne < tableau[ligne].length ; colonne++) {
                            tableau[ligne][colonne] = new Salle(index);
                            index++;
                    }
            }
            generationChaineAscendante();
            
    }

    /**
     * Vérifie que le labyrinthe soit valide.
     * @param lignes
     * @param colonnes
     * @return True ou False selon si le labyrinthe peut être crée ou non
     */
    private static boolean isValide(int lignes, int colonnes) {
            return lignes >= 3 && colonnes >= 3;
    }

        @Override
        public int hashCode() {
                return this.nbColonnes * 10 + this.nbLignes;
        }

        @Override
        public String toString() {
                String resultat = "";
                for (int ligne = 0 ; ligne < tableau.length ; ligne++) {
                        for (int colonne = 0 ; colonne < tableau[ligne].length ; colonne++) {
                                resultat += "+";
                                resultat += tableau[ligne][colonne].isPorteNord() ?
                                                "     " : "-----";
                        }
                        resultat += "+\n";
                        for (int colonne = 0 ; colonne < tableau[ligne].length ; colonne++) {
                                String murVertical = tableau[ligne][colonne].isPorteOuest() ? 
                                                " ":"|";
                                resultat += String.format(murVertical + "  " +
                                                tableau[ligne][colonne].getPresenceJoueur()
                                                + "  ");
                        }
                        resultat += "|\n";
                }
                for (int colonne = 0 ; colonne < nbColonnes ; colonne++) {
                        resultat += "------";
                }
                resultat += "-\n";
                return resultat;
        }

        /**
         * Permet de reconnaître un mur comme mur extérieur.
         * @param ligne la ligne de la case concernée
         * @param colonne la colonne de la case concernée
         */
        private boolean isMurExterieur(int ligne, int colonne,
                        boolean murNord) {
                return (murNord && ligne == 0) || (!murNord && colonne == 0);
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
                                        colonneHasard = (int) (Math.random() * getNbColonnes());
                                        murNord = (int) Math.round(Math.random()) == 0;
                                        salleRandom = tableau[ligneHasard][colonneHasard];
                                } while (isMurExterieur(ligneHasard, colonneHasard, murNord)
                                                || isMurDejaPerce(salleRandom, murNord));
                                if (murNord) {
                                        salleAdjacente = tableau[ligneHasard - 1]
                                                        [colonneHasard];
                                } else {
                                        salleAdjacente = tableau[ligneHasard]
                                                        [colonneHasard - 1];
                                }
                        } while (salleRandom.getMarque() == salleAdjacente.getMarque()
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
         * Permet de savoir si le mur nord ou ouest d'une salle est une 
         * porte ou non.
         * @param salle la salle dont on veut connaitre les états de mur
         * @param murNord un booleen représentant le mur du nord si true
         *              ou le mur de l'ouest autrement
         * @return
         */
        private boolean isMurDejaPerce(Salle salle, boolean murNord) {
                if (murNord) {
                        return salle.isPorteNord();
                }
                return salle.isPorteNord();
        }

        /**
         * Permet de décider du remplacement des marques pour l'algorithme 1.
         * @param premiereSalle la salle sélectionnée
         * @param deuxiemeSalle la salle adjacente à la première
         * @param marqueActuelle la marque à appliquer en cas de salles sans marques
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
                        for (int colonne = 0 ; colonne < tableau[ligne].length ; colonne++) {
                                if (tableau[ligne][colonne].getMarque() == marqueInitiale) {
                                        tableau[ligne][colonne].setMarque(marqueFinale);
                                }
                        }
                }
        }

        /**
         * @return le nombre de colonnes
         */
        public int getNbColonnes() {
                return nbColonnes;
        }

        /**
         * @return le nombre de lignes
         */
        public int getNbLignes() {
                return nbLignes;
        }
        
        /** TODO comment method role
         * @param ligne
         * @param colonne
         * @return salle
         */
        public Salle getSalle(int ligne, int colonne) {
            return tableau[ligne][colonne];
        }

        /**
         * @return le tableau
         */
        public Salle[][] getTableau() {
                return tableau;
        }

}