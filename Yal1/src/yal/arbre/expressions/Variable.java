package yal.arbre.expressions;

import yal.analyse.Symbole;
import yal.analyse.TDS;
import yal.exceptions.AnalyseSemantiqueException;

/**
 * Classe Variable qui représente une variable dans une expression
 */
public class Variable extends Expression{
    // Identificateur de la variable
    protected String idf;

    /**
     * Constructeur de la classe variable qui prend deux paramètres
     * @param n le numéro de la ligne
     * @param idf l'identificateur de la variable
     */
    public Variable(int n, String idf) {
        super(n);
        this.idf = idf;
    }

    /**
     * Méthode vérifier : vérification de la sémantique
     */
    @Override
    public void verifier() {
        // On vérifie que la variable se trouve bien dans la table des symboles
        TDS instance = TDS.getInstance();
        // Si ce n'est pas le cas, on lance une exception
        if(!instance.existe(idf)){
            System.out.println("pipop");
            throw new AnalyseSemantiqueException(noLigne,"Identificateur non déclaré");
        }
    }

    /**
     * Méthode de traduction en Mips
     * @return le code mips correspondant
     */
    @Override
    public String toMIPS() {
        // Récupération du symbole de la variable
        Symbole symbole = TDS.getInstance().identifier(idf);
        // Construction du code dans un string builder
        StringBuilder sb = new StringBuilder();
        sb.append("\t# Chargement de la valeur de la variable dans v0\n");
        sb.append("\tlw $v0, "+symbole.getDeplacement()+"($s7)\n");
        return sb.toString();
    }
}
