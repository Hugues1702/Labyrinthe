/*
 * CreationAscendanteDeChaine.java
 * IUT de Rodez, no copyright nor copyleft.
 */
package iut.info1.labyrinthe;

/** 
 * Création d'un labyrinthe à l'aide d'une méthode par 
 * création ascendante de chaînes.
 * @author hugues.bertrand
 */
public class CreationAscendanteDeChaines {
	
	private static int nbColonnes;
	private static int nbLignes;
    
    /** 
     * Algorithme de génération d'un labyrinthe à l'aide d'une méthode
     * par création ascendente 
     * @param args
     */
    public static void main(String[]args) {
    	nbLignes = 4;
    	nbColonnes = 4;
    	
    	int numeroCase = 1;
    	
    	// Initialisation du tableau
        int[][] tableau = new int[nbLignes][nbColonnes];
        for (int ligne = 0 ; ligne < tableau.length ; ligne++) {
        	for (int colonne = 0 ; colonne < tableau[ligne].length ; colonne++) {
        		tableau[ligne][colonne] = numeroCase;
            	numeroCase++;
            }
        }
        
        // Affichage
        for (int ligne = 0 ; ligne < tableau.length ; ligne++) {
        	for (int colonne = 0 ; colonne < tableau[ligne].length ; colonne++) {
        		System.out.print(" ---- ");
            }
        	System.out.println();
        	for (int colonne = 0 ; colonne < tableau[ligne].length ; colonne++) {
        		System.out.printf("|%4d ",tableau[ligne][colonne]);
            }
        	System.out.print("|");
        	System.out.println();
        }
        for (int colonne = 0 ; colonne < nbLignes ; colonne++) {
    		System.out.print(" ---- ");
        }
        
    }
    
}