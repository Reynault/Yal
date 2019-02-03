package yal.arbre.expressions;

import yal.arbre.ArbreAbstrait;

/**
 * Classe qui représente une expression
 *
 * Elle hérite de la classe ArbreAbstrait
 */
public abstract class Expression extends ArbreAbstrait {
    /**
     * Constructeur qui prend le numéro de la ligne en paramètre
     * @param n numéro de la ligne
     */
    protected Expression(int n) {
        super(n) ;
    }

    protected abstract boolean isArithmetique();
}
