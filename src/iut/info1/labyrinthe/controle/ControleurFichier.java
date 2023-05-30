/**
 * controleurFichier.java                                23 mai 2023
 * IUT Rodez, info1 2022-2023 paas de copyright copyleft
 */
package iut.info1.labyrinthe.controle;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import iut.info1.labyrinthe.Labyrinthe;
import static iut.info1.labyrinthe.controle.Controleur.labyrintheActuel;


/** 
 * 
 * @author djedline.boyer
 *
 */
public class ControleurFichier {
	
	private final static String BASE_NOM = "src\\SauvegardeLabyrinthe\\Labyrinthe";
	private final static char SEPARATEUR = '_';
	private static File file;
	
	/** TODO method field role
	 * @return x
	 */
	public static int nombreFichierSauvegarde() {
		File monRepertoire = new File("SauvegardeLabyrinthe");
		File[] listeFichier = monRepertoire.listFiles();
		int nombreFichier = 0;
		for (int i = 0 ; i < listeFichier.length ; i++) {
		  if (listeFichier[i].isFile()) {
			  nombreFichier++;
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
		System.out.println(nomFichier);
		/*file = new File(nomFichier);
		// si ce fichier n'existe pas, alors on le créé.
		if (!file.exists()) {
			file.createNewFile();
			System.out.println("fichier cree");
		}*/
	}
	
	/** TODO method field role
	 * @throws IOException 
	 * 
	 */
	public void stockage() throws IOException {
		String fichierTxt = "src\\iut\\info1\\labyrinthe\\idk.txt",
				contenu,
				valeursRecuperees;
		int nbLignes,
		nbColonnes;
		String[] valeursFinales;

		Labyrinthe essai = new Labyrinthe(5,10);

		/*
		 * On récupère les informations du labyrinthe grace à la méthode getInfoTxt()
		 * que nous avons développée dans la classe Labyrinthe
		 */
		contenu = essai.getNbLignes() + ";" + essai.getNbColonnes() ;

		/*
		 * On cherche le fichier dans le package voulu et on en créé un objet
		 * sur lequel on va pouvoir intéragir avec
		 */ 
		File file = new File(fichierTxt);

		// si ce fichier n'existe pas, alors on le créé.
		if (!file.exists()) {
			file.createNewFile();
		}

		FileOutputStream fos = null; // stub
		try {
			fos = new FileOutputStream(file);
		} catch (FileNotFoundException e) {
			// Auto-generated catch block
			e.printStackTrace();
		}

		//convertion en bytes car la méthode write() ne fonctionne qu'avec des bytes.
		byte[] contenuEnBytes = contenu.getBytes();

		// écriture des informations dans le fichier
		fos.write(contenuEnBytes);

		/* Maintenant on doit récupérer les données à partir du fichier pour
		 * les renseigner dans les int.
		 * Pour ceci on a besoin d'un FileInputStream.
		 */

		//

		FileInputStream fis = new FileInputStream(fichierTxt);   

		valeursRecuperees = ""+fis.read(); 


		System.out.println(valeursRecuperees);

		/*
	        valeursFinales = valeursRecuperees.split(";");

	        nbLignes = Integer.parseInt(valeursFinales[0]);
	        nbColonnes = Integer.parseInt(valeursFinales[1]);

	        System.out.println(nbLignes +"" + nbColonnes); */


	}

	/** TODO comment method role
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		nouveauFichier();
	}
}
	// NIQUE TA PUTE DE MERE