package yal.arbre.expressions;

import yal.analyse.tds.entree.EntreeVariable;
import yal.analyse.tds.symbole.Symbole;
import yal.analyse.tds.TDS;
import yal.analyse.tds.symbole.SymboleVariable;
import yal.arbre.GestionnaireNombres;
import yal.exceptions.AnalyseSemantiqueException;

/**
 * Classe Variable qui représente une variable dans une expression
 */
public class Variable extends Expression{

    // Identificateur de la variable
    protected String idf;

    protected int blockCourant;
    protected int blockVariable;

    // Déplacement de la variable mis à jour lors de la vérification de l'arbre
    protected int deplacement;

    /**
     * Constructeur de la classe variable qui prend deux paramètres
     * @param n le numéro de la ligne
     * @param idf l'identificateur de la variable
     */
    public Variable(int n, String idf) {
        super(n);
        this.idf = idf;
    }

    public int getDeplacement() {
        return deplacement;
    }

    /**
     * Méthode qui permet de placer la base au bon endroit pour pouvoir modifier
     * facilement la variable (juste avec son deplacement)
     *
     * Cette méthode est utilisée lorsqu'on veut modifier une variable,
     * comme on ne connaît que la base s7, et le déplacement de la variable par rapport
     * à la base où elle a été déclarée, on veut retrouver cette base
     * temporairement pour pouvoir accéder à la variable.
     *
     * @return la liste des instructions
     */
    @Override
    public String placerT8(){
        StringBuilder sb = new StringBuilder();
        sb.append("\t# Deplacement de t8 vers le s7 de la variable\n");
        int numeroBoucle = GestionnaireNombres.getInstance().nouvelleIteration();
        sb.append("\tmove $t8, $s7\n");
        sb.append("\tsw $v0, 0($sp)\n");
        sb.append("\taddi $sp, $sp, -4\n");
        sb.append("BOUCLE" + numeroBoucle + ":\n");
        sb.append("\tlw $v0, 4($t8)\n");
        sb.append("\tbeq $v0, "+blockVariable+", BOUCLE"+numeroBoucle+"FIN\n");
        sb.append("\tlw $t8, 8($t8)\n");
        sb.append("\tb BOUCLE" + numeroBoucle + "\n");
        sb.append("BOUCLE" + numeroBoucle + "FIN:\n");
        sb.append("\taddi $sp, $sp, 4\n");
        sb.append("\tlw $v0, 0($sp)\n");
        return sb.toString();
    }

    /**
     * Méthode vérifier : vérification de la sémantique
     */
    @Override
    public void verifier() {
        // Récupération de l'instance de tds
        TDS instance = TDS.getInstance();
        // On récupère le deplacement de l'identificateur
        SymboleVariable sv = (SymboleVariable) instance.identifier(new EntreeVariable(idf, noLigne));
        deplacement = sv.getDeplacement();
        blockCourant = instance.getTableCourante().getNumeroBlock();
        blockVariable = sv.getNumeroBlock();
    }

    /**
     * Méthode de traduction en Mips
     * @return le code mips correspondant
     */
    @Override
    public String toMIPS() {
        // Construction du code dans un string builder
        StringBuilder sb = new StringBuilder();
        int numeroBoucle = GestionnaireNombres.getInstance().nouvelleIteration();
        sb.append("\tmove $t8, $s7\n");
        sb.append("BOUCLE" + numeroBoucle + ":\n");
        sb.append("\tlw $v0, 4($t8)\n");
        sb.append("\tbeq $v0, "+blockVariable+",BOUCLE"+numeroBoucle+"FIN\n");
        sb.append("\tlw $t8, 8($t8)\n");
        sb.append("\tb BOUCLE" + numeroBoucle + "\n");
        sb.append("BOUCLE" + numeroBoucle + "FIN:\n");
        sb.append("\t# Chargement de la valeur de la variable dans v0\n");
        sb.append("\tlw $v0, "+deplacement+"($t8)\n");
        return sb.toString();
    }

    @Override
    public boolean isArithmetique() {
        return true;
    }
}
