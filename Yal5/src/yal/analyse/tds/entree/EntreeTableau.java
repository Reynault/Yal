package yal.analyse.tds.entree;

public class EntreeTableau extends Entree {
    /**
     * Constructeur
     * @param nom   le nom de l'entrée
     * @param ligne numéro de la ligne
     */
    public EntreeTableau(String nom, int ligne) {
        super(nom, ligne);
    }

    @Override
    public String type() {
        return "Tableau";
    }
}
