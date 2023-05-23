                                                                           package iut.info1.labyrinthe;

/** 
 * Représente un labyrinthe rectangulaire, constitué de pièces originellement
 * ferméepar des murs.
 * @author nael.briot
 *
 */
public class Labyrinthe {

    private int nbColonnes;
    private int nbLignes;
    private int[][] tableau;
    private boolean[][] mursVerticaux;
    private boolean[][] mursHorizontaux;
    
    /**
     * Crée un labyrinthe en fonction d'un nombre de salles en longueur
     * et en hauteur.
     * @param lignes
     * @param colonnes
     */
    public Labyrinthe(int lignes, int colonnes) {
        if (!isValide(lignes, colonnes)) {
            throw new IllegalArgumentException();
        }
        this.nbColonnes = colonnes;
        this.nbLignes = lignes;
        this.tableau = new int[nbLignes][nbColonnes];
        this.mursHorizontaux = new boolean[nbLignes + 1][nbColonnes];
        this.mursVerticaux = new boolean[nbLignes][nbColonnes + 1];
                
        int numeroCase = 0;

        for (int ligne = 0 ; ligne < tableau.length ; ligne++) {
            for (int colonne = 0 ; colonne < tableau[ligne].length ; colonne++) {
                tableau[ligne][colonne] = numeroCase;
                numeroCase++;
            }
        }
        
        for (int ligne = 0 ; ligne < mursHorizontaux.length ; ligne++) {
            for (int colonne = 0 ; colonne < mursHorizontaux[ligne].length ; colonne++) {
                System.out.print(mursHorizontaux[ligne][colonne]);
            } 
            System.out.println();
        }
    }
    
    /**
     * Vérifie que le labyrinthe soit valide
     * @param lignes
     * @param colonnes
     * @return True ou False selon si le labyrinthe peut être crée ou non
     */
    private static boolean isValide(int lignes, int colonnes) {
        return lignes >= 3 && colonnes >= 3;
    }

    @Override
    public int hashCode() {
        return this.tableau.hashCode() / 100 + this.nbColonnes * 10 + this.nbLignes;
    }

    @Override
    public String toString() {
        String resultat = "";
        for (int ligne = 0 ; ligne < tableau.length ; ligne++) {
            for (int colonne = 0 ; colonne < tableau[ligne].length ; colonne++) {
                resultat += " ---- ";
            }
            resultat += "\n";
            for (int colonne = 0 ; colonne < tableau[ligne].length ; colonne++) {
                resultat += String.format("|%4d ",tableau[ligne][colonne]);
            }
            resultat += "|\n";
        }
        for (int colonne = 0 ; colonne < nbColonnes ; colonne++) {
            resultat += " ---- ";
        }
        return resultat;
   
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

    /**
     * @return le tableau
     */
    public int[][] getTableau() {
        return tableau;
    }

}
