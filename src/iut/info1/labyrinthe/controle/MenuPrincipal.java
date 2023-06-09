/**
 * Controleur.java                                23 mai 2023
 * IUT Rodez, info1 2022-2023 paas de copyright copyleft
 */
package iut.info1.labyrinthe.controle;

import iut.info1.sdd.Pile;
import iut.info1.labyrinthe.parcours.ParcoursProfondeur;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import iut.info1.labyrinthe.Labyrinthe;
import iut.info1.labyrinthe.Salle;


/** 
 * Affiche le menu principal à l'utilisateur
 * Une fois fait l'utilisateur peut librement activer les options du menu
 * les options lui permettent de jouer au jeu du labyrinthe
 * sauvegarder le labyrinthe actuel
 * générer un nouveau labyrinthe
 * télécharger un labyrinthe sauvegarder dans un fichier pour pouvoir jouer avec
 * @author BAUDROIT Leïla
 * @author BERTRAND Hugues
 * @author BOYER Djedline
 * @author BRIOT Nael
 */
public class MenuPrincipal {

    private static final char HAUT = 'Z';
    private static final char BAS = 'S';
    private static final char DROITE = 'D';
    private static final char GAUCHE = 'Q';
    private final static String MENU = "Veuillez choisir l'une des options suivantes."
            + "\nG ou g : Genere un nouveau labyrinthe"
            + "\nT ou t : Telecharger un labyrinthe qui existe deja"
            + "\nJ ou j : Permet de jouer avec le dernier labyrinthe telecharge/genere"
            + "\nS ou s : Sauvegarde le dernier labyrinthe telecharge/genere dans un nouveau fichier"
            + "\nQ ou q : Permet de quitter le programme"
            + "\nEntrer l'option choisie :";
    private final static char GENERER_NOUVEAU_LABYRINTHE = 'G';
    private final static char TELECHARGER_LABYRINTHE = 'T';
    private final static char JOUER = 'J';
    private final static char SAUVEGARDER ='S';
    private final static char QUITTER ='Q';

    private static String deplacementUtil;
    private static int nbDeplacement; 
    private static boolean jeuFini = false;
    private static String choixOptionUtil;

    /** La position du joueur */
    public static int[] joueur = new int[]{0,0};;

    /** Contient le chemin optimal pour résourdre un labyrinthe sélectionné */
    public static Pile cheminOptimal;

    /** nombre de ligne dans un labyrinthe*/
    public static int nbLignes;

    /** nombre de colonnes */
    public static int nbColonnes;

    private static Salle salle;

    /** permet de  déterminer la sortie du labyrinthe */
    public static int[] arrivee;

    /** permet de recueillir le choix de l'uitlisateur*/
    public static Scanner entreeUtilisateur = new Scanner(System.in);

    /** labyrinthe qui est utilisé pour le jeu*/
    public static Labyrinthe labyrintheActuel;

    /** Récupère un fichier */
    public static File fichierLabyrintheActuel;


