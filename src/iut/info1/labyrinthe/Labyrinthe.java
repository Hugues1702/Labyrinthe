/*
 * Labyrinthe.java                                          11/05/2023
 * Ni copyright, ni 
 */

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
	private Salle[][] tableau;

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
		this.tableau = new Salle[nbLignes][nbColonnes];

		int numeroCase = 0;

		for (int ligne = 0 ; ligne < tableau.length ; ligne++) {
			for (int colonne = 0 ; colonne < tableau[ligne].length ; colonne++) {
				tableau[ligne][colonne] = new Salle(numeroCase);
				numeroCase++;
			}
		}

		generationChaineAscendante();
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
				resultat += String.format("|%4d ", tableau[ligne][colonne].getIndex());
			}
			resultat += "|\n";
		}
		for (int colonne = 0 ; colonne < nbColonnes ; colonne++) {
			resultat += " ---- ";
		}
		return resultat;

	}

	private boolean generationChaineAscendante() {
		final int NB_OPERATIONS_MAX = getNbLignes() * getNbColonnes() - 1;
		Salle salleRandom;
		int marque = 1;

		for (int nbOperations = 0 ; nbOperations < NB_OPERATIONS_MAX ; 
				nbOperations++) {
			int ligneAuHasard = (int) (Math.random() * getNbLignes());
			int colonneAuHasard = (int) (Math.random() * getNbColonnes());
			salleRandom = tableau[ligneAuHasard][colonneAuHasard];
			boolean murNord = (int) Math.random() == 0;
			Salle voisin;
			if (murNord) {
				voisin = tableau[ligneAuHasard - 1][colonneAuHasard];
			} else {
				voisin = tableau[ligneAuHasard][colonneAuHasard - 1];
			}
			if (voisin.getMarque() != 0) {
				salleRandom.setMarque(voisin.getMarque());
			} else {
				salleRandom.setMarque(marque);
			}
		}

		return false;
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
	public Salle[][] getTableau() {
		return tableau;
	}

}
