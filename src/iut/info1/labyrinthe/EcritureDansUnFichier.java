/*
 * EcritureDansUnFichier.java                                    16 mai 2023
 * IUT Rodez, info1 2022-2023, pas de copyright ni "copyleft"
 */
package randomTests;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/** TODO comment class responsibility (SRP)
 * @author hugues.bertrand
 *
 */
public class EcritureDansUnFichier {
    /** TODO comment method role
     * @param args
     * @throws IOException 
     */
    public static void main(String[] args) throws IOException {
        
        // On cherche le fichier dans le package voulu et on en créé un objet
        // sur lequel on va pouvoir intéragir avec
        File file = new File("Z:\\iut\\info1\\testsae\\test.txt"); 
        
        // Si le fichier n'existe pas, alors on le créé.
        if (!file.exists()) {
            file.createNewFile();
         }
        
        // on met notre Objet fichier dans un Objet OutputStream pour écrire ce
        // qu'on veut sur le fichier précédemment créé
        FileOutputStream fos = new FileOutputStream(file);
        
        /* Pour le contenu qu'on veut mettre dans ce fichier, il faut le  convertir en bytes pour ensuite
         *  écrire dans le fichier grâce à la méthode .write qui ne prend en paramètre que des bytes

         * - First, contenu -> bytes
         * - on insere dans un tableau byte[] le contenu qu'on converti en byte grace à la méthode getBytes() 
         * - FileOutputStream.write(le tableau byte[])
         */
        
        // example
        String contenu =  "Texte ou contenu à insérer dans le fichier";
        byte[] contenuEnBytes = contenu.getBytes();
        fos.write(contenuEnBytes);
        
        fos.close();
        
        
        
    }
}
