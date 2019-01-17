package yal.arbre.expressions;

import yal.analyse.Symbole;
import yal.analyse.TDS;

public class Variable extends Expression{
    protected String idf;

    public Variable(int n, String idf) {
        super(n);
        this.idf = idf;
    }

    @Override
    public void verifier() {

    }

    @Override
    public String toMIPS() {
        Symbole symbole = TDS.getInstance().identifier(idf);
        StringBuilder sb = new StringBuilder();
        sb.append("\t# Chargement de la valeur de la variable dans v0\n");
        sb.append("\tlw $v0, "+symbole.getDeplacement()+"($s7)\n");
        return sb.toString();
    }
}
