package yal.arbre.instructions;

import yal.arbre.BlocDInstructions;
import yal.arbre.GestionnaireNombres;
import yal.arbre.expressions.Expression;

/**
 * Classe qui représente une condition
 */
public class Condition extends Instruction{
    // Condition du si
    protected Expression condition;
    protected BlocDInstructions alors;
    protected BlocDInstructions sinon;

    /**
     * constructeur
     * @param n numéro de la ligne
     * @param condition condition du si
     * @param alors bloc alors
     * @param sinon bloc sinon
     */
    public Condition(int n, Expression condition, BlocDInstructions alors, BlocDInstructions sinon) {
        super(n);
        this.condition = condition;
        this.alors = alors;
        this.sinon = sinon;
    }

    @Override
    public void verifier() {
        condition.verifier();
        alors.verifier();
        sinon.verifier();
    }

    @Override
    public String toMIPS() {
        int numero = GestionnaireNombres.getInstance().nouvelleCondition();
        StringBuilder sb = new StringBuilder();
        sb.append(condition.toMIPS());
        sb.append("\tbeqz $v0, Alors"+ numero +"\n");
        sb.append(alors.toMIPS());
        sb.append("\tb FinSiAlors"+numero+"\n");
        sb.append("\tAlors"+ numero +" :\n");
        sb.append(sinon.toMIPS());
        sb.append("\tFinSiAlors"+numero+":\n");
        return sb.toString();
    }
}
