package yal.arbre.instructions;

import yal.arbre.expressions.Variable;

import java.util.ArrayList;

/**
 * Classe qui représente une lecture en entrée
 */
public class Lire extends Instruction{
    // Variable dans laquelle est stockée la valeur
    protected Variable idf;

    /**
     * Constructeur à deux paramètres
     * @param idf Variable dans laquelle on stocke la valeur
     * @param n numéro de la ligne
     */
    public Lire(Variable idf, int n) {
        super(n);
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

    /**
     * Méthode qui permet de vérifier la sémantique
     */
    @Override
    public void verifier() {
        idf.verifier();
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
        stringBuilder.append(idf.placerT8());
        stringBuilder.append("\tsw $v0, "+idf.getDeplacement()+"($t8)\n");
        return stringBuilder.toString();
    }

}
