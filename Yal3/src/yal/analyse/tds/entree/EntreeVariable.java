package yal.analyse.tds.entree;

import java.util.Objects;

/**
 * Classe qui représente une entrée de variable dans la table
 */
public class EntreeVariable extends Entree{
    /**
     * Constructeur
     * @param nom nom de l'entrée
     */
    public EntreeVariable(String nom, int ligne) {
        super(nom, ligne);
    }

    @Override
    public String type() {
        return "Variable";
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
        EntreeVariable entree = (EntreeVariable) o;
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
}
