package yal.arbre.expressions;

import yal.analyse.tds.TDS;
import yal.analyse.tds.entree.EntreeFonction;
import yal.analyse.tds.symbole.SymboleFonction;

public class AppelFonction extends Expression{

    private String id;
    private int nbParam;
    private int numeroFonction;

    public AppelFonction(int n, String id, int nbParam) {
        super(n);
        this.id = id;
        this.nbParam = nbParam;
    }

    @Override
    public void verifier() {
        super.verifier();
        SymboleFonction sf = (SymboleFonction) TDS.getInstance().identifier(new EntreeFonction(id, noLigne, nbParam));
        numeroFonction = sf.getNumeroFonction();
    }

    @Override
    public String toMIPS() {
        StringBuilder sb = new StringBuilder();
        // On commence par générer la place pour la valeur du retour
        sb.append("\raddi $sp, $sp, -4\n");
        // Puis on jump vers l'étiquette
        sb.append("\rjal FONC"+numeroFonction+"\n");
        return sb.toString();
    }

    @Override
    public boolean isArithmetique() {
        return true;
    }
}
