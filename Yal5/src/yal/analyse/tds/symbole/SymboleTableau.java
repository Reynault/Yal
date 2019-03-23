package yal.analyse.tds.symbole;

public class SymboleTableau extends Symbole {
    // Déplacemenet dans la pile
    protected int deplacement;
    // Type de la variable : entier / tableau
    private String type;
    // Numéro de bloc de la variable
    private int idBlock;
    // Profondeur du bloc
    private int numBlock;

    public SymboleTableau(int deplacement, String type, int block) {
        this.deplacement = deplacement;
        this.type = type;
        this.numBlock = block;
    }

    @Override
    public int getDeplacement() {
        return deplacement;
    }

    @Override
    public String toString() {
        return "SymboleTableau{" +
                "deplacement=" + deplacement +
                ", numBlock=" + numBlock +
                '}';
    }
}
