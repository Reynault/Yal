package yal.arbre.instructions;

import yal.arbre.expressions.Expression;
import yal.exceptions.AnalyseSemantiqueException;

public class Retourne extends Instruction{
    protected Expression e;
    /**
     * Constructeur
     *
     * @param n numéro de la ligne
     */
    public Retourne(int n, Expression e) {
        super(n);
        this.e = e;
    }

    @Override
    public void verifier() {
        e.verifier();
        if(!e.isArithmetique()){
            throw new AnalyseSemantiqueException(noLigne,"Une fonction doit retourner un entier.");
        }
    }

    @Override
    public String toMIPS() {
        return null;
    }
}
