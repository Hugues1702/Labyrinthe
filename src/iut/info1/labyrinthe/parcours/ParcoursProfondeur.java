package iut.info1.labyrinthe.parcours;

import iut.info1.labyrinthe.Labyrinthe;
import iut.info1.labyrinthe.Salle;
import iut.info1.sdd.Pile;

import static iut.info1.labyrinthe.Labyrinthe.NORD;
import static iut.info1.labyrinthe.Labyrinthe.SUD;
import static iut.info1.labyrinthe.Labyrinthe.EST;
import static iut.info1.labyrinthe.Labyrinthe.OUEST;

public class ParcoursProfondeur {

	public ParcoursProfondeur() {
	}

	public void parcours(Labyrinthe laby) {
		Pile<Salle> pileSalles = new Pile();
		Salle entree = laby.getSalle(0, 0);
		Salle sortie = laby.getSalle(laby.getNbColonnes(), laby.getNbLignes());
		
		laby.resetMarques();
		entree.setMarque(1);
		pileSalles.empiler(entree);
		Salle sommetCourant = entree;
		int ligneCourante = 0;
		int colonneCourante = 0;
		do {
			Salle[] accessibles = getSallesAccessibles(laby, ligneCourante, colonneCourante);
			if (isTousParcourus(accessibles)) {
				if (!pileSalles.estVide()) {
					pileSalles.depiler();
				}
			} else {
				int nombreHasard = (int) Math.random() * 4; // TODO attention yen a pas forcément 4
			}
		} while (!sommetCourant.equals(sortie));
	}

	private boolean isTousParcourus(Salle[] salles) {
		boolean resultat = true;
		for (int i = 0 ; i < salles.length ; i++) {
			resultat &= salles[i].getMarque() == 1;
		}
		return resultat;
	}

	private Salle[] getSallesAccessibles(Labyrinthe laby, int ligne, int colonne) {
		
		/*
		Salle[] adjacentes = laby.getSallesAdjacentes(ligne, colonne);
		int i = 0;
		if (adjacentes[0]!= null && adjacentes[0].isPorteNord()) {
			accessibles[0] = adjacentes[i];
			i++;
		}
		if (adjacentes[1]!= null && adjacentes[1].isPorteOuest()) {
			accessibles[1] = adjacentes[i];
			i++;
		}
		if (adjacentes[2]!= null && laby.getSalle(ligne, colonne + 1).isPorteOuest()) {
			accessibles[2] = adjacentes[i];
			i++;
		}
		if (adjacentes[3]!= null && laby.getSalle(ligne + 1, colonne).isPorteNord()) {
			accessibles[3] = adjacentes[i];
			i++;
		}
		*/
		return accessibles;
	}

}
