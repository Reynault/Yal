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
    public String toString() {
        return "EntreeVariable{" +
                "nom='" + nom + '\'' +
                ", ligne=" + ligne +
                '}';
    }
}
