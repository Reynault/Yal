package yal.arbre;

import yal.arbre.instructions.Retourne;

import java.util.ArrayList;

public class Tableau extends ArbreAbstrait {
    /**
     * Constructeur
     *
     * @param n numéro de la ligne
     */
    protected Tableau(int n) {
        super(n);
    }

    @Override
    public ArrayList<Retourne> get_retourne() {
        return null;
    }

    @Override
    public int get_nb_retourne() {
        return 0;
    }

    @Override
    public void verifier() {

    }

    @Override
    public String toMIPS() {
        return null;
    }
}
