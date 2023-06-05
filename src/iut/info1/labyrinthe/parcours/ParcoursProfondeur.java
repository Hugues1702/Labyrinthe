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
 */
public class ParcoursProfondeur {

	public ParcoursProfondeur() {
	}

	public static void parcours(Labyrinthe laby) {
		final int PARCOURU = 1;
		Pile<Salle> pileSalles = new Pile<>();
		Salle entree = laby.getSalle(0, 0);
		Salle sortie = laby.getSalle(laby.getNbColonnes() - 1, laby.getNbLignes() - 1);
		
		laby.resetMarques();
		entree.setMarque(PARCOURU);
		pileSalles.empiler(entree);
		Salle sommetCourant = entree;
		int ligneCourante = 0;
		int colonneCourante = 0;
		while (!sommetCourant.equals(sortie)){
			Salle[] accessibles = getSallesAccessibles(laby, ligneCourante, colonneCourante);
			if (isTousParcourus(accessibles)) {
				if (!pileSalles.estVide()) {
					pileSalles.depiler();
				}
			} else {
				int nombreHasard = (int) Math.random() * accessibles.length;
				accessibles[nombreHasard].setMarque(PARCOURU);
			}
			sommetCourant = pileSalles.sommet();
		};
		// affichage
		System.out.println("Etapes à reculons de l'arrivée :");
		do {
			System.out.print(pileSalles.sommet() + ", ");
			pileSalles.depiler();
		} while (!pileSalles.estVide());
	}

	private static boolean isTousParcourus(Salle[] salles) {
		boolean resultat = true;
		for (int i = 0 ; i < salles.length ; i++) {
			resultat &= salles[i].getMarque() == 1;
		}
		return resultat;
	}

	private static Salle[] getSallesAccessibles(Labyrinthe laby, int ligne, int colonne) {
		Salle[] accessibles = new Salle[4];
		int i = 0;
		Salle salleCourante = laby.getSalle(ligne, colonne);
		if (salleCourante.isPorteNord()) {
			accessibles[i] = laby.getSalleAdjacente(ligne, colonne, NORD);
			i++;
		}
		if (salleCourante.isPorteOuest()) {
			accessibles[i] = laby.getSalleAdjacente(ligne, colonne, OUEST);
			i++;
		}
		Salle salleSud = laby.getSalleAdjacente(ligne, colonne, SUD);
		if (salleSud.isPorteNord()) {
			accessibles[i] = salleSud;
			i++;
		}
		Salle salleEst = laby.getSalleAdjacente(ligne, colonne, EST);
		if (salleSud.isPorteOuest()) {
			accessibles[i] = salleEst;
			i++;
		}
		return Arrays.copyOf(accessibles, i);
	}

}
