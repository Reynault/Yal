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
        sb.append("\tsw $v0, 0($sp)\n");
        sb.append("\tadd $sp, $sp, -4\n");
        sb.append(droite.toMIPS());
        sb.append("\tadd $sp, $sp, 4\n");
        sb.append("\tlw $t8, 0($sp)\n");
        return sb.toString();
    }
}
