package yal.arbre;

import yal.analyse.tds.TDS;
import yal.analyse.tds.entree.EntreeTableau;
import yal.analyse.tds.symbole.Symbole;
import yal.arbre.expressions.Expression;
import yal.arbre.instructions.Retourne;
import yal.exceptions.AnalyseSemantiqueException;

import java.util.ArrayList;

public class Tableau extends ArbreAbstrait {
    private String idf;
    private Expression expression;
    private int deplacement;

    /**
     * Constructeur
     *
     * @param n numéro de la ligne
     */
    public Tableau(int n, Expression expression, String idf) {
        super(n);
        this.expression = expression;
        this.idf = idf;
    }

    @Override
    public ArrayList<Retourne> get_retourne() {
        return null;
    }

    @Override
    public int get_nb_retourne() {
        return 0;
    }

    @Override
    public void verifier() {
        TDS instance = TDS.getInstance();
        Symbole symbole = instance.identifier(new EntreeTableau(idf, this.noLigne));
        deplacement = symbole.getDeplacement();
        expression.verifier();
        if (!expression.isArithmetique()){
            throw new AnalyseSemantiqueException(this.noLigne, "Un tableau doit avoir une expression arithmetique pour indiquer sa taille");
        }
    }

    @Override
    public String toMIPS() {
        StringBuilder sb = new StringBuilder();
        sb.append(expression.toMIPS());
        int numero = GestionnaireNombres.getInstance().nouvelleErreur();
        // Suite à l'évaluation de l'expression
        // Vérification de la valeur pour éviter d'avoir
        // des valeurs négatives
        sb.append("\tbgtz $v0, INDICE"+numero+"\n");
        sb.append("\tli $v0, 4\n");
        sb.append("\tla $a0, indiceNegatif\n");
        sb.append("\tsyscall\n");
        sb.append("\tli $v0, 10\n");
        sb.append("\tsyscall\n");
        sb.append("\tINDICE"+numero+":\n");
        sb.append("   sw $v0, "+deplacement+"($s7)\n");
        sb.append("   sw $sp, "+(deplacement-4)+"($s7)\n");
        sb.append("   mul $v0, $v0, -4\n");
        sb.append("   add $sp, $sp, $v0 #ajout de la taille du tableau a la pile\n");
        return sb.toString();
    }
}
