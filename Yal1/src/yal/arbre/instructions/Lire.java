package yal.arbre.instructions;

import yal.arbre.expressions.Expression;

public class Lire extends Instruction{

    protected Lire(Expression exp, int n) {
        super(n);
    }

    @Override
    public void verifier() {
    }

    @Override
    public String toMIPS() {
        return null;
    }
}
