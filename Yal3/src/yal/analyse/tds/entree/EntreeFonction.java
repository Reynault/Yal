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

    @Override
    public boolean equals(Object o) {
        System.out.println("oui");
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        EntreeFonction that = (EntreeFonction) o;
        return nbParam == that.nbParam &&
                Objects.equals(nom, that.nom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), nom, nbParam);
    }

    @Override
    public String toString() {
        return "EntreeFonction{" +
                "nbParam=" + nbParam +
                ", nom='" + nom + '\'' +
                ", ligne=" + ligne +
                '}';
    }
}
