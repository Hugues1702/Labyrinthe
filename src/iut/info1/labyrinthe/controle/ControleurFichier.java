/**
 * controleurFichier.java                                23 mai 2023
 * IUT Rodez, info1 2022-2023 paas de copyright copyleft
 */
package iut.info1.labyrinthe.controle;

import static iut.info1.labyrinthe.controle.Controleur.fichierLabyrintheActuel;
import static iut.info1.labyrinthe.controle.Controleur.labyrintheActuel;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import iut.info1.labyrinthe.Labyrinthe;


/** 
 * Permet de controler toutes les méthodes liées au fichier
 * @author djedline.boyer
 *
 */
public class ControleurFichier {
        
        private final static String BASE_NOM = "SauvegardeLabyrinthe\\Labyrinthe";
        private final static char SEPARATEUR = '_';
        
        /** 
         * Permet de rechercher le nombre de fichier dans un dossier
         * @return le nombre de fichier trouvée
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
        public static void ecritureFichier() {
                
            try {
                ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(fichierLabyrintheActuel));
                os.writeObject(labyrintheActuel);
                os.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } 

        }
        
        /** 
         * Recupère les données sauvegarder dans un fichier
         * @throws IOException
         */
        public static void RecupDonnee() {
            
            try {
                ObjectInputStream is = new ObjectInputStream(new FileInputStream(fichierLabyrintheActuel));
                labyrintheActuel = (Labyrinthe)is.readObject();
                is.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } 
            
        }
        
        /** 
         * Permet de rechercher le nombre de fichier dans un dossier
         */
        public static void afficheFichierSauvegarde() {
                File monRepertoire = new File("SauvegardeLabyrinthe");
                File[] listeFichier = monRepertoire.listFiles();
                
                if (listeFichier == null) {
                    System.out.println("Pas de fichier sauvegardée");
                } else {
                        for (int i = 0 ; i < listeFichier.length ; i++) {
                            System.out.println(listeFichier[i] + " : " + i); 
                        }
                }
        }
        
        /** 
         * Permet de rechercher le nombre de fichier dans un dossier
         * @param emplacementFichier 
         */
        public static void choixFichier(int emplacementFichier) {
                File monRepertoire = new File("SauvegardeLabyrinthe");
                File[] listeFichier = monRepertoire.listFiles();
                fichierLabyrintheActuel = listeFichier[emplacementFichier];
        }
                   
}