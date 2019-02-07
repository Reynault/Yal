package yal.arbre.expressions.expressionBinaire.expressionLogique;

import yal.arbre.expressions.Expression;
import yal.exceptions.AnalyseSemantiqueException;

public class OperandesBooleens extends ExpressionLogiqueBinaire {
    /**
     * Constructeur
     *
     * @param n      numéro de la ligne
     * @param gauche expression de gauche
     * @param droite expression de droite
     */
    public OperandesBooleens(int n, Expression gauche, Expression droite) {
        super(n, gauche, droite);
    }

    @Override
    public void verifier() {
        super.verifier();
        if(gauche.isArithmetique() || droite.isArithmetique()){
            throw new AnalyseSemantiqueException(noLigne,"Les opérandes des opérateurs ET et OU doivent" +
                    "être des booléens");
        }
    }
}
