package yal.analyse.tds.entree;

import java.util.Objects;

/**
 * Classe qui modélise une entrée dans la table des symboles
 */
public abstract class Entree {
    // Nom de l'entrée
    protected String nom;

    /**
     * Constructeur
     * @param nom le nom de l'entrée
     */
    public Entree(String nom) {
        this.nom = nom;
    }

    /**
     * Méthode equals
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entree entree = (Entree) o;
        return Objects.equals(nom, entree.nom);
    }

    /**
     * Méthode de hashcode
     * @return
     */
    @Override
    public int hashCode() {
        return Objects.hash(nom);
    }

    /**
     * Méthode de récupération du nom de l'entrée
     * @return le nom
     */
    public abstract String getNom();
}
