package yal.analyse.tds.entree;

/**
 * Classe qui représente une entrée de variable dans la table
 */
public class EntreeVariable extends Entree{
    /**
     * Constructeur
     * @param nom nom de l'entrée
     */
    public EntreeVariable(String nom) {
        super(nom);
    }

    /**
     * Méthode getteur de nom
     * @return valeur de nom
     */
    public String getNom(){
        return this.nom;
    }
}
