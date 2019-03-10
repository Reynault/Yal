package yal.arbre.instructions.affectation;

import yal.arbre.instructions.Instruction;

/**
 * Classe qui représente une affectation
 */
public abstract class Affectation extends Instruction {
    /**
     * Constructeur
     * @param n numéro de la ligne
     */
    protected Affectation(int n) {
        super(n);
    }
}
