package yal.arbre.expressions.expressionBinaire.expressionArithmetique;

import yal.arbre.GestionnaireNombres;
import yal.arbre.Programme;
import yal.arbre.expressions.Expression;
import yal.exceptions.AnalyseSemantiqueException;

public class Division extends ExpressionArithmetiqueBinaire {
    /**
     * Constructeur
     *
     * @param n      numéro de la ligne
     * @param gauche expression de gauche
     * @param droite expression de droite
     */
    public Division(int n, Expression gauche, Expression droite) {
        super(n, gauche, droite);
    }

    @Override
    public void verifier() {
        super.verifier();
        if(droite.isZero()){
            throw new AnalyseSemantiqueException(noLigne,"Division par un zéro");
        }
    }

    @Override
    public String toMIPS() {
        String res = super.toMIPS();
        StringBuilder sb = new StringBuilder();
        sb.append(res);
        int numero = GestionnaireNombres.getInstance().nouvelleErreur();
        // Ajout de la vérification en mips pour tester si
        // $v0 est égal à 0
        sb.append("\tbnez $v0, DIVISION"+ numero +"\n");
        sb.append("\tli $v0, 4\n");
        sb.append("\tla $a0, divisionZero\n");
        sb.append("\tsyscall\n");
        sb.append("\tli $v0, 10\n");
        sb.append("\tsyscall\n");
        sb.append("\tDIVISION"+ numero +":\n");
        sb.append("\tdiv $v0, $t8, $v0\n");
        return sb.toString();
    }
}
