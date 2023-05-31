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

	public static final int NORD = 0;
	public static final int OUEST = 1;
	public static final int SUD = 2;
	public static final int EST = 3;

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
		return this.tableau.hashCode() / 100 + this.nbColonnes * 10 + this.nbLignes;
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
				resultat += String.format(murVertical + "%4d ", 
						tableau[ligne][colonne].getIndex());
			}
			resultat += "|\n";
		}
		for (int colonne = 0 ; colonne < nbColonnes ; colonne++) {
			resultat += "+-----";
		}
		resultat += "+\n";
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
	 * 		ou le mur de l'ouest autrement
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

	/**
	 * Remet à 0 les marques de toutes les salles du labyrinthe.
	 */
	public void resetMarques() {
		for (int ligne = 0 ; ligne < tableau.length ; ligne++) {
			for (int colonne = 0 ; colonne < tableau[ligne].length ; colonne++) {
				tableau[ligne][colonne].setMarque(0);
			}
		}
	}

	/**
	 * Renvoie une salle selon sa ligne et sa colonne dans le labyrinthe.
	 * La méthode isCoordonneesSalleValide peut être utilisée pour vérifier la validité des
	 * coordonnées.
	 * @param ligne un nombre compris entre 0 et getNbLignes()-1
	 * @param colonne un nombre compris entre 0 et getNbColonnes()-1
	 * @return la Salle située à ces coordonnées.
	 * @throws IllegalArgumentException si les coordonnées sont invalides
	 */
	public Salle getSalle(int ligne, int colonne) {
		if (!isCoordonneesSalleValide(ligne, colonne)) {
			throw new IllegalArgumentException();
		}
		return tableau[ligne][colonne];
	}

	/**
	 * Prédicat de vérification de coordonnées.
	 * @return true si les coordonnées sont valides pour ce labyrinthe,
	 * false sinon
	 */
	public boolean isCoordonneesSalleValide(int ligne, int colonne) {
		return       ligne >= 0 &&   ligne < getNbLignes() 
				&& colonne >= 0 && colonne < getNbColonnes();
	}

	/**
	 * Permet d'obtenir la salle adjacente à une autre avec ses coordonnées et
	 * la direction.
	 * @param ligne la ligne de la salle valide dont on veut la salle adjacente
	 * @param colonne la colonne de la salle valide dont ont veut la salle adjacente
	 * @param direction la direction parmi les constantes NORD, OUEST, SUD, EST
	 * @return la salle située à côté
	 * @throws IllegalArgumentException si les coordonnées ou la direction sont invalides,
	 * ou si les coordonnées de la salle obtenue sont invalides
	 */
	public Salle getSalleAdjacente(int ligne, int colonne, int direction) {
		if (!isCoordonneesSalleValide(ligne, colonne) 
				|| !isDirectionValide(direction)) {
			throw new IllegalArgumentException();
		}
		int ligneAdjacent = ligne;
		int colonneAdjacent = colonne;
		if (direction == NORD) {
			ligneAdjacent--;
		} else if (direction == SUD) {
			ligneAdjacent++;
		} else if (direction == OUEST) {
			colonneAdjacent--;
		} else {
			colonneAdjacent++;
		}
		System.out.printf("[%d;%d]\t", ligneAdjacent, colonneAdjacent);
		if (!isCoordonneesSalleValide(ligneAdjacent, colonneAdjacent)) {
			throw new IllegalArgumentException();
		}
		return tableau[ligneAdjacent][colonneAdjacent];
	}

	/**
	 * Prédicat vérifiant qu'un nombre correspond bien à une direction.
	 * @param direction l'entier à vérifier
	 * @return true si la direction vaut une des constantes NORD, SUD, OUEST, EST
	 */
	private boolean isDirectionValide(int direction) {
		return direction == NORD || direction == SUD || direction == OUEST || direction == EST;
	}

	/**
	 * Requête renvoyant le booléen représentant la porte dans la direction indiquée
	 * par rapport à la salle.
	 * @param ligne la ligne de la salle concernée
	 * @param colonne la colonne de la salle concernée
	 * @param direction la direction parmi les constantes NORD, OUEST, SUD, EST
	 * @return true si le mur est une porte, false sinon
	 * @throws IllegalArgumentException si les coordonnées ou la direction sont invalides
	 */
	public boolean isPorteAdjacente(int ligne, int colonne, int direction) {
		if (!isCoordonneesSalleValide(ligne, colonne) || !isDirectionValide(direction)) {
			throw new IllegalArgumentException();
		}
		boolean resultat = false;
		switch(direction) {
		case NORD -> {
			resultat = tableau[ligne][colonne].isPorteNord();
		}
		case OUEST -> {
			resultat = tableau[ligne][colonne].isPorteOuest();
		}
		case EST -> {
			if (ligne != getNbLignes() - 1) { //TODO ajouter un test pour ce cas
				resultat = getSalleAdjacente(ligne, colonne, EST).isPorteOuest();
			}
		}
		case SUD -> {
			if (colonne != getNbColonnes() - 1) {
				resultat = getSalleAdjacente(ligne, colonne, SUD).isPorteNord();
			}
		}
		}
		return resultat;
	}
}