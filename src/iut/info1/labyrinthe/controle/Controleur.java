/**
 * Controleur.java                                23 mai 2023
 * IUT Rodez, info1 2022-2023 paas de copyright copyleft
 */
package iut.info1.labyrinthe.controle;

import iut.info1.labyrinthe.controle.ControleurFichier;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import iut.info1.labyrinthe.Labyrinthe;
import iut.info1.labyrinthe.Salle;


/** TODO comment class responsability(SRP)
 * @author djedline.boyer
 *
 */
public class Controleur {

    private static final char HAUT = 'Z';

    private static final char BAS = 'S';

    private static final char DROITE = 'D';

    private static final char GAUCHE = 'Q';

    /** nombre de ligne dans un labyrinthe*/
    public static int nbLignes = 3;

    /** nombre de colonnes */
    public static int nbColonnes = 3;


    /** permet de  déterminer la sortie du labyrinthe */
    public static int[] arrivee;

    /** permet de recueillir le choix de l'uitlisateur*/
    public static Scanner entreeUtilisateur = new Scanner(System.in);

    /** labyrinthe qui est utilisé pour le jeu*/
    public static Labyrinthe labyrintheActuel = new Labyrinthe(nbLignes,nbColonnes);

    /** Récupère un fichier */
    public static File fichierLabyrintheActuel;

    /** La position du joueur */
    public static int[] joueur;

    private static String deplacementUtil;

    static int nbDeplacement; 

    static boolean jeuFini = false;

    static String choixOptionUtil;
    final static String MENU = "Veuillez choisir l'une des options suivantes."
            + "\nG ou g : Genere un nouveau labyrinthe"
            + "\nT ou t : Telecharger un labyrinthe qui existe deja"
            + "\nJ ou j : Permet de jouer avec le dernier labyrinthe telecharge/genere"
            + "\nS ou s : Sauvegarde le dernier labyrinthe telecharge/genere dans un nouveau fichier"
            + "\nQ ou q : Permet de quitter le programme"
            + "\nEntrer l'option choisie :";
    final static char GENERER_NOUVEAU_LABYRINTHE = 'G';
    final static char TELECHARGER_LABYRINTHE = 'T';
    final static char JOUER = 'J';
    final static char SAUVEGARDER ='S';
    final static char QUITTER ='Q';

