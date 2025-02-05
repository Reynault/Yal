package yal.arbre;

import yal.arbre.instructions.Retourne;

import java.util.ArrayList;

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

    public abstract ArrayList<Retourne> get_retourne();

    public abstract int get_nb_retourne();
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
