package yal.arbre.expressions;

import yal.exceptions.AnalyseSemantiqueException;

public class ConstanteEntiere extends Constante {
    
    public ConstanteEntiere(String texte, int n) {
        super(texte, n) ;
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
    public String toMIPS() {
        StringBuilder sb = new StringBuilder("") ;
        sb.append("                   # Chargement immédiat d'une constante entière\n");
        sb.append("    li $v0, ") ;
        sb.append(cste) ;
        sb.append("\n") ;
        return sb.toString() ;
    }

}
