package yal.arbre.expressions.expressionUnaire.expressionArithmetique;

import yal.arbre.expressions.Expression;
import yal.arbre.expressions.expressionUnaire.ExpressionUnaire;

public class ExpressionArithmetiqueUnaire extends ExpressionUnaire {
    /**
     * Constructeur qui prend le numéro de la ligne en paramètre
     *
     * @param n   numéro de la ligne
     * @param exp expression qui suit
     */
    public ExpressionArithmetiqueUnaire(int n, Expression exp) {
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
        return true;
    }
}
