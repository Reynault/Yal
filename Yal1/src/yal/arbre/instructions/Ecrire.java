package yal.arbre.instructions;

import yal.arbre.expressions.Expression;

public class Ecrire extends Instruction {

    protected Expression exp ;

    public Ecrire (Expression e, int n) {
        super(n) ;
        exp = e ;
    }

    @Override
    public void verifier() {
        exp.verifier();
    }

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
