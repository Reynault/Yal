package yal.arbre.expressions;

import yal.analyse.tds.entree.EntreeVariable;
import yal.analyse.tds.symbole.Symbole;
import yal.analyse.tds.TDS;
import yal.analyse.tds.symbole.SymboleVariable;
import yal.exceptions.AnalyseSemantiqueException;

/**
 * Classe Variable qui représente une variable dans une expression
 */
public class Variable extends Expression{

    // Identificateur de la variable
    protected String idf;

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
     * Méthode vérifier : vérification de la sémantique
     */
    @Override
    public void verifier() {
        // Récupération de l'instance de tds
        TDS instance = TDS.getInstance();
        // On récupère le deplacement de l'identificateur
        Symbole sv = instance.identifier(new EntreeVariable(idf, noLigne));
        deplacement = sv.getDeplacement();
    }

    /**
     * Méthode de traduction en Mips
     * @return le code mips correspondant
     */
    @Override
    public String toMIPS() {
        // Construction du code dans un string builder
        StringBuilder sb = new StringBuilder();
        sb.append("\t# Chargement de la valeur de la variable dans v0\n");
        sb.append("\tlw $v0, "+deplacement+"($s7)\n");
        return sb.toString();
    }

    @Override
    public boolean isArithmetique() {
        return true;
    }
}
