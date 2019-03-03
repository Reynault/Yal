package yal.analyse.tds.entree;

/**
 * Classe Entrée Fonction qui permet de modéliser une entrée d'une fonction
 */
public class EntreeFonction extends Entree{
    // Nombre de paramètres
    private int nbParam;

    /**
     * Constructeur à trois paramètres
     * @param nom nom de la fonction
     * @param ligne numéro de la ligne
     * @param nbParam nombre de paramètres
     */
    public EntreeFonction(String nom, int ligne, int nbParam) {
        super(nom, ligne);
        this.nbParam = nbParam;
    }

    /**
     * Méthode qui indique le type de l'entrée
     * @return
     */
    @Override
    public String type() {
        return "Fonction";
    }

    @Override
    public boolean equals(Object o) {
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
