package yal.analyse.tds.entree;

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
        System.out.println(this.getClass());
        System.out.println(o.getClass());
        System.out.println("oui");
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EntreeFonction that = (EntreeFonction) o;
        return super.equals(o) && nbParam == that.nbParam;
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
