package yal.arbre.expressions.expressionBinaire;

import yal.arbre.expressions.Expression;

/**
 * Class qui modélise une expression binaire
 */
public class ExpressionBinaire extends Expression {
    // Expression de gauche
    protected Expression gauche;
    // Expression de droite
    protected Expression droite;

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
        StringBuilder sb = new StringBuilder();
        sb.append(gauche.toMIPS());
        sb.append("sw $v0, 0($sb)");
        sb.append("add $sb, $sb, -4");
        sb.append(droite.toMIPS());
        sb.append("add $sb, $sb, 4");
        sb.append("lw $t8, 0($sb)");
        return sb.toString();
    }
}
