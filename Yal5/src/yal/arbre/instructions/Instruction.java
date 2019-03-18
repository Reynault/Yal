package yal.arbre.instructions;

import yal.arbre.ArbreAbstrait;

/**
 * Classe qui représente une instruction
 */
public abstract class Instruction extends ArbreAbstrait {
    /**
     * Constructeur
     * @param n numéro de la ligne
     */
    protected Instruction(int n) {
        super(n);
    }
}
