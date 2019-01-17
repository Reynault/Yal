package yal.arbre.instructions;

import yal.arbre.expressions.Expression;

public class AffectExp extends Affectation{
    protected Expression exp;
    protected AffectExp(int n) {
        super(n);
    }

    @Override
    public void verifier() {
        exp.verifier();
    }

    @Override
    public String toMIPS() {
        return null;
    }
}
