package yal.analyse.tds.symbole;

/**
 * Classe qui représente un symbole de variable
 */
public class SymboleVariable extends Symbole{
    // Type de la variable : entier / tableau
    private String type;

    /**
     * Constructeur
     * @param deplacement déplacement dans la pile
     * @param type type de la variable
     */
    public SymboleVariable(int deplacement, String type) {
        super(deplacement);
        this.type = type;
    }

    /**
     * Getteur du déplacement dans la pile
     * @return la valeur de deplacement
     */
    public int getDeplacement() {
        return deplacement;
    }
}
