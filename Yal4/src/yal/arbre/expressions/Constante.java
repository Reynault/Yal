package yal.arbre.expressions;

import yal.exceptions.AnalyseSemantiqueException;

/**
 * Classe qui représente une constante dans une expression
 */
public abstract class Constante extends Expression {
    // La valeur de la constante
    protected String cste ;

    /**
     * Constructeur à deux paramètres
     * @param texte la valeur de la constante
     * @param n le numéro de la ligne
     */
    protected Constante(String texte, int n) {
        super(n) ;
        cste = texte ;
    }

    /**
     * Méthode qui permet de vérifier la sémantique
     */
    @Override
    public abstract void verifier();

    /**
     * Méthode de traduction en mips
     * @return code en mips
     */
    @Override
    public String toString() {
        return cste ;
    }

}
