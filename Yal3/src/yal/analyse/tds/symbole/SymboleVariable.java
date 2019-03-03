package yal.analyse.tds.symbole;

/**
 * Classe qui représente un symbole de variable
 */
public class SymboleVariable extends Symbole{
    // Déplacemenet dans la pile
    protected int deplacement;
    // Type de la variable : entier / tableau
    private String type;
    // Numéro de bloc de la variable
    private int idBlock;

    /**
     * Constructeur
     * @param deplacement déplacement dans la pile
     * @param type type de la variable
     */
    public SymboleVariable(int deplacement, String type, int block) {
        this.deplacement = deplacement;
        this.type = type;
        this.idBlock = block;
    }

    public int getNumeroBlock() {
        return idBlock;
    }

    /**
     * Getteur du déplacement dans la pile
     * @return la valeur de deplacement
     */
    public int getDeplacement() {
        return deplacement;
    }

    @Override
    public String toString() {
        return "SymboleVariable{" +
                "deplacement=" + deplacement +
                ", numeroBlock=" + idBlock +
                '}';
    }
}
