package yal.arbre.expressions.expressionBinaire.expressionArithmetique;

import yal.arbre.expressions.Expression;
import yal.arbre.expressions.expressionBinaire.ExpressionBinaire;
import yal.exceptions.AnalyseSemantiqueException;

public class ExpressionArithmetiqueBinaire extends ExpressionBinaire {
    /**
     * Constructeur
     *
     * @param n      numéro de la ligne
     * @param gauche expression de gauche
     * @param droite expression de droite
     */
    public ExpressionArithmetiqueBinaire(int n, Expression gauche, Expression droite) {
        super(n, gauche, droite);
    }

    @Override
    public void verifier() {
        super.verifier();
        // On vérifie si les deux opérandes sont des expressions arithmétiques
        if(!gauche.isArithmetique() || !droite.isArithmetique()){
            throw new AnalyseSemantiqueException(noLigne,"Une expression arithmétique doit être composée uniquement" +
                    "d'autres expressions arithmétiques");
        }
    }

    @Override
    public String toMIPS() {
        return super.toMIPS();
    }

    @Override
    public boolean isArithmetique() {
        return true;
    }
}