    /** 
     * Affiche un menu pour l'utilisateur
     * Renvoie un message si l'utilisateur entre une mauvaise option
     * @throws IOException 
     */
    public static void menu() throws IOException {

        System.out.println("Bienvenue dans le jeu du labyrinthe !");
        do {
            System.out.print("\n\n" + MENU);
            choixOptionUtil = entreeUtilisateur.nextLine().trim();
            if (choixOptionUtil.length() == 1) {
                choixOptionUtil = choixOptionUtil.toUpperCase();
                switch (choixOptionUtil.charAt(0)) {
                case GENERER_NOUVEAU_LABYRINTHE ->  nouveauLabyrinthe();
                case TELECHARGER_LABYRINTHE ->  ChoixFichierSauvegarde();
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
        } while (choixOptionUtil.charAt(0) != QUITTER && choixOptionUtil != null );
    }

    /** 
     * Permet de sauvegarder un labyrinthe dans un fichier
     * @throws IOException 
     */
    public static void sauvegarderLabyrinthe() throws IOException {
        if (fichierLabyrintheActuel == null) {
            ControleurFichier.nouveauFichier();
        }
        ControleurFichier.ecritureFichier();
        //recupDonnee();
    }

    /** 
     * Permet de créer un labyrinthe et l'affiche à l'utilisateur
     * Une fois le labyrinthe affiché l'utilisateur peut se déplacer librement
     * Le but étant qu'il trouve la sortie
     * Si l'utilisateur réussie à trouver la sortie alors le jeu se termine
     * Le joueur est renvoyé vers le menu 
     */
    public static void jouer() {
        joueur = new int[]{0,0};
        arrivee = new int[]{nbLignes-1, nbColonnes-1};
        nbDeplacement = 0;
        labyrintheActuel.getSalle(joueur[0], joueur[1]).setSymbole("X");
        arrivee[0] = nbLignes-1;
        arrivee[1] = nbColonnes-1;
        labyrintheActuel.getSalle(arrivee[0], arrivee[1]).setSymbole("f");
        System.out.println(labyrintheActuel.toString());
        do {
            System.out.println("Z,z -> pour se déplacer en haut\n"
                    + "S,s -> pour se déplacer en bas\n"
                    + "D,d -> pour se déplacer à droite\n"
                    + "Q,q -> pour se déplacer à gauche"
                    + "I,i -> pour interrompre la partie");

            deplacementUtil = entreeUtilisateur.nextLine().trim();
            if (deplacementUtil.length() == 1) {
                deplacementUtil = deplacementUtil.toUpperCase();
                switch (deplacementUtil.charAt(0)) {
                case HAUT, BAS, DROITE,GAUCHE  ->  deplacementVisible();
                case 'I' ->  System.out.println("Partie interrompue, sauvegarde de la partie");
                default -> System.out.println("Cette option n'existe pas, choisissez les options proposés");
                }
            } else {
                System.out.println("Cette option n'existe pas, entrer qu'une seule lettre");
            }
        } while (!jeuFini && deplacementUtil.charAt(0) != 'I');
        for (int ligne = 0 ; ligne < nbLignes ; ligne++) {
            for (int colonne = 0 ; colonne < nbColonnes ; colonne++) {
                labyrintheActuel.getSalle(ligne, colonne).setSymbole(" ");

            }
        }
    }

    /**
     * Permet le déplacement dans le labyrinthe
     * Envoie un message lorsque le déplacement est impossible
     * Déplace l'utilisateur selon choix et si déplacement possible
     */
    public static void deplacementVisible() {
        try {
            if (deplacementUtil.charAt(0) == HAUT) {
                ControleDeplacement.deplacementHaut();
            } else if (deplacementUtil.charAt(0) == BAS) {
                ControleDeplacement.deplacementBas();
            } else if (deplacementUtil.charAt(0) == DROITE) {
                ControleDeplacement.deplacementDroite();
            } else if (deplacementUtil.charAt(0) == GAUCHE) {
                ControleDeplacement.deplacementGauche();
            }
            //System.out.println("( " + joueur[0] + ";"+ joueur[1] + " )");
            nbDeplacement++;
            labyrintheActuel.getSalle(joueur[0], joueur[1]).setSymbole("X");
            System.out.println(labyrintheActuel.toString());            
            jeuFini = arrivee[0] == joueur[0] && arrivee[1] == joueur[1];
            if (jeuFini) {
                System.out.println("Bravo !! Vous etes sortis " 
                        + "\nVotre score est de : " + nbDeplacement);
            }
        } catch (IllegalArgumentException erreur) {
            System.out.println("Deplacement impossible");
            jeuFini = false;
        }

    }



    /** 
     * Créer un nouveau labyrinthe
     * Demande à l'utilisateur de choisir les dimensions du labyrinthe
     * Envoie un message d'erreur si l'entree saisie est mauvaise
     */
    public static void nouveauLabyrinthe() {
        boolean saisieOk;
        final String MESSAGE_ERREUR = "Erreur :Il faut entrer un nombre entier plus grand que 3 ";
        System.out.print("Pour une meilleur experience de jeu,"
                + "il est déconseillé de dépasser 17 pour le nombre de ligne"
                + " et 30 pour le nombre de colonne.");
        do {
            System.out.print("\nEntrez un nombre de lignes :");
            saisieOk = entreeUtilisateur.hasNextInt();
            if (saisieOk) {
                nbLignes = entreeUtilisateur.nextInt();
                saisieOk = nbLignes >= 3;
                entreeUtilisateur.nextLine();
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
        fichierLabyrintheActuel = null;
    }


    /**
     * 
     */
    public static void ChoixFichierSauvegarde() {
        boolean saisieOk;
        int nbFichier;
        ControleurFichier.afficheFichierSauvegarde();
        do {
            System.out.print("\nEntrez un nombre correspondant au dernier "
                    + "nombre faisant parti du nom du fichier correspondant:"
                    + "\nExemple : entrer 0 pour télécharger le fichier :"
                    + "\"SauvegardeLabyrinthe\\Labyrinthe_L3_C3_0\""
                    + "\n Veuiller effectuer votre saisie : ");
            saisieOk = entreeUtilisateur.hasNextInt();
            if (saisieOk) {
                nbFichier = entreeUtilisateur.nextInt();
                saisieOk = nbFichier < ControleurFichier.nombreFichierSauvegarde()
                         && nbFichier >= 0;
                if (saisieOk) {
                    System.out.println("Début téléchargement");
                    ControleurFichier.choixFichier(nbFichier);
                    ControleurFichier.RecupDonnee();
                    nbLignes = labyrintheActuel.getNbLignes();
                    nbColonnes = labyrintheActuel.getNbColonnes();
                    System.out.println("Fin téléchargement");
                }
            }
            if (!saisieOk) {
                System.err.println("Ce fichier n'existe pas");
            }
            entreeUtilisateur.nextLine();
        } while(!saisieOk);
    }

    /** 
     * Démarre le jeu en ouvrant le menu
     * @param args
     * @throws IOException 
     */
    public static void main(String[] args) throws IOException {
        //nouveauLabyrinthe();
        menu();

    }

}