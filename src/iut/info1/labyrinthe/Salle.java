package iut.info1.labyrinthe;

/** 
 * Permet de modéliser une pièce d'un labyrinthe
 * @author djedline.boyer
 *
 */
public class Salle {
	
	int index;
	boolean porteNord;
	boolean porteOuest;
	int marque;
	
	/** 
	 * Récupère la marque de la salle
	 * @return la marque récupéré
	 */
	public int getMarque() {
		return marque;
	}

	/** 
	 * Modifie la marque de la salle
	 * @param marque la marque à modifier
	 */
	public void setMarque(int marque) {
		this.marque = marque;
	}

	/** 
	 * Initialise une nouvelle salle
	 * @param index
	 */
	public Salle(int index) {
		super();
		if (!isValide(index)) {
			throw new IllegalArgumentException();
		}
		this.index = index;
	}

	private boolean isValide(int index) {
		return index >= 0;
	}

	/** 
	 * 
	 * @return true si la porteNord existe
	 *         false sinon
	 */
	public boolean isPorteNord() {
		return porteNord;
	}

	/** TODO method field role
	 * @param porteNord
	 */
	public void setPorteNord(boolean porteNord) {
		this.porteNord = porteNord;
	}

	/** TODO method field role
	 * @return true si la porte Ouest existe
	 *         false sinon
	 */
	public boolean isPorteOuest() {
		return porteOuest;
	}

	/** TODO method field role
	 * @param porteOuest
	 */
	public void setPorteOuest(boolean porteOuest) {
		this.porteOuest = porteOuest;
	}

	/** TODO method field role
	 * @return l'index
	 */
	public int getIndex() {
		return index;
	}

	@Override
	public int hashCode() {
		return index;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Salle other = (Salle) obj;
		return index == other.index && porteNord == other.porteNord && porteOuest == other.porteOuest;
	}

}