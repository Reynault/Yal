package yal.arbre.expressions.expressionBinaire.expressionArithmetique;

import yal.arbre.expressions.Expression;

public class Soustraction extends ExpressionArithmetique {
    /**
     * Constructeur
     *
     * @param n      numéro de la ligne
     * @param gauche expression de gauche
     * @param droite expression de droite
     */
    public Soustraction(int n, Expression gauche, Expression droite) {
        super(n, gauche, droite);
    }
}
