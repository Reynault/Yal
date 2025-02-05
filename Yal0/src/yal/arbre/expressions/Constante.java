package yal.arbre.expressions;

import yal.exceptions.AnalyseSemantiqueException;

public abstract class Constante extends Expression {

    protected String cste ;
    
    protected Constante(String texte, int n) {
        super(n) ;
        cste = texte ;
    }
    
    @Override
    public abstract void verifier();

    @Override
    public String toString() {
        return cste ;
    }

}
