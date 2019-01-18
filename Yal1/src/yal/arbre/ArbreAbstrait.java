package yal.arbre;

/**
 * Classe qui représente un arbre abstrait dans lequel on va stocker le code fourni
 * par l'utilisateur
 */
public abstract class ArbreAbstrait {
    
    // Numéro de ligne du début de l'instruction
    protected int noLigne ;

    /**
     * Constructeur
     * @param n numéro de la ligne
     */
    protected ArbreAbstrait(int n) {
        noLigne = n ;
    }

    /**
     * Méthode qui permet de récupérer le numéro de la ligne
     * @return
     */
    public int getNoLigne() {
            return noLigne ;
    }

    /**
     * Méthode de vérification de la sémantique
     */
    public abstract void verifier() ;

    /**
     * Méthode de traduction en mips
     * @return le code en mips
     */
    public abstract String toMIPS();

}
