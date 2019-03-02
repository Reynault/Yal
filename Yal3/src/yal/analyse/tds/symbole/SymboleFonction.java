package yal.analyse.tds.symbole;

public class SymboleFonction extends Symbole{
    private int numeroFonction;

    public SymboleFonction(int numeroFonction) {
        this.numeroFonction = numeroFonction;
    }

    @Override
    public int getDeplacement() {
        return 0;
    }

    public int getNumeroFonction() {
        return numeroFonction;
    }
}
