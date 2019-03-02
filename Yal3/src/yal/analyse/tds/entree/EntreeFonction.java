package yal.analyse.tds.entree;

import java.util.Objects;

public class EntreeFonction extends Entree{
    private int nbParam;

    public EntreeFonction(String nom, int ligne, int nbParam) {
        super(nom, ligne);
        this.nbParam = nbParam;
    }

    @Override
    public String type() {
        return "Fonction";
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
        EntreeFonction entree = (EntreeFonction) o;
        return Objects.equals(nom, entree.nom) && Objects.equals(nbParam, entree.nbParam);
    }

    /**
     * Méthode de hashcode
     * @return
     */
    @Override
    public int hashCode() {
        return Objects.hash(nom+nbParam);
    }
}
