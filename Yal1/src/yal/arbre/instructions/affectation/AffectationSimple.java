package yal.arbre.instructions.affectation;

import yal.analyse.Symbole;
import yal.analyse.TDS;
import yal.arbre.expressions.Expression;

public class AffectationSimple extends Affectation {
    protected  Expression exp;
    protected  String idf;

    public AffectationSimple(int n, Expression exp, String idf) {
        super(n);
        this.exp = exp;
        this.idf = idf;
    }

    @Override
    public void verifier() {
        exp.verifier();
    }

    @Override
    public String toMIPS() {
        StringBuilder sb = new StringBuilder();
        Symbole symbole = TDS.getInstance().identifier(idf);
        sb.append(exp.toMIPS());
        sb.append("\t# Affectation simple\n");
        sb.append("\tsw $v0, "+symbole.getDeplacement()+"($s7)\n");
        return sb.toString();
    }
}
