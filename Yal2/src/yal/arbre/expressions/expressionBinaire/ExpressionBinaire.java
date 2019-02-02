package yal.arbre.expressions.expressionBinaire;

import yal.arbre.expressions.Expression;

public class ExpressionBinaire extends Expression {

    private Expression gauche;
    private Expression droite;

    /**
     * Constructeur qui prend le numéro de la ligne en paramètre
     *
     * @param n numéro de la ligne
     */
    protected ExpressionBinaire(int n) {
        super(n);
    }

    public void setGauche(Expression gauche) {
        this.gauche = gauche;
    }

    public void setDroite(Expression droite) {
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
