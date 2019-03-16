package yal.arbre.instructions;

import yal.arbre.BlocDInstructions;
import yal.arbre.GestionnaireNombres;
import yal.arbre.expressions.Expression;
import yal.exceptions.AnalyseSemantiqueException;

import java.util.ArrayList;

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
    public ArrayList<Retourne> get_retourne() {
        ArrayList<Retourne> res = new ArrayList<Retourne>();
        ArrayList<Retourne> resAlors;
        ArrayList<Retourne> resSinon;

        if(alors != null && sinon != null) {
            resAlors = alors.get_retourne();
            resSinon = sinon.get_retourne();
            if (resAlors.size() != 0 && resSinon.size() != 0) {
                res.addAll(resAlors);
                res.addAll(resSinon);
            }
            // Gestion du cas dans lequel l'un possède un retourne et pas l'autre
            if ((resAlors.size() == 0 && resSinon.size() != 0 )
                    || resAlors.size() != 0 && resSinon.size() == 0) {
                throw new AnalyseSemantiqueException(noLigne, "L'instruction retourne doit apparaître dans la clause si " +
                        "ET dans la clause alors");
            }
        }

        if(sinon != null && alors == null){
            resSinon = sinon.get_retourne();
            if (resSinon.size() != 0){
                throw new AnalyseSemantiqueException(noLigne, "L'instruction retourne doit apparaître dans la clause si " +
                        "ET dans la clause alors");
            }
        }
        return res;
    }

    @Override
    public void verifier() {
        condition.verifier();
        if (alors != null){
            alors.verifier();
        }
        if (sinon != null){
            sinon.verifier();
        }
    }

    @Override
    public String toMIPS() {
        int numero = GestionnaireNombres.getInstance().nouvelleCondition();
        StringBuilder sb = new StringBuilder();
        sb.append(condition.toMIPS());
        sb.append("\tbeqz $v0, Alors"+ numero +"\n");
        if (alors != null){
            sb.append(alors.toMIPS());
        }
        sb.append("\tb FinSiAlors"+numero+"\n");
        sb.append("\tAlors"+ numero +" :\n");
        if (sinon != null){
            sb.append(sinon.toMIPS());
        }
        sb.append("\tFinSiAlors"+numero+":\n");
        return sb.toString();
    }
}
