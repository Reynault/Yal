package yal.arbre.expressions.expressionUnaire.expressionArithmetique;

import yal.arbre.expressions.Expression;
import yal.arbre.expressions.expressionUnaire.ExpressionUnaire;

public class Negatif extends ExpressionUnaire {
    /**
     * Constructeur qui prend le numéro de la ligne en paramètre
     *
     * @param n   numéro de la ligne
     * @param exp expression qui suit
     */
    public Negatif(int n, Expression exp) {
        super(n, exp);
    }
}
