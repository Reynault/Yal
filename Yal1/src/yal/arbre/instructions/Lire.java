package yal.arbre.instructions;

import yal.analyse.Symbole;
import yal.analyse.TDS;
import yal.arbre.expressions.Expression;
import yal.arbre.instructions.affectation.Affectation;

/**
 * Classe qui représente une lecture en entrée
 */
public class Lire extends Instruction{
    // Variable dans laquelle est stockée la valeur
    protected String idf;

    /**
     * Constructeur à deux paramètres
     * @param idf identificateur de la variable dans laquelle on stocke la valeur
     * @param n numéro de la ligne
     */
    public Lire(String idf, int n) {
        super(n);
        this.idf = idf;
    }

    /**
     * Méthode qui permet de vérifier la sémantique
     */
    @Override
    public void verifier() {
        // Vérification de l'existence de la variable
    }

    /**
     * Méthode de traduction en mips
     * @return code en mips
     */
    @Override
    public String toMIPS() {
        // Création du code
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\t# Lire un entier\n");
        stringBuilder.append("\tli $v0 , 5 \t# $v0 <- 5 (code du read entier)\n");
        stringBuilder.append("\tsyscall \t# le résultat de la lecture est dans $V0 \n");
        Symbole s = TDS.getInstance().identifier(idf);
        stringBuilder.append("\tsw $v0, "+s.getDeplacement()+"($s7)");
        return stringBuilder.toString();
    }
}