    /** 
     * Affiche un menu pour l'utilisateur
     * Renvoie un message si l'utilisateur entre une mauvaise option
     * @throws IOException lorsqu'un fichier n'est pas trouvé
     */
    public static void menu() throws IOException {

        nbLignes = 3;
        nbColonnes = 3;
        nbDeplacement = 0;
        labyrintheActuel = new Labyrinthe(nbLignes,nbColonnes);
        System.out.println("Bienvenue dans le jeu du labyrinthe !");
        do {
            System.out.print("\n\n" + MENU);
            choixOptionUtil = entreeUtilisateur.nextLine().trim();
            if (choixOptionUtil.length() == 1) {
                choixOptionUtil = choixOptionUtil.toUpperCase();
                switch (choixOptionUtil.charAt(0)) {
                case GENERER_NOUVEAU_LABYRINTHE ->  nouveauLabyrinthe();
                case TELECHARGER_LABYRINTHE ->  ChoixFichierSauvegarde();
                case JOUER -> { System.out.println("Creation du labyrinthe :");
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
                choixOptionUtil = "R";
            }
        } while (choixOptionUtil.charAt(0) != QUITTER && choixOptionUtil != null );
    }

    /** 
     * Permet de sauvegarder un labyrinthe dans un fichier
     * @throws IOException lorsqu'un fichier n'est pas trouvé
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
     * @throws IOException lorsqu'un fichier n'est pas trouvé 
     */
    public static void jouer() throws IOException {
        boolean finPartie;
        arrivee = new int[]{nbLignes-1, nbColonnes-1};
        labyrintheActuel.getSalle(joueur[0], joueur[1]).setSymbole("X");
        arrivee[0] = nbLignes-1;
        arrivee[1] = nbColonnes-1;
        labyrintheActuel.getSalle(arrivee[0], arrivee[1]).setSymbole("f");
        System.out.println(labyrintheActuel.toString());
        do {
            System.out.println("Z,z -> pour se deplacer en haut\n"
                    + "S,s -> pour se deplacer en bas\n"
                    + "D,d -> pour se deplacer a droite\n"
                    + "Q,q -> pour se deplacer a gauche\n"
                    + "I,i -> pour interrompre la partie");

            deplacementUtil = entreeUtilisateur.nextLine().trim();
            finPartie = true;
            if (deplacementUtil.length() == 1) {
                deplacementUtil = deplacementUtil.toUpperCase();
                switch (deplacementUtil.charAt(0)) {
                case HAUT, BAS, DROITE,GAUCHE  ->  deplacementVisible();
                case 'I' ->{ 
                    System.out.println("Partie interrompue, sauvegarde de la partie");
                    sauvegarderLabyrinthe();
                    finPartie = false;
                }
                default -> System.out.println("Cette option n'existe pas, choisissez les options proposés");
                }
            } else {
                System.out.println("Cette option n'existe pas, entrer qu'une seule lettre");
                deplacementUtil = "R"; 
            }
        } while (!jeuFini && deplacementUtil.charAt(0) != 'I');
        if (finPartie) {
            cheminOptimal = ParcoursProfondeur.parcours(labyrintheActuel);
            System.out.println("Score optimal : " 
                 + (cheminOptimal.getTaille()-1) + "\nChemin optimal :");
            for (int ligne = 0 ; ligne < nbLignes ; ligne++) {
                for (int colonne = 0 ; colonne < nbColonnes ; colonne++) {
                    labyrintheActuel.getSalle(ligne, colonne).setSymbole(" ");

                }
            }
            cheminOptimalOrdi();
            nbDeplacement = 0;
        }
    }

    /**
     * Permet le déplacement dans le labyrinthe
     * Envoie un message lorsque le déplacement est impossible
     * Déplace l'utilisateur selon choix et si déplacement possible
     */
    public static void cheminOptimalOrdi() {
        for(int index = cheminOptimal.getTaille(); index > 0 ; index--) {
            salle = (Salle) cheminOptimal.sommet();
            salle.setSymbole("o");
            cheminOptimal.depiler();
        }
        System.out.println(labyrintheActuel.toString());

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
                joueur = new int[]{0,0};
                nbDeplacement = 0;
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
                + "il est deconseille de depasser 17 pour le nombre de ligne"
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
        joueur = new int[]{0,0};
        nbDeplacement = 0;
    }


    /**
     * Permet de récupéré un fichier prétélécharger dans un dossier
     * crée ensuite un labyrinthe à partir des données du fichier
     * L'utilisateur peut ensuite jouer avec le labyrinthe créé
     */
    public static void ChoixFichierSauvegarde() {
        boolean saisieOk;
        int nbFichier;
        System.out.println("\nListe des fichiers enregistres:");
        ControleurFichier.afficheFichierSauvegarde();
        do {
            System.out.print("\nEntrez un nombre correspondant au  "
                    + "nombre situer apres les deux points d'un fichier"
                    + "\nExemple : entrer 0 pour telecharger le fichier :"
                    + "\"SauvegardeLabyrinthe\\Labyrinthe_L3_C3_5 : 0"
                    + "\n Veuillez effectuer votre saisie : ");
            saisieOk = entreeUtilisateur.hasNextInt();
            if (saisieOk) {
                nbFichier = entreeUtilisateur.nextInt();
                saisieOk = nbFichier < ControleurFichier.nombreFichierSauvegarde()
                        && nbFichier >= 0;
                        if (saisieOk) {
                            System.out.println("Debut telechargement");
                            ControleurFichier.choixFichier(nbFichier);
                            ControleurFichier.RecupDonnee();
                            nbLignes = labyrintheActuel.getNbLignes();
                            nbColonnes = labyrintheActuel.getNbColonnes();
                            System.out.println("Un labyrinthe ( " + nbLignes + "," + nbColonnes + ") a ete cree");
                            System.out.println("Fin telechargement");
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
     * @param args non utilisé
     * @throws IOException lorsqu'un fichier n'est pas trouvé
     */
    public static void main(String[] args) throws IOException {
        //nouveauLabyrinthe();
        menu();

    }

}