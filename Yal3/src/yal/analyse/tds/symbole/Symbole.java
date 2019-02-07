package yal.analyse.tds.symbole;

/**
 * Classe qui représente un symbole de la table des symboles
 */
public abstract class Symbole{
    // Déplacemenet dans la pile
    protected int deplacement;

    /**
     * Constructeur
     * @param deplacement le déplacement dans la pile
     */
    public Symbole(int deplacement){
        this.deplacement = deplacement;
    }

    /**
     * Méthode qui permet de récupérer le déplacement dans la pile du symbole
     * @return
     */
    public abstract int getDeplacement();
}
