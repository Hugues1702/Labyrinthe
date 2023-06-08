package iut.info1.labyrinthe;

import java.io.Serializable;

/**
 * Représente une salle de labyrinthe, avec ses murs au nord et à
 * l'ouest.
 * @author BAUDROIT Leïla
 * @author BERTRAND Hugues
 * @author BOYER Djedline
 * @author BRIOT Nael
 */

public class Salle implements Serializable{

    
    /** Identifiant serializable */
    private static final long serialVersionUID = 1141785540087881617L;

    /** 
     * Symbole permettant de connaître l'état du déplacement de l'utilisateur
     */
    private String symbole;

    /**
     * Le numéro assigné à la salle.
     */
    int index;

    /**
     * Vaut true si le mur au Nord est une porte, false sinon.
     */
    boolean porteNord;

    /**
     * Vaut true si le mur à l'Ouest est une porte, false sinon.
     */
    boolean porteOuest;

    /**
     * Marque assignée à chaque salle pour les algorithmes.
     */
    int marque;

    /**
     * Crée une salle de labyrinthe avec un numéro d'index.
     * @param index le numéro assigné à la salle
     */
    public Salle(int index) {
        super();
        if (!isValide(index)) {
            throw new IllegalArgumentException();
        }
        this.index = index;
        this.marque = 0;

        this.symbole = " ";
    }

    /**
     * Vérifie la validité d'un index 
     * @param index l'index à vérifier.
     * @return true si l'index est positif ou nul.
     *         false sinon
     */
    private static boolean isValide(int index) {
        return index >= 0;
    }

    /**
     * Permet de connaitre la marque de la salle
     * @return marque assignée à la salle
     */
    public int getMarque() {
        return marque;
    }

    /** 
     * Permet de changer la marque de la salle
     * @param marque la nouvelle marque assignée à la salle
     */
    public void setMarque(int marque) {
        this.marque = marque;
    }

    /**
     * Permet de savoir si une porte se trouve au nord de la salle
     * @return true si le mur au Nord est une porte,
     *         false autrement
     */
    public boolean isPorteNord() {
        return porteNord;
    }

    /** 
     * Permet de modifier l'ouverture de la porte au nord de la salle
     * @param porteNord la nouvelle valeur pour le mur au Nord de cette salle
     * - true si le mur est une porte
     * - false sinon
     */
    public void setPorteNord(boolean porteNord) {
        this.porteNord = porteNord;
    }

    /**
     * Permet de savoir si une porte se trouve à l'ouest de la salle
     * @return porteOuest qui vaut true si le mur à l'Ouest est une
     * porte, false autrement
     */
    public boolean isPorteOuest() {
        return porteOuest;
    }

    /** 
     * Permet de modifier l'ouverture de la porte à l'ouest de la salle
     * @param porteOuest la nouvelle valeur pour le mur à l'Ouest de cette salle
     *  true si le mur est une porte
     *  false sinon
     */
    public void setPorteOuest(boolean porteOuest) {
        this.porteOuest = porteOuest;
    }

    /** 
     * Le numéro d'indice de la salle au sein du labyrinthe.
     * @return index de la salle
     */
    public int getIndex() {
        return index;
    }
    /** 
     * Permet de connaître si l'utilisateur est présent
     * @return X si l'utilisateur est présent dans la salle
     *         . si l'utilisateur est passée dans la salle
     *         espace sinon
     */
    public String getPresenceJoueur() {
        return symbole;
    }

    /** 
     * Modifie le symbole à afficher
     * @param choixSymbole récupère le symbole à afficher dans la salle :
     *  X si l'utilisateur est présent dans la salle
     *  . si l'utilisateur est passée dans la salle
     *  espace sinon 
     */
    public void setSymbole(String choixSymbole) {
        symbole = choixSymbole;
    }

    /* non javadoc - @see java.lang.Object#toString() */
    @Override
    public String toString() {
        String resultat = "";
        if(isPorteNord()) {
            resultat += "-----+\n";
        }
        return resultat;
    }

    @Override
    public int hashCode() {
        int resultat = index;
        resultat *= 10;
        resultat += isPorteNord() ? 0 : 1;
        resultat *= 10;
        resultat += isPorteNord() ? 0 : 1;
        return resultat;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Salle other = (Salle) obj;
        return index == other.index && porteNord == other.porteNord && porteOuest == other.porteOuest;
    }



}