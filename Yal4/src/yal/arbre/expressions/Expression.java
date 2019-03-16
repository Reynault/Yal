package yal.arbre.expressions;

import yal.arbre.ArbreAbstrait;
import yal.arbre.instructions.Retourne;

import java.util.ArrayList;

/**
 * Classe qui représente une expression
 *
 * Elle hérite de la classe ArbreAbstrait
 */
public class Expression extends ArbreAbstrait {
    /**
     * Constructeur qui prend le numéro de la ligne en paramètre
     * @param n numéro de la ligne
     */
    protected Expression(int n) {
        super(n) ;
    }

    @Override
    public int get_nb_retourne() {
        return 0;
    }

    @Override
    public ArrayList<Retourne> get_retourne() {
        return null;
    }

    @Override
    public void verifier() {
    }

    @Override
    public String toMIPS() {
        return null;
    }

    /**
     * Méthode isArithmetique qui permet d'identifier les expressions qui sont
     * des expressions arithmétiques.
     *
     * Méthode utilisée dans la vérification de l'arbre abstrait
     * @return
     */
    public boolean isArithmetique(){
        return false;
    }

    /**
     * Méthode qui indique si l'expression est une
     * constante égale à zero
     * @return boolean
     */
    public boolean isZero(){ return false; }
}
