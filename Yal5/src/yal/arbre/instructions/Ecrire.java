package yal.arbre.instructions;

import yal.arbre.GestionnaireNombres;
import yal.arbre.expressions.Expression;

import java.util.ArrayList;

/**
 * Classe qui représente une instruction d'écriture
 */
public class Ecrire extends Instruction {
    // Expression à écrire
    protected Expression exp ;

    protected boolean arithmetique;

    /**
     * Constructeur
     * @param e expression à écrire
     * @param n numéro de la ligne
     */
    public Ecrire (Expression e, int n) {
        super(n) ;
        exp = e ;
    }

    @Override
    public ArrayList<Retourne> get_retourne() {
        return null;
    }

    @Override
    public int get_nb_retourne() {
        return 0;
    }

    /**
     * Méthode de vérification de la sémantique
     */
    @Override
    public void verifier() {
        exp.verifier();
        arithmetique = exp.isArithmetique();
    }

    /**
     * Méthode de traduction en mips
     * @return code en mips
     */
    @Override
    public String toMIPS() {
        GestionnaireNombres g = GestionnaireNombres.getInstance();
        int nombre = g.nouvelleEcriture();
        StringBuilder sb = new StringBuilder();
        sb.append("\t# affichage de l'expression\n");
        sb.append(exp.toMIPS());
        if(arithmetique){
            sb.append("\tmove $a0, $v0\n");
            sb.append("\tli $v0, 1\n");
            sb.append("\tsyscall\n");
        }else{
            sb.append("\tbeqz $v0, ECRITURE"+nombre+"\n");
            sb.append("\t\tli $v0, 4\n");
            sb.append("\t\tla $a0, booleenVrai\n");
            sb.append("\t\tsyscall\n");
            sb.append("\t\tb FINECRITURE"+nombre+"\n");
            sb.append("\tECRITURE"+nombre+":\n");
            sb.append("\t\tli $v0, 4\n");
            sb.append("\t\tla $a0, booleenFaux\n");
            sb.append("\t\tsyscall\n");
            sb.append("\tFINECRITURE"+nombre+":\n");
        }
        sb.append("\tli $v0, 4      # retour à la ligne\n");
        sb.append("\tla $a0, finLigne\n");
        sb.append("\tsyscall\n");
        sb.append("\tmove $v0, $a0\n");
        return sb.toString();
    }

}
