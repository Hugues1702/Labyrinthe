package iut.info1.labyrinthe;

/** TODO comment class responsibility (SRP)
 * @author nael.briot
 *
 */
public class Labyrinthe {

    private int nbColonnes;
    private int nbLignes;
    private int[][] tableau;

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
        
        int numeroCase = 1;

        for (int ligne = 0 ; ligne < tableau.length ; ligne++) {
            for (int colonne = 0 ; colonne < tableau[ligne].length ; colonne++) {
                tableau[ligne][colonne] = numeroCase;
                numeroCase++;
            }
        }
    }

    private static boolean isValide(int lignes, int colonnes) {
        return lignes >= 3 && colonnes >= 3;
    }

    @Override
    public int hashCode() {
        return this.tableau.hashCode() / 100 + this.nbColonnes * 10 + this.nbLignes;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return super.toString();
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
