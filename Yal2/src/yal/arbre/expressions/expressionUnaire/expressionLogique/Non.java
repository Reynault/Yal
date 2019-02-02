package yal.arbre.expressions.expressionUnaire.expressionLogique;

import yal.arbre.expressions.Expression;
import yal.arbre.expressions.expressionUnaire.ExpressionUnaire;

public class Non extends ExpressionUnaire {
    /**
     * Constructeur qui prend le numéro de la ligne en paramètre
     *
     * @param n   numéro de la ligne
     * @param exp expression qui suit
     */
    public Non(int n, Expression exp) {
        super(n, exp);
    }
}
