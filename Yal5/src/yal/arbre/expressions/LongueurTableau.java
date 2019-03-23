package yal.arbre.expressions;

import yal.analyse.tds.TDS;
import yal.analyse.tds.entree.EntreeTableau;
import yal.analyse.tds.symbole.Symbole;
import yal.analyse.tds.symbole.SymboleTableau;
import yal.arbre.GestionnaireNombres;

public class LongueurTableau extends Expression {
    private String idf;
    private int deplacement;
    private int nbBlock;

    /**
     * Constructeur qui prend le numéro de la ligne en paramètre
     *
     * @param n numéro de la ligne
     */
    public LongueurTableau(int n, String idf) {
        super(n);
        this.idf = idf;
    }

    @Override
    public boolean isArithmetique() {
        return true;
    }

    @Override
    public void verifier() {
        TDS instance = TDS.getInstance();
        SymboleTableau symbole = (SymboleTableau) instance.identifier(new EntreeTableau(idf, this.noLigne));
        this.deplacement = symbole.getDeplacement();
        this.nbBlock = symbole.getNumeroBlock();
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
        sb.append("\tbeq $v0, "+nbBlock+", BOUCLE"+numeroBoucle+"FIN\n");
        sb.append("\tlw $t8, 8($t8)\n");
        sb.append("\tb BOUCLE" + numeroBoucle + "\n");
        sb.append("BOUCLE" + numeroBoucle + "FIN:\n");
        sb.append("\taddi $sp, $sp, 4\n");
        sb.append("\tlw $v0, 0($sp)\n");
        return sb.toString();
    }

    @Override
    public String toMIPS() {
        StringBuilder sb = new StringBuilder();
        sb.append(placerT8());
        sb.append("   lw $v0, "+deplacement+"($t8) #recuperation de la longueur\n");
        return sb.toString();
    }
}
