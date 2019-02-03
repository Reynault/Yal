package yal.arbre.expressions.expressionBinaire.expressionLogique;

import yal.arbre.expressions.Expression;

public class Ou extends ExpressionLogiqueBinaire {
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

    @Override
    public void verifier() {
        super.verifier();
    }

    @Override
    public String toMIPS() {
        String res = super.toMIPS();
        StringBuilder sb = new StringBuilder();
        sb.append(res);
        sb.append("\tor $v0, $t8, $v0\n");
        return sb.toString();
    }
}
