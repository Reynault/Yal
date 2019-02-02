package yal.arbre.expressions.expressionBinaire.expressionLogique;

import yal.arbre.expressions.Expression;

public class Et extends ExpressionLogique {
    /**
     * Constructeur
     *
     * @param n      numéro de la ligne
     * @param gauche expression de gauche
     * @param droite expression de droite
     */
    public Et(int n, Expression gauche, Expression droite) {
        super(n, gauche, droite);
    }
}
