package yal.arbre.expressions;

import yal.analyse.tds.TDS;
import yal.analyse.tds.entree.EntreeFonction;
import yal.analyse.tds.symbole.SymboleFonction;

import java.util.ArrayList;

/**
 * Classe qui représente un appel d'une fonction dans une expression
 */
public class AppelFonction extends Expression{
    // L'id de la fonction
    private String id;
    // Le nombre de paramètres
    private int nbParam;
    // Le numéro de la fonction
    private int numeroFonction;
    // Liste des param
    private ArrayList<Expression> param;

    /**
     * Constructeur à trois paramètres
     * @param n la ligne
     * @param id l'id
     * @param nbParam le nombre de param
     */
    public AppelFonction(int n, String id, int nbParam, ArrayList<Expression> param) {
        super(n);
        this.id = id;
        this.nbParam = nbParam;
        this.param = param;
    }

    /**
     * Méthode de vérification de la sémantique qui décore l'arbre
     */
    @Override
    public void verifier() {
        super.verifier();
        // Vérification de la fonction
        SymboleFonction sf = (SymboleFonction) TDS.getInstance().identifier(new EntreeFonction(id, noLigne, nbParam));
        numeroFonction = sf.getNumeroFonction();
        // Vérification des expressions
        for(int i = 0 ; i < nbParam; i++){
            param.get(i).verifier();
        }
    }

    /**
     * Méthode de traduction en mips
     * @return
     */
    @Override
    public String toMIPS() {
        StringBuilder sb = new StringBuilder();
        // On commence par générer la place pour les paramètres
        for(int i = 0 ; i < nbParam; i++){
            sb.append(param.get(i).toMIPS());
            sb.append("\taddi $sp, $sp, -4\n");
        }
        // Puis on génére la place de la valeur de retour
        sb.append("\taddi $sp, $sp, -4\n");
        // Puis on jump vers l'étiquette
        sb.append("\tjal FONC"+numeroFonction+"\n");
        sb.append("\tlw $v0, 0($sp)\n");
        return sb.toString();
    }

    @Override
    public boolean isArithmetique() {
        return true;
    }
}
