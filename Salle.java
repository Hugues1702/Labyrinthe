package iut.info1.labyrinthe;

public class Salle {
	
	int index;
	boolean porteNord;
	boolean porteOuest;
	int marque;
	
	public int getMarque() {
		return marque;
	}

	public void setMarque(int marque) {
		this.marque = marque;
	}

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

	public boolean isPorteNord() {
		return porteNord;
	}

	public void setPorteNord(boolean porteNord) {
		this.porteNord = porteNord;
	}

	public boolean isPorteOuest() {
		return porteOuest;
	}

	public void setPorteOuest(boolean porteOuest) {
		this.porteOuest = porteOuest;
	}

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
