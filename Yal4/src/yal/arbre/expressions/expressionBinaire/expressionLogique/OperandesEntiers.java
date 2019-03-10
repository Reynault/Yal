package yal.arbre.expressions.expressionBinaire.expressionLogique;

import yal.arbre.expressions.Expression;
import yal.exceptions.AnalyseSemantiqueException;

public class OperandesEntiers extends ExpressionLogiqueBinaire {
    /**
     * Constructeur
     *
     * @param n      numéro de la ligne
     * @param gauche expression de gauche
     * @param droite expression de droite
     */
    public OperandesEntiers(int n, Expression gauche, Expression droite) {
        super(n, gauche, droite);
    }

    @Override
    public void verifier() {
        super.verifier();
        if(!gauche.isArithmetique() || !droite.isArithmetique()){
            throw new AnalyseSemantiqueException(noLigne,"Les opérateurs > et < doivent avoir des opérandes de " +
                    "type entier");
        }
    }
}
