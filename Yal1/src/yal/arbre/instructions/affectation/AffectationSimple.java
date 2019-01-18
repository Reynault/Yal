package yal.arbre.instructions.affectation;

import yal.analyse.Symbole;
import yal.analyse.TDS;
import yal.arbre.expressions.Expression;
import yal.exceptions.AnalyseSemantiqueException;

/**
 * Classe qui représente une affectation simple (à une variable entière)
 */
public class AffectationSimple extends Affectation {
    // Expression à affecter
    protected  Expression exp;
    // Identificateur de la variable
    protected  String idf;

    /**
     * Constructeur à trois paramètres
     * @param n le numéro de la ligne
     * @param exp expression à affecter
     * @param idf variable
     */
    public AffectationSimple(int n, Expression exp, String idf) {
        super(n);
        this.exp = exp;
        this.idf = idf;
    }

    /**
     * Méthode de vérification de la sémantique
     */
    @Override
    public void verifier() {
        // Vérification de l'expression
        exp.verifier();
        // Vérification de l'existence de la variable
        if(!TDS.getInstance().existe(idf)){
            throw new AnalyseSemantiqueException(noLigne,"Identificateur non déclaré");
        }
    }

    /**
     * Méthode de traduction en Mips
     * @return code mips
     */
    @Override
    public String toMIPS() {
        // Construction du code
        StringBuilder sb = new StringBuilder();
        // Récupération du symbole lié à la variable
        Symbole symbole = TDS.getInstance().identifier(idf);
        // Récupération du code de l'expression
        sb.append(exp.toMIPS());
        sb.append("\t# Affectation simple\n");
        sb.append("\tsw $v0, "+symbole.getDeplacement()+"($s7)\n");
        return sb.toString();
    }
}
