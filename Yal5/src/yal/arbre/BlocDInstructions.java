package yal.arbre;

import yal.arbre.instructions.Retourne;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * 21 novembre 2018
 *
 * @author brigitte wrobel-dautcourt
 */

public class BlocDInstructions extends ArbreAbstrait {
    // Liste des instructions du bloc
    protected ArrayList<ArbreAbstrait> programme ;

    /**
     * Constructeur
     * @param n numéro de la ligne
     */
    public BlocDInstructions(int n) {
        super(n) ;
        programme = new ArrayList<>() ;
    }

    @Override
    public ArrayList<Retourne> get_retourne() {
        ArrayList<Retourne> liste = new ArrayList<Retourne>();
        ArrayList<Retourne> res;
        for (ArbreAbstrait a : programme){
            res = a.get_retourne();
            if(res != null){
                liste.addAll(res);
            }
        }
        return liste;
    }

    @Override
    public int get_nb_retourne() {
        int res = 0;
        for (ArbreAbstrait a : programme){
            res += a.get_nb_retourne();
        }
        return res;
    }

    /**
     * Méthode qui permet d'ajouter un arbre abstrait
     * @param a le nouvel arbre
     */
    public void ajouter(ArbreAbstrait a) {
        programme.add(a) ;
    }

    /**
     * Méthode toString
     * @return
     */
    @Override
    public String toString() {
        return programme.toString() ;
    }

    /**
     * Méthode vérifier qui vérifie la sémantique
     */
    @Override
    public void verifier() {
        for (ArbreAbstrait a : programme) {
            a.verifier() ;
        }
    }

    /**
     * Méthode qui permet de traduire en mips
     * @return
     */
    @Override
    public String toMIPS() {
        StringBuilder sb = new StringBuilder("") ;
        for (ArbreAbstrait a : programme) {
            sb.append(a.toMIPS()) ;
        }
        return sb.toString() ;
    }

}
