package yal.arbre.instructions;

import yal.analyse.Symbole;
import yal.analyse.TDS;
import yal.arbre.expressions.Expression;

public class Lire extends Instruction{

    protected String idf;

    public Lire(String idf, int n) {
        super(n);
        this.idf = idf;
    }

    @Override
    public void verifier() {
    }

    @Override
    public String toMIPS() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\t# Lire un entier\n");
        stringBuilder.append("\tli $v0 , 5 \t# $v0 <- 5 (code du read entier)\n");
        stringBuilder.append("\tsyscall \t# le résultat de la lecture est dans $V0 \n");
        Symbole s = TDS.getInstance().identifier(idf);
        stringBuilder.append("\tsw $v0, "+s.getDeplacement()+"($s7)");
        return stringBuilder.toString();
    }
}
