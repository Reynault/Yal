package yal.arbre.instructions;

import yal.arbre.BlocDInstructions;
import yal.arbre.GestionnaireNombres;
import yal.arbre.expressions.Expression;
import yal.exceptions.AnalyseSemantiqueException;

import java.util.ArrayList;

/**
 * Classe boucle qui modélise une boucle dans un programme yal
 */
public class Boucle extends Instruction {

    // Expression de condition
    protected Expression condition;
    // Bloc d'instructions
    protected BlocDInstructions instructions;

    /**
     * Constructeur
     * @param n numéro de la ligne
     * @param condition condition d'itération
     * @param instructions bloc d'instructions
     */
    public Boucle(int n, Expression condition, BlocDInstructions instructions) {
        super(n);
        this.condition = condition;
        this.instructions = instructions;
        if(condition.isArithmetique()){
            throw new AnalyseSemantiqueException(noLigne,"Une boucle doit prendre une expression logique en " +
                    "condition");
        }
    }

    @Override
    public ArrayList<Retourne> get_retourne() {
        return null;
    }

    @Override
    public void verifier() {
        condition.verifier();
        instructions.verifier();
    }

    @Override
    public String toMIPS() {
        int numero_boucle = GestionnaireNombres.getInstance().nouvelleIteration();
        StringBuilder sb = new StringBuilder();

        sb.append("BOUCLE" + numero_boucle + ":\n");
        sb.append("\t#evaluation de la condition\n");
        sb.append(condition.toMIPS());
        sb.append("\tbeqz $v0, BOUCLE" + numero_boucle + "FIN\n");
        sb.append(instructions.toMIPS());
        sb.append("\tb BOUCLE" + numero_boucle + "\n");
        sb.append("BOUCLE" + numero_boucle + "FIN:\n");

        return sb.toString();
    }
}
