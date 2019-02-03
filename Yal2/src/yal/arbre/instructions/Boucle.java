package yal.arbre.instructions;

import yal.arbre.BlocDInstructions;
import yal.arbre.GestionnaireNombres;
import yal.arbre.expressions.Expression;

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
    }

    @Override
    public void verifier() {

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
