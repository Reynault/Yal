package yal.arbre.expressions.expressionUnaire.expressionArithmetique;

import yal.arbre.expressions.Expression;
import yal.arbre.expressions.expressionUnaire.ExpressionUnaire;

public class Negatif extends ExpressionArithmetique{
    /**
     * Constructeur qui prend le numéro de la ligne en paramètre
     *
     * @param n   numéro de la ligne
     * @param exp expression qui suit
     */
    public Negatif(int n, Expression exp) {
        super(n, exp);
    }

    @Override
    public void verifier() {

    }

    @Override
    public String toMIPS() {
        String res = super.toMIPS();
        StringBuilder sb = new StringBuilder();
        sb.append(res);
        sb.append("\tneg $v0, $v0\n");
        return sb.toString();
    }
}
