package yal.arbre.instructions;

import yal.arbre.expressions.Expression;
import yal.exceptions.AnalyseSemantiqueException;

/**
 * Classe qui représente l'instruction de retour d'une fonction
 */
public class Retourne extends Instruction{
    // Expression à retourner
    protected Expression e;
    // Deplacement de la fonction (celle des variables dans la fonction pour connaître le deplacement
    // de sp à effectuer pour revenir à l'ancienne position)
    protected int deplacement;
    protected int nbparam;
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

    public void setNbParam(int nbParam){
        this.nbparam = nbParam;
    }

    public void setDeplacement(int deplacement) {
        this.deplacement = deplacement;
    }

    @Override
    public String toMIPS() {
        StringBuilder sb = new StringBuilder();
        // Stockage de la valeur de retour
        sb.append(e.toMIPS());
        // Mise à jour du pointeur de la pile
        int d = (-deplacement)+16+(nbparam*4);
        sb.append("\taddi $sp, $sp, "+d+"\n");
        sb.append("\tsw $v0, 16($s7)\n");
        // On revient à l'ancienne base
        sb.append("\tlw $ra, 12($s7)\n");
        sb.append("\tlw $s7, 8($s7)\n");
        // On revient à l'adresse de retour
        sb.append("\tjr $ra\n");
        return sb.toString();
    }
}
