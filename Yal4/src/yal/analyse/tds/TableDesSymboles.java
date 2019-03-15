package yal.analyse.tds;

import yal.analyse.tds.entree.Entree;
import yal.analyse.tds.symbole.Symbole;

/**
 * Classe abstraite qui modélise une table des symboles
 */
abstract class TableDesSymboles {
    /**
     * Méthode ajouter qui permet d'ajouter une entrée et un symbole
     * @param e l'entrée
     * @param S le symbole
     */
    public abstract void ajouter(Entree e, Symbole S);

    /**
     * Méthode qui permet d'identifier une entrée
     * @param e l'entrée à identifier
     * @return le symbole qui lui correspond
     */
    public abstract Symbole identifier(Entree e);

    /**
     * Méthode qui permet de vérifier si une entrée existe
     * @param e l'entrée à vérifier
     * @return un booléen qui indique si oui ou non l'entrée existe
     */
    public abstract boolean existe(Entree e);

    /**
     * Méthode qui permet de réinitialiser la table
     */
    public abstract void reinitialiserTable();

    /**
     * Méthode qui permet de créer un déplacement
     * @return le nouveau deplacement
     */
    public abstract int creerDeplacement();

    /**
     * Méthode qui permet d'avoir le deplacement actuel
     * @return
     */
    public abstract int getDeplacement();

    public abstract boolean existeTableLocale(Entree e);
}
