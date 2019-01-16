package yal.arbre.expressions;

import yal.exceptions.AnalyseSemantiqueException;

public abstract class Constante extends Expression {

    protected String cste ;
    
    protected Constante(String texte, int n) {
        super(n) ;
        cste = texte ;
    }
    
    @Override
    public void verifier() {
        // On vérifie si c'est bien un entier correct
        try {
            int i = Integer.parseInt(cste);
        }catch (Exception e){
            throw new AnalyseSemantiqueException(noLigne,"Constante entière non correctement définie");
        }
    }

    @Override
    public String toString() {
        return cste ;
    }

}
