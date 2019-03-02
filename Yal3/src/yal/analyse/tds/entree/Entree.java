package yal.analyse.tds.entree;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {

        System.out.println("this: " + this.getClass());
        System.out.println("obj: " + o.getClass());
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entree entree = (Entree) o;
        return Objects.equals(nom, entree.nom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nom);
    }

    @Override
    public String toString() {
        return "Entree{" +
                "nom='" + nom + '\'' +
                ", ligne=" + ligne +
                '}';
    }
}
