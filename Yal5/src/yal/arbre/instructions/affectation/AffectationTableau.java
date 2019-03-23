package yal.arbre.instructions.affectation;

import yal.arbre.expressions.Expression;
import yal.arbre.expressions.IndiceTableau;
import yal.arbre.instructions.Retourne;
import yal.exceptions.AnalyseSemantiqueException;

import java.util.ArrayList;

/**
 * Classe qui représente une affectation de tableau
 */
public class AffectationTableau extends Affectation {
    private IndiceTableau idf;
    private Expression e;
    /**
     * Constructeur
     *
     * @param n numéro de la ligne
     */
    public AffectationTableau(int n, IndiceTableau idf, Expression e) {
        super(n);
        this.idf = idf;
        this.e = e;
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
        idf.verifier();
        e.verifier();
        if(!e.isArithmetique()){
            throw new AnalyseSemantiqueException(noLigne, "Les valeurs d'un tableau doivent être des valeurs " +
                    "arithmètiques");
        }
    }

    @Override
    public String toMIPS() {
        StringBuilder sb = new StringBuilder();
        // Récupération de la valeur de l'expression dans $v0
        sb.append(e.toMIPS());
        sb.append("\tsw $v0, 0($sp)\n");
        sb.append("\taddi $sp, $sp, -4\n");
        // Récupération de la position de l'indice dans $t9
        sb.append(idf.toMIPS());
        sb.append("\taddi $sp, $sp, 4\n");
        sb.append("\tlw $v0, 0($sp)\n");
        // Utilisation de $t9 pour stocker la variable
        sb.append("\tsw $v0, 0($t9)\n");
        return sb.toString();
    }
}
