package yal.arbre.expressions;

import yal.analyse.tds.TDS;
import yal.analyse.tds.entree.EntreeTableau;
import yal.analyse.tds.symbole.SymboleTableau;
import yal.arbre.GestionnaireNombres;
import yal.exceptions.AnalyseSemantiqueException;

public class IndiceTableau extends Expression {
    private String idf;
    private Expression exp;
    private int numBlock;
    private int deplacement;
    /**
     * Constructeur qui prend le numéro de la ligne en paramètre
     *
     * @param n numéro de la ligne
     */
    public IndiceTableau(int n, String idf, Expression expr) {
        super(n);
        this.idf = idf;
        this.exp =expr;
    }

    @Override
    public void verifier() {
        super.verifier();
        TDS instance = TDS.getInstance();
        SymboleTableau symb = (SymboleTableau) instance.identifier(new EntreeTableau(idf, noLigne));
        deplacement = symb.getDeplacement();
        numBlock = symb.getNumeroBlock();
        exp.verifier();
        if(!exp.isArithmetique()){
            throw new AnalyseSemantiqueException(noLigne, "Un indice de tableau doit être le résultat d'" +
                    "une expression arithmétique");
        }
    }

    @Override
    public String placerT8() {
        StringBuilder sb = new StringBuilder();
        sb.append("\t# Deplacement de t8 vers le s7 de la variable\n");
        int numeroBoucle = GestionnaireNombres.getInstance().nouvelleIteration();
        sb.append("\tmove $t8, $s7\n");
        sb.append("\tsw $v0, 0($sp)\n");
        sb.append("\taddi $sp, $sp, -4\n");
        sb.append("BOUCLE" + numeroBoucle + ":\n");
        sb.append("\tlw $v0, 4($t8)\n");
        sb.append("\tbeq $v0, "+numBlock+", BOUCLE"+numeroBoucle+"FIN\n");
        sb.append("\tlw $t8, 8($t8)\n");
        sb.append("\tb BOUCLE" + numeroBoucle + "\n");
        sb.append("BOUCLE" + numeroBoucle + "FIN:\n");
        sb.append("\taddi $sp, $sp, 4\n");
        sb.append("\tlw $v0, 0($sp)\n");
        return sb.toString();
    }

    public int getDeplacement() {
        return deplacement;
    }

    @Override
    public boolean isArithmetique() {
        return true;
    }

    @Override
    public String toMIPS() {
        StringBuilder sb = new StringBuilder();
        // Récupération du résultat de l'expression dans $v0
        sb.append(exp.toMIPS());
        // On commence par placer la base locale à partir de laquelle se trouve le tableau
        sb.append(placerT8());
        // Ensuite, on récupère l'indice de la case voulue avec le déplacement du tableau
        sb.append("\tlw $t9, "+(deplacement-4)+"($s7)\n");
        sb.append("\tmul $v0, $v0, -4\n");
        sb.append("\tadd $t9, $t9, $v0\n");
        // Puis stockage de la valeur de l'indice dans $v0
        sb.append("\tlw $v0, 0($t9)\n");
        return sb.toString();
    }
}
