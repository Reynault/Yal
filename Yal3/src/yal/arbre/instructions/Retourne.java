package yal.arbre.instructions;

import yal.arbre.expressions.Expression;
import yal.exceptions.AnalyseSemantiqueException;

public class Retourne extends Instruction{
    protected Expression e;
    protected int deplacement;
    /**
     * Constructeur
     *
     * @param n numéro de la ligne
     */
    public Retourne(int n, Expression e) {
        super(n);
        this.e = e;
    }

    @Override
    public void verifier() {
        e.verifier();
        if(!e.isArithmetique()){
            throw new AnalyseSemantiqueException(noLigne,"Une fonction doit retourner un entier.");
        }

    }

    public void setDeplacement(int deplacement) {
        this.deplacement = deplacement;
    }

    @Override
    public String toMIPS() {
        StringBuilder sb = new StringBuilder();
        // Mise à jour du pointeur de la pile
        int d = (-deplacement)+16;
        sb.append("\taddi $sp, $sp, "+d+"\n");
        // Stockage de la valeur de retour
        sb.append(e.toMIPS());
        sb.append("\tsw $v0, 16($s7)\n");
        // On revient à l'ancienne base
        sb.append("\tlw $s7, 8($s7)\n");
        // On revient à l'adresse de retour
        sb.append("\tjr $ra\n");
        return sb.toString();
    }
}
