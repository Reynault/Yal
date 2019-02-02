package yal.arbre.expressions.expressionBinaire.expressionLogique;

import yal.arbre.expressions.Expression;

public class Ou extends ExpressionLogique {
    /**
     * Constructeur
     *
     * @param n      numéro de la ligne
     * @param gauche expression de gauche
     * @param droite expression de droite
     */
    public Ou(int n, Expression gauche, Expression droite) {
        super(n, gauche, droite);
    }
}
