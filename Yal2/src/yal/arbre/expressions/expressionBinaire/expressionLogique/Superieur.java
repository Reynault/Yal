package yal.arbre.expressions.expressionBinaire.expressionLogique;

import yal.arbre.GestionnaireNombres;
import yal.arbre.expressions.Expression;

public class Superieur extends ExpressionLogique {
    /**
     * Constructeur
     *
     * @param n      numéro de la ligne
     * @param gauche expression de gauche
     * @param droite expression de droite
     */
    public Superieur(int n, Expression gauche, Expression droite) {
        super(n, gauche, droite);
    }

    @Override
    public void verifier() {
        super.verifier();
    }

    @Override
    public String toMIPS() {
        String res = super.toMIPS();
        int numero = GestionnaireNombres.getInstance().nouvelleExpression();
        StringBuilder sb = new StringBuilder();
        sb.append(res);
        sb.append("bgt $t8, $v0, Vrai"+ numero +"\n");
        sb.append("li $v0, 0\n");
        sb.append("b FinSi"+numero+"\n");
        sb.append("Vrai"+ numero +" :\n");
        sb.append("li $v0, 1\n");
        sb.append("FinSi"+numero+":\n");
        return sb.toString();
    }
}
