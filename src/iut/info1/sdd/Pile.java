/**
 * Pile.java                                12 mai 2023
 * IUT Rodez, info1 2022-2023 paas de copyright copyleft
 */
package iut.info1.sdd;

import java.util.ArrayList;
import java.util.List;

/** 
 * Permet de générer une pile pour être réutiliser plus tard
 * @author djedline.boyer
 * @author tany.catalabailly
 *
 */
public class Pile<E> {

	/** corps de la liste qui contient des objects */
	private List<E> corpsPile;

	/** 
	 * Créer une liste vide
	 */
	public Pile() {
		super();
		this.corpsPile = new ArrayList();
	}

	/** 
	 * Permet de rajouter en haut de la pile
	 * @param element à ajouter
	 * @return la pile
	 */
	public Pile<E> empiler(E element) {
		corpsPile.add(element);
		return this;
	}

	/** 
	 * Permet d'enlever l'élément en haut de la pile
	 * @return la pile
	 */
	public Pile<E> depiler() {
		corpsPile.remove(this.corpsPile.size() - 1);
		return this;
	}

	/** 
	 * recherche le sommet
	 * @return l'élément au sommet de la pile
	 */
	public E sommet() {
		return this.corpsPile.get(this.corpsPile.size() - 1);
	}

	/** 
	 * vérifie si la pile est vide
	 * @return vrai la pile est vide
	 *         faux la pile n'est pas vide
	 */
	public boolean estVide() {
		return corpsPile.isEmpty();
	}
}