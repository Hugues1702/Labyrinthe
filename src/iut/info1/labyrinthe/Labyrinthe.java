package iut.info1.labyrinthe;

public class Labyrinthe {
	
	private int nbColonnes;
	private int nbLignes;
	private int[][] tableau;

	public Labyrinthe(int lignes, int colonnes) {
		if (!isValide(lignes, colonnes)) {
			throw new IllegalArgumentException();
		}
	}
	
	private static boolean isValide(int lignes, int colonnes) {
		return lignes >= 0;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}

	public int getNbColonnes() {
		return nbColonnes;
	}

	public int getNbLignes() {
		return nbLignes;
	}

	public int[][] getTableau() {
		return tableau;
	}

}
