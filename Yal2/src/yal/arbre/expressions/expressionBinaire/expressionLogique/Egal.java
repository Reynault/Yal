package yal.arbre.expressions.expressionBinaire.expressionLogique;

import yal.arbre.expressions.Expression;

public class Egal extends ExpressionLogique {
    /**
     * Constructeur
     *
     * @param n      numéro de la ligne
     * @param gauche expression de gauche
     * @param droite expression de droite
     */
    public Egal(int n, Expression gauche, Expression droite) {
        super(n, gauche, droite);
    }
}
