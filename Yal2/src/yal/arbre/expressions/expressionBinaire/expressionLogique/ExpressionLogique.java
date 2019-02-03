package yal.arbre.expressions.expressionBinaire.expressionLogique;

import yal.arbre.GestionnaireNombres;
import yal.arbre.expressions.Expression;
import yal.arbre.expressions.expressionBinaire.ExpressionBinaire;

public class ExpressionLogique extends ExpressionBinaire {
    /**
     * Constructeur
     *
     * @param n      numéro de la ligne
     * @param gauche expression de gauche
     * @param droite expression de droite
     */
    public ExpressionLogique(int n, Expression gauche, Expression droite) {
        super(n, gauche, droite);
    }

    @Override
    public void verifier() {
        super.verifier();
    }

    @Override
    public String toMIPS() {
        return super.toMIPS();
    }

    @Override
    protected boolean isArithmetique() {
        return false;
    }
}
