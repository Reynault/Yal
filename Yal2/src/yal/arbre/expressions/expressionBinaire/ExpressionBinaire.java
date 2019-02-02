package yal.arbre.expressions.expressionBinaire;

import yal.arbre.expressions.Expression;

/**
 * Class qui modélise une expression binaire
 */
public class ExpressionBinaire extends Expression {
    // Expression de gauche
    private Expression gauche;
    // Expression de droite
    private Expression droite;

    /**
     * Constructeur
     * @param n numéro de la ligne
     * @param gauche expression de gauche
     * @param droite expression de droite
     */
    public ExpressionBinaire(int n, Expression gauche, Expression droite) {
        super(n);
        this.gauche = gauche;
        this.droite = droite;
    }

    @Override
    public void verifier() {

    }

    @Override
    public String toMIPS() {
        return null;
    }
}
