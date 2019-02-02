package yal.arbre.expressions.expressionUnaire;

import yal.arbre.expressions.Expression;

public class ExpressionUnaire extends Expression {

    private Expression exp;

    /**
     * Constructeur qui prend le numéro de la ligne en paramètre
     *
     * @param n numéro de la ligne
     */
    protected ExpressionUnaire(int n) {
        super(n);
    }

    public void setExp(Expression exp) {
        this.exp = exp;
    }

    @Override
    public void verifier() {

    }

    @Override
    public String toMIPS() {
        return null;
    }
}
