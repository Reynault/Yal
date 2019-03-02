package yal.analyse.tds.entree;

/**
 * Classe qui modélise une entrée dans la table des symboles
 */
public abstract class Entree {
    // Nom de l'entrée
    protected String nom;
    protected int ligne;

    /**
     * Constructeur
     * @param nom le nom de l'entrée
     * @param ligne numéro de la ligne
     */
    public Entree(String nom, int ligne) {
        this.nom = nom;
        this.ligne = ligne;
    }

    /**
     * Méthode de récupération du nom de l'entrée
     * @return le nom
     */
    public String getNom(){
        return nom;
    }

    public int getLigne() {
        return ligne;
    }

    public abstract String type();
}
