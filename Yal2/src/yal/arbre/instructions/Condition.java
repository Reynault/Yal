package yal.arbre.instructions;

import yal.arbre.BlocDInstructions;
import yal.arbre.expressions.Expression;

/**
 * Classe qui représente une condition
 */
public class Condition extends Instruction{
    // Condition du si
    protected Expression condition;
    protected BlocDInstructions alors;
    protected BlocDInstructions sinon;

    /**
     * constructeur
     * @param n numéro de la ligne
     * @param condition condition du si
     * @param alors bloc alors
     * @param sinon bloc sinon
     */
    public Condition(int n, Expression condition, BlocDInstructions alors, BlocDInstructions sinon) {
        super(n);
        this.condition = condition;
        this.alors = alors;
        this.sinon = sinon;
    }

    @Override
    public void verifier() {

    }

    @Override
    public String toMIPS() {
        return null;
    }
}