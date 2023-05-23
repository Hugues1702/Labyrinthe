/*
 * EcrireLeLabyrinthe.java                                    23 mai 2023
 * IUT Rodez, info1 2022-2023, pas de copyright ni "copyleft"
 */
package iut.info1.labyrinthe;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

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
      
        String fichierTxt = "Z:\\BUT_1\\Semestre_2\\SAE ALGO\\Workspace sae"
                       + "\\Labyrinthe\\src\\iut\\info1\\labyrinthe\\idk.txt",
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
        contenu = essai.getInfoTxt();
        
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
    
    
}

// NIQUE TA PUTE DE MERE
