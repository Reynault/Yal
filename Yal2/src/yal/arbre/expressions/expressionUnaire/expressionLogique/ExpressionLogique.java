package yal.arbre.expressions.expressionUnaire.expressionLogique;

import yal.arbre.expressions.Expression;
import yal.arbre.expressions.expressionUnaire.ExpressionUnaire;

public class ExpressionLogique extends ExpressionUnaire {

    /**
     * Constructeur qui prend le numéro de la ligne en paramètre
     *
     * @param n   numéro de la ligne
     * @param exp expression qui suit
     */
    public ExpressionLogique(int n, Expression exp) {
        super(n, exp);
    }

    @Override
    public void verifier(){
        super.verifier();
    }

    @Override
    public String toMIPS(){
        return super.toMIPS();
    }

    @Override
    protected boolean isArithmetique() {
        return false;
    }
}
