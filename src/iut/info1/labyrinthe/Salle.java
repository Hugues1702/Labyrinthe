package iut.info1.labyrinthe;

/**
 * Représente une salle de labyrinthe, avec ses murs au nord et à
 * l'ouest.
 * @author BAUDROIT Leïla
 * @author BERTRAND Hugues
 * @author BOYER Djedline
 * @author BRIOT Nael
 */
public class Salle {
        
        boolean presenceJoueur;
        
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
         * @return marque la marque assignée à la salle
         */
        public int getMarque() {
                return marque;
        }

        /** 
         * @param marque la nouvelle marque assignée à la salle
         */
        public void setMarque(int marque) {
                this.marque = marque;
        }

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
                
                this.presenceJoueur = false;
        }

        /**
         * Vérifie la validité d'un index 
         * @param index l'index à vérifier.
         * @return true si l'index est positif ou nul.
         */
        private static boolean isValide(int index) {
                return index >= 0;
        }

        /**
         * @return porteNord qui vaut true si le mur au Nord est une
         * porte, false autrement
         */
        public boolean isPorteNord() {
                return porteNord;
        }

        /** 
         * @param porteNord 
         * @param la nouvelle valeur pour le mur au Nord de cette salle
         * - true si le mur est une porte
         * - false sinon
         */
        public void setPorteNord(boolean porteNord) {
                this.porteNord = porteNord;
        }

        /**
         * @return porteOuest qui vaut true si le mur à l'Ouest est une
         * porte, false autrement
         */
        public boolean isPorteOuest() {
                return porteOuest;
        }

        /** 
         * @param porteOuest 
         * @param la nouvelle valeur pour le mur à l'Ouest de cette salle
         * - true si le mur est une porte
         * - false sinon
         */
        public void setPorteOuest(boolean porteOuest) {
                this.porteOuest = porteOuest;
        }

        /** 
         * Le numéro d'indice de la salle au sein du labyrinthe.
         * @return index
         */
        public int getIndex() {
                return index;
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
        
        /** TODO comment method role
         * @return resultat
         */
        public String getPresenceJoueur() {
            String resultat;
            if (presenceJoueur) {
                resultat = "X";
            } else {
                resultat = ".";
            }
            return resultat;
        }
        
        /** TODO comment method role
         * @param joueurpresent
         */
        public void setPresenceJoueur(boolean joueurpresent) {
            presenceJoueur = joueurpresent;
        }

}