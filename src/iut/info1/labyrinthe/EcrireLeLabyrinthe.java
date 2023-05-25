/*
 * EcrireLeLabyrinthe.java                                    23 mai 2023
 * IUT Rodez, info1 2022-2023, pas de copyright ni "copyleft"
 */
package iut.info1.labyrinthe;

import static iut.info1.labyrinthe.controle.Controleur.labyrintheActuel;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
/** TODO comment class responsibility (SRP)
 * @author hugues.bertrand
 *
 */
/** TODO comment class responsibility (SRP)
 * @author hugues.bertrand
 *
 */
public class EcrireLeLabyrinthe {
    
    /** TODO comment method role
     * @param args
     * @throws IOException 
     */
    public static void main(String[] args) throws IOException {
        
    }
        
        
    
    /** TODO comment method role
     * @throws IOException 
     * 
     */
    public static void ecrireDansLeFichier() throws IOException {
        
        String fichierTxt = "Z:\\BUT_1\\Semestre_2\\SAE ALGO\\Workspace sae"
                + "\\Labyrinthe\\src\\iut\\info1\\labyrinthe\\informations_labyrinthe.txt",
               contenu;
        
        Labyrinthe essai = labyrintheActuel;
        
        
        /*
         * On cherche le fichier dans le package voulu et on en créé un objet
         * sur lequel on va pouvoir intéragir avec
         */ 
        File file = new File(fichierTxt);
        
        // si ce fichier n'existe pas, alors on le créé.
        if (!file.exists()) {
            file.createNewFile();
        }
        
        
        /* OutputStream permet d'écrire dans le fichier séléctionné
         * (celui-ci doit être contenu dans un Objet de type File 
         */
        FileOutputStream fos = null; // stub
        try {
            fos = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            // Auto-generated catch block
            e.printStackTrace();
        }
        
        /*
         * On récupère les informations du labyrinthe grace à la méthode getInfoTxt()
         * que nous avons développée dans la classe Labyrinthe
         */
        contenu = essai.getInfoTxt();
        
        //convertion en bytes car la méthode write() ne fonctionne qu'avec des bytes.
        byte[] contenuEnBytes = contenu.getBytes();
        
        // écriture des informations dans le fichier
        fos.write(contenuEnBytes);
        
    }
    
    /** TODO comment method role
     * @throws IOException
     */
    public static void recupererInfosFichier() throws IOException {
        String fichierTxt = "Z:\\BUT_1\\Semestre_2\\SAE ALGO\\Workspace sae"
                + "\\Labyrinthe\\src\\iut\\info1\\labyrinthe\\idk.txt",
                valeursRecuperees;
        
        int nbLignes,
            nbColonnes;
        String[] valeursFinales;
      
        //On cherche le fichier dans le package voulu et on en créé un objet    
        FileInputStream fis = new FileInputStream(fichierTxt);   
        // Avec l'objet InputStream On créé un objet StreamReader pour être
        // capable de lire les caractères du fichier et non les bytes
        InputStreamReader ipsr = new InputStreamReader(fis);
        // Puis finalement grâce à un objet BufferedReader, on peut lire
        // selon différents facteurs. Ici on lit selon la ligne
        BufferedReader br = new BufferedReader(ipsr);
        
        valeursRecuperees = br.readLine();
        br.close();

        valeursFinales = valeursRecuperees.split(";");
        
        nbLignes = Integer.parseInt(valeursFinales[0]);
        nbColonnes = Integer.parseInt(valeursFinales[1]);
        
        System.out.printf("lignes : %d    colonnes : %d", nbLignes, nbColonnes);        
        

    }

    
    
}

