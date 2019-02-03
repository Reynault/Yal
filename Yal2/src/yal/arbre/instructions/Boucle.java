package yal.arbre.instructions;

import yal.arbre.BlocDInstructions;
import yal.arbre.expressions.Expression;

/**
 * Classe boucle qui modélise une boucle dans un programme yal
 */
public class Boucle extends Instruction {

    // Expression de condition
    protected Expression condition;
    // Bloc d'instructions
    protected BlocDInstructions instructions;

    /**
     * Constructeur
     * @param n numéro de la ligne
     * @param condition condition d'itération
     * @param instructions bloc d'instructions
     */
    public Boucle(int n, Expression condition, BlocDInstructions instructions) {
        super(n);
        this.condition = condition;
        this.instructions = instructions;
    }

    @Override
    public void verifier() {

    }

    @Override
    public String toMIPS() {
        return null;
    }
}
