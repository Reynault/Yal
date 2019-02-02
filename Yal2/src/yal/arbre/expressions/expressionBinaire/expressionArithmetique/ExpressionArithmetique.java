package yal.arbre.expressions.expressionBinaire.expressionArithmetique;

import yal.arbre.expressions.Expression;
import yal.arbre.expressions.expressionBinaire.ExpressionBinaire;

public class ExpressionArithmetique extends ExpressionBinaire {
    /**
     * Constructeur
     *
     * @param n      numéro de la ligne
     * @param gauche expression de gauche
     * @param droite expression de droite
     */
    public ExpressionArithmetique(int n, Expression gauche, Expression droite) {
        super(n, gauche, droite);
    }
}
