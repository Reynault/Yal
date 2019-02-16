package yal.analyse.tds.symbole;

public class SymboleFonction extends Symbole{
    private String typeRetour;
    private int nbParam;
    /**
     * Constructeur
     *
     * @param deplacement le déplacement dans la pile
     */
    public SymboleFonction(int deplacement) {
        super(deplacement);
    }

    @Override
    public int getDeplacement() {
        return 0;
    }
}
