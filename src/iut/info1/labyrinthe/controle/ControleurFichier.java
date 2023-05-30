/**
 * controleurFichier.java                                23 mai 2023
 * IUT Rodez, info1 2022-2023 paas de copyright copyleft
 */
package iut.info1.labyrinthe.controle;

import static iut.info1.labyrinthe.controle.Controleur.fichierLabyrintheActuel;
import static iut.info1.labyrinthe.controle.Controleur.labyrintheActuel;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;


/** 
 * 
 * @author djedline.boyer
 *
 */
public class ControleurFichier {
	
	private final static String BASE_NOM = "src\\SauvegardeLabyrinthe\\Labyrinthe";
	private final static char SEPARATEUR = '_';
	//private static File file;
	
	/** TODO method field role
	 * @return x
	 */
	public static int nombreFichierSauvegarde() {
		File monRepertoire = new File("SauvegardeLabyrinthe");
		File[] listeFichier = monRepertoire.listFiles();
		int nombreFichier = 0;
		if (listeFichier == null) {
			nombreFichier = 0;
		} else {
			for (int i = 0 ; i < listeFichier.length ; i++) {
				if (listeFichier[i].isFile()) {
					nombreFichier++;
				}
			}
		}
		return nombreFichier; 
	}
	/** 
	 * Crée un nouveau fichier avec les données de labyrinthe à l'intérieur
	 * @throws IOException 
	 */
	public static void nouveauFichier() throws IOException {
	    //labyrintheActuel.getNbLignes()
		String nomFichier = BASE_NOM + SEPARATEUR 
				  + "L" + labyrintheActuel.getNbLignes() + SEPARATEUR 
				  + "C" + labyrintheActuel.getNbColonnes()+ SEPARATEUR 
				  + nombreFichierSauvegarde();
		fichierLabyrintheActuel = new File(nomFichier);
		// si ce fichier n'existe pas, alors on le créé.
		if (!fichierLabyrintheActuel.exists()) {
			fichierLabyrintheActuel.createNewFile();
			System.out.println(nomFichier + " est cree.");
		}
	}
	
	/** 
	 * Permet de récupérer un fichier et d'écrire
	 * @throws IOException 
	 */
	public static void ecritureFichier() throws IOException {
		String fichierTxt = "Z:\\BUT_1\\Semestre_2\\SAE ALGO\\Workspace sae"
				+ "\\Labyrinthe\\src\\iut\\info1\\labyrinthe\\informations_labyrinthe.txt",
				contenu;

		/* OutputStream permet d'écrire dans le fichier séléctionné
		 * (celui-ci doit être contenu dans un Objet de type File 
		 */
		FileOutputStream fos = null; // stub
		try {
			fos = new FileOutputStream(fichierLabyrintheActuel);
		} catch (FileNotFoundException e) {
			// Auto-generated catch block
			e.printStackTrace();
		}

		/*FileReader fr = new FileReader(fichierLabyrintheActuel);
		int nbBoucle = ;*/
		//Récupération des informations du labyrinthe
		contenu = labyrintheActuel.getNbLignes() + ";" + labyrintheActuel.getNbColonnes();

		//convertion en bytes car la méthode write() ne fonctionne qu'avec des bytes.
		byte[] contenuEnBytes = contenu.getBytes();

		// écriture des informations dans le fichier
		fos.write(contenuEnBytes);
	}
}
	
	