package yal.analyse.tds.symbole;

/**
 * Classe qui modélise un symbole de fonction
 */
public class SymboleFonction extends Symbole{
    // Numéro de la fonction
    private int numeroFonction;

    /**
     * Constructeur de la classe Symbole  de fonction
     * @param numeroFonction numéro de la fonction (Pour l'étiquette dans le fichier .mips)
     */
    public SymboleFonction(int numeroFonction) {
        this.numeroFonction = numeroFonction;
    }

    /**
     * Méthode qui permet de récupérer le déplacement
     * @return une valeur par défaut
     */
    @Override
    public int getDeplacement() {
        return 0;
    }

    /**
     * Méthode qui permet de récupérer le numéro de la fonction
     * @return numéro de la fonction
     */
    public int getNumeroFonction() {
        return numeroFonction;
    }
}
