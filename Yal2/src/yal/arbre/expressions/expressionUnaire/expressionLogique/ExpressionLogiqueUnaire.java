package yal.arbre.expressions.expressionUnaire.expressionLogique;

import yal.arbre.expressions.Expression;
import yal.arbre.expressions.expressionUnaire.ExpressionUnaire;
import yal.exceptions.AnalyseSemantiqueException;

public class ExpressionLogiqueUnaire extends ExpressionUnaire {

    /**
     * Constructeur qui prend le numéro de la ligne en paramètre
     *
     * @param n   numéro de la ligne
     * @param exp expression qui suit
     */
    public ExpressionLogiqueUnaire(int n, Expression exp) {
        super(n, exp);
    }

    @Override
    public void verifier(){
        super.verifier();
        // Vérification de l'expression, qui doit être une expression logique
        if(exp.isArithmetique()){
            throw new AnalyseSemantiqueException(noLigne,"Une expression logique unaire doit être composée d'une" +
                    "expression logique");
        }
    }

    @Override
    public String toMIPS(){
        return super.toMIPS();
    }

    @Override
    public boolean isArithmetique() {
        return false;
    }
}
