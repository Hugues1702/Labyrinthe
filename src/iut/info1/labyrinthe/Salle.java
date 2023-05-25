package iut.info1.labyrinthe;

/** TODO comment class responsability(SRP)
 * @author djedline.boyer
 *
 */
public class Salle {
	
	int index;
	boolean porteNord;
	boolean porteOuest;
	int marque;
	
	/** TODO method field role
	 * @return marque
	 */
	public int getMarque() {
		return marque;
	}

	/** TODO method field role
	 * @param marque
	 */
	public void setMarque(int marque) {
		this.marque = marque;
	}

	/** TODO Comment initial state
	 * @param index
	 */
	public Salle(int index) {
		super();
		if (!isValide(index)) {
			throw new IllegalArgumentException();
		}
		this.index = index;
		this.marque = 0;
	}

	private boolean isValide(int index) {
		return index >= 0;
	}

	/** TODO method field role
	 * @return porteNord
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
	 * @return porteOuest
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
	 * @return index
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