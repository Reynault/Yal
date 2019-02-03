package yal.arbre.expressions;

import yal.exceptions.AnalyseSemantiqueException;

/**
 * Classe qui représente une constante entière
 */
public class ConstanteEntiere extends Constante {

    /**
     * Constructeur de la classe constante entiere
     * @param texte valeur de la constante
     * @param n numéro de la ligne
     */
    public ConstanteEntiere(String texte, int n) {
        super(texte, n) ;
    }

    /**
     * Méthode de vérification de la sémantique
     */
    @Override
    public void verifier() {
        // On vérifie si c'est bien un entier correct
        try {
            int i = Integer.parseInt(cste);
        }catch (Exception e){
            throw new AnalyseSemantiqueException(noLigne,"Constante entière non correctement définie");
        }
    }

    /**
     * Méthode de traduction en mips
     * @return
     */
    @Override
    public String toMIPS() {
        StringBuilder sb = new StringBuilder("") ;
        sb.append("\t# Chargement immédiat d'une constante entière\n");
        sb.append("\tli $v0, ") ;
        sb.append(cste) ;
        sb.append("\n") ;
        return sb.toString() ;
    }

    @Override
    public boolean isArithmetique() {
        return true;
    }
}
