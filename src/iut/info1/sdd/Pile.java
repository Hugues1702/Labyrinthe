/**
 * Pile.java                                12 mai 2023
 * IUT Rodez, info1 2022-2023 paas de copyright copyleft
 */
package iut.info1.sdd;

import java.util.Arrays;

/** 
 * Permet de générer une pile pour être réutiliser plus tard
 * @author BAUDROIT Leïla
 * @author BERTRAND Hugues
 * @author BOYER Djedline
 * @author BRIOT Nael
 */
public class Pile {

	/** Taille à l'initialisation de la pile*/
	private static final int TAILLE_INITIALE = 10;
	
	/** Indice du sommet */
	int taille;
	
	/** corps de la liste qui contient des objects */
	private Object[] corpsPile;

	/** 
	 * Créer une liste vide
	 */
	public Pile() {
		super();
		this.corpsPile = new Object[TAILLE_INITIALE];
		this.taille = 0;
	}

	/** 
	 * Permet de rajouter en haut de la pile
	 * @param element à ajouter
	 * @return la pile
	 */
	public Pile empiler(Object element) {
		if (taille >= corpsPile.length) {
			corpsPile = Arrays.copyOf(corpsPile, (int) (corpsPile.length * 1.5));
		}
		corpsPile[taille] = element;
		taille++;
		return this;
	}

	/** 
	 * Permet d'enlever l'élément en haut de la pile
	 * @return la pile
	 */
	public Pile depiler() {
		if (estVide()) {
			throw new PileVideException("Une pile vide n'a pas de sommet.");
		}
		taille--;
		return this;
	}

	/** 
	 * recherche le sommet
	 * @return l'élément au sommet de la pile
	 */
	public Object sommet() {
		if (estVide()) {
			throw new PileVideException("Une pile vide n'a pas de sommet.");
		}
		return corpsPile[taille - 1];
	}

	/** 
	 * vérifie si la pile est vide
	 * @return vrai la pile est vide
	 *         faux la pile n'est pas vide
	 */
	public boolean estVide() {
		return taille == 0;
	}

	/**
	 * @see {@code java.lang.Object#toString()}
	 */
	@Override
	public String toString() {
		String resultat = new String();
		for (int parcours = 0 ; parcours < taille ; parcours++) {
			resultat += corpsPile[parcours];
			if (parcours != (taille - 1)) {
				resultat += ", ";
			}
		}
		return resultat;
	}
	
	/**
	 * @return la taille de cette pile
	 */
	public int getTaille() {
		return taille;
	}
}