package yal.arbre.expressions;

import yal.arbre.ArbreAbstrait;

/**
 * Classe qui représente une expression
 *
 * Elle hérite de la classe ArbreAbstrait
 */
public class Expression extends ArbreAbstrait {
    /**
     * Constructeur qui prend le numéro de la ligne en paramètre
     * @param n numéro de la ligne
     */
    protected Expression(int n) {
        super(n) ;
    }

    @Override
    public void verifier() {
    }

    @Override
    public String toMIPS() {
        return null;
    }

    /**
     * Méthode isArithmetique qui permet d'identifier les expressions qui sont
     * des expressions arithmétiques.
     *
     * Méthode utilisée dans la vérification de l'arbre abstrait
     * @return
     */
    public boolean isArithmetique(){
        return false;
    }
}
