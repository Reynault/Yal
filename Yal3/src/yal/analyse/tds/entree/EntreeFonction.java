package yal.analyse.tds.entree;

public class EntreeFonction extends Entree{
    private int nbParam;

    public EntreeFonction(String nom, int numBloc, int ligne, int nbParam) {
        super(nom, numBloc, ligne);
        this.nbParam = nbParam;
    }
}
