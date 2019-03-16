package yal.arbre.instructions;

import yal.arbre.ArbreAbstrait;

import java.util.ArrayList;

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
