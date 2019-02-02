package yal.arbre.instructions;

import yal.arbre.expressions.Expression;

/**
 * Classe qui représente une instruction d'écriture
 */
public class Ecrire extends Instruction {
    // Expression à écrire
    protected Expression exp ;

    /**
     * Constructeur
     * @param e expression à écrire
     * @param n numéro de la ligne
     */
    public Ecrire (Expression e, int n) {
        super(n) ;
        exp = e ;
    }

    /**
     * Méthode de vérification de la sémantique
     */
    @Override
    public void verifier() {
        exp.verifier();
    }

    /**
     * Méthode de traduction en mips
     * @return code en mips
     */
    @Override
    public String toMIPS() {
        return  "\t# affichage de l'expression\n" +
                exp.toMIPS() +
                "\tmove $a0, $v0\n" +
                "\tli $v0, 1\n" +
                "\tsyscall\n" +
                "\tli $v0, 4      # retour à la ligne\n" +
                "\tla $a0, finLigne\n" +
                "\tsyscall\n" ;
    }

}
