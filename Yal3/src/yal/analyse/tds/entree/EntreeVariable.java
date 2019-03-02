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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entree entree = (Entree) o;
        System.out.println("b");
        return Objects.equals(nom, entree.nom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nom);
    }

    @Override
    public String toString() {
        return "EntreeVariable{" +
                "nom='" + nom + '\'' +
                ", ligne=" + ligne +
                '}';
    }
}
