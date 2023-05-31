/**
 * Controleur.java                                23 mai 2023
 * IUT Rodez, info1 2022-2023 paas de copyright copyleft
 */
package iut.info1.labyrinthe.controle;

import static iut.info1.labyrinthe.controle.ControleurFichier.ecritureFichier;
import static iut.info1.labyrinthe.controle.ControleurFichier.nouveauFichier;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import iut.info1.labyrinthe.Labyrinthe;


/** TODO comment class responsability(SRP)
 * @author djedline.boyer
 *
 */
public class Controleur {
        
    /** nombre de ligne dans un labyrinthe*/
    public static int nbLignes = 3;
    
        /** nombre de colonnes */
        public static int nbColonnes = 3;
        
        /** permet de recueillir le choix de l'uitlisateur*/
        public static Scanner entreeUtilisateur = new Scanner(System.in);

        /** labyrinthe qui est utilisé pour le jeu*/
        public static Labyrinthe labyrintheActuel = new Labyrinthe(nbLignes,nbColonnes);
        
        /** Récupère un fichier */
        public static File fichierLabyrintheActuel;
        /** 
         * Affiche un menu pour l'utilisateur
         * @throws IOException 
         */
        public static void menu() throws IOException {
                String choixOptionUtil;
                final String MENU = " Veuillez choisir l'une des options suivantes."
                                + "\nG ou g : Genere un nouveau labyrinthe"
                                + "\nT ou t : Telecharger un labyrinthe qui existe deja"
                                + "\nJ ou j : Permet de jouer avec le dernier labyrinthe telecharge/genere"
                                + "\nS ou s : Sauvegarde le dernier labyrinthe telecharge/genere"
                                + "\nQ ou q : Permet de quitter le programme"
                                + "\nEntrer l'option choisie :";
                final char GENERER_NOUVEAU_LABYRINTHE = 'G';
                final char TELECHARGER_LABYRINTHE = 'T';
                final char JOUER = 'J';
                final char SAUVEGARDER ='S';
                final char QUITTER ='Q';

                do {
                        System.out.print(MENU);
                        choixOptionUtil = entreeUtilisateur.nextLine();
                        if (choixOptionUtil.length() == 1) {
                                choixOptionUtil = choixOptionUtil.toUpperCase();
                                switch (choixOptionUtil.charAt(0)) {
                                case GENERER_NOUVEAU_LABYRINTHE ->  nouveauLabyrinthe();
                                case TELECHARGER_LABYRINTHE ->  System.out.println("Vous voulez télécharger un labyrinthe");
                                case JOUER -> { System.out.println("Création du labyrinthe :");
                                                jouer();
                                }
                                case SAUVEGARDER -> { System.out.println("Sauvegarde en cours");
                                                      sauvegarderLabyrinthe();
                                }
                                case QUITTER -> System.out.println("A bientot");
                                default -> System.out.println("cette option n'existe pas");
                                }
                        } else {
                                System.out.println("Cette option n'existe pas, entrer qu'une seule lettre");
                        }
                } while (!(choixOptionUtil.charAt(0) == QUITTER));
        }
        
        /** 
         * Permet de sauvegarder un labyrinthe dans un fichier
         * @throws IOException 
         */
        public static void sauvegarderLabyrinthe() throws IOException {
                if (fichierLabyrintheActuel == null) {
                        nouveauFichier();
                }
                ecritureFichier();
        }
        
        /** 
         * Permet de créer un labyrinthe
         * et l'affiche à l'utilisateur
         */
        public static void jouer() {
                System.out.println(labyrintheActuel.toString());
        }
        
        /** 
         * Créer un nouveau labyrinthe
         */
        public static void nouveauLabyrinthe() {
                boolean saisieOk;
                final String MESSAGE_ERREUR = "Erreur :Il faut entrer un nombre entier plus grand que 3 ";
                do {
                        System.out.print("Entrez un nombre de lignes :");
                        saisieOk = entreeUtilisateur.hasNextInt();
                        if (saisieOk) {
                                nbLignes = entreeUtilisateur.nextInt();
                                saisieOk = nbLignes >= 3;
                                if (saisieOk) {
                                        System.out.print("Entrez un nombre de colonnes :");
                                        saisieOk = entreeUtilisateur.hasNextInt();
                                }
                                if (saisieOk) {
                                        nbColonnes = entreeUtilisateur.nextInt();
                                        saisieOk = nbColonnes >= 3;
                                }
                        }
                        if (!saisieOk) {
                                System.err.println(MESSAGE_ERREUR);
                        }
                        entreeUtilisateur.nextLine();

                } while(!saisieOk);
                labyrintheActuel = new Labyrinthe(nbLignes,nbColonnes);
        }
        
        /** 
         * Démarre le menu
         * @param args
         * @throws IOException 
         */
        public static void main(String[] args) throws IOException {
                //nouveauLabyrinthe();
                menu();

        }

}