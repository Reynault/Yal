package yal.arbre.expressions.expressionUnaire.expressionLogique;

import yal.arbre.GestionnaireNombres;
import yal.arbre.expressions.Expression;
import yal.arbre.expressions.expressionUnaire.ExpressionUnaire;

public class Non extends ExpressionLogique {
    /**
     * Constructeur qui prend le numéro de la ligne en paramètre
     *
     * @param n   numéro de la ligne
     * @param exp expression qui suit
     */
    public Non(int n, Expression exp) {
        super(n, exp);
    }

    @Override
    public void verifier() {

    }

    @Override
    public String toMIPS() {
        String res = super.toMIPS();
        int numero = GestionnaireNombres.getInstance().nouvelleExpression();
        StringBuilder sb = new StringBuilder();
        sb.append(res);
        sb.append("\tbeqz $v0, Vrai"+ numero +"\n");
        sb.append("\tli $v0, 0\n");
        sb.append("\tb FinSi"+numero+"\n");
        sb.append("\tVrai"+ numero +" :\n");
        sb.append("\tli $v0, 1\n");
        sb.append("\tFinSi"+numero+":\n");
        return sb.toString();
    }
}
