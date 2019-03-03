package yal.arbre.instructions.affectation;

import yal.arbre.expressions.Expression;
import yal.arbre.expressions.Variable;
import yal.exceptions.AnalyseSemantiqueException;

/**
 * Classe qui représente une affectation simple (à une variable entière)
 */
public class AffectationSimple extends Affectation {
    // Expression à affecter
    protected  Expression exp;
    // Variable
    protected Variable idf;

    /**
     * Constructeur à trois paramètres
     * @param n le numéro de la ligne
     * @param exp expression à affecter
     * @param idf variable
     */
    public AffectationSimple(int n, Expression exp, Variable idf) {
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
        idf.verifier();
        exp.verifier();
        if(!exp.isArithmetique()){
            throw new AnalyseSemantiqueException(noLigne,"Compatibilié de type invalide : Booléen -> Entier");
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
        // Récupération du code de l'expression
        sb.append(idf.placerT8());
        sb.append("\tsw $t8, 0($sp)\n");
        sb.append("\taddi $sp, $sp, -4\n");
        sb.append(exp.toMIPS());
        sb.append("\t# Affectation simple\n");
        sb.append("\taddi $sp, $sp, 4\n");
        sb.append("\tlw $t8, 0($sp)\n");
        sb.append("\tsw $v0, "+idf.getDeplacement()+"($t8)\n");
        return sb.toString();
    }
}
