package yal.arbre.expressions.expressionUnaire.expressionArithmetique;

import yal.arbre.expressions.Expression;
import yal.arbre.expressions.expressionUnaire.ExpressionUnaire;
import yal.exceptions.AnalyseSemantiqueException;

public class ExpressionArithmetiqueUnaire extends ExpressionUnaire {
    /**
     * Constructeur qui prend le numéro de la ligne en paramètre
     *
     * @param n   numéro de la ligne
     * @param exp expression qui suit
     */
    public ExpressionArithmetiqueUnaire(int n, Expression exp) {
        super(n, exp);
    }

    @Override
    public void verifier(){
        super.verifier();
        // On vérifie si l'expression est entière
        if(!exp.isArithmetique()){
            throw new AnalyseSemantiqueException(noLigne, "Une expression arithmétique unaire est composée d'une" +
                    "autre expression arithmétique");
        }
    }

    @Override
    public String toMIPS(){
        return super.toMIPS();
    }

    @Override
    public boolean isArithmetique() {
        return true;
    }
}
