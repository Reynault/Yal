package yal.arbre.expressions.expressionUnaire;

import yal.arbre.expressions.Expression;

/**
 * Classe qui modélise une expression unaire
 */
public class ExpressionUnaire extends Expression {
    // Expression qui suit
    protected Expression exp;

    /**
     * Constructeur qui prend le numéro de la ligne en paramètre
     *
     * @param n numéro de la ligne
     * @param exp expression qui suit
     */
    public ExpressionUnaire(int n, Expression exp) {
        super(n);
        this.exp = exp;
    }




    @Override
    public void verifier() {

    }

    @Override
    public String toMIPS() {
        return exp.toMIPS();
    }
}
