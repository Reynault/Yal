package yal.analyse.tds;

import yal.analyse.tds.entree.Entree;
import yal.analyse.tds.symbole.Symbole;
import yal.exceptions.AnalyseSemantiqueException;

import java.util.HashMap;

/**
 * Classe TDS qui représente la table des symboles
 *
 * elle contient un dictionnaire qui permet de retrouver les variables / fonctions qui ont été déclarées
 * dans le code source
 *
 * Classe Singleton
 */
public class TDS {
    // Instance de la classe
    private static TDS instance = new TDS();
    // Position actuelle dans la pile
    private int deplacement = 0;
    // Dictionnaire qui permet de stocker et de retrouver les symboles via des entrées
    private HashMap<Entree, Symbole> table;

    /**
     * Constructeur de la classe
     */
    private TDS(){
        table = new HashMap<Entree,Symbole>();
    }

    /**
     * Getteur de l'instance
     * @return l'instance unique
     */
    public static TDS getInstance() {
        return instance;
    }

    /**
     * Méthode ajouter qui permet d'ajouter une entrée dans la table
     * des symboles
     * @param entree nouvelle entrée
     * @param deplacement position dans la pile
     */
    public void ajouter(Entree entree, Symbole deplacement){
        // Vérification
        if(existe(entree)){
            throw new AnalyseSemantiqueException(entree.getLigne(),"Variable déjà déclarée");
        }else {
            table.put(entree, deplacement);
        }
    }

    /**
     * Méthode qui permet d'identifier une entrée dans la table est de récupérer
     * le symbole correspondant
     * @param entree entrée recherchée
     * @return Symbole de l'entrée
     */
    public Symbole identifier(Entree entree){
        if(existe(entree)){
            return table.get(entree);
        }else{
            throw new AnalyseSemantiqueException(entree.getLigne(), "Variable non déclarée");
        }
    }

    /***
     * Méthode qui permet de vérifier qu'une entrée existe dans la table
     * @param entree entrée à vérifier
     * @return booléen qui indique si l'entrée existe
     */
    public boolean existe(Entree entree){
        return table.containsKey(entree);
    }

    /**
     * Méthode qui permet de récupérer la position actuelle dans la pile
     * pour ajouter un nouveau int
     * @return position du nouveau int
     */
    public int creerDeplacement(){
        int temp = deplacement;
        deplacement = deplacement - 4;
        return temp;
    }

    /**
     * Méthode qui permet de réinitialiser la table des symboles
     */
    public void reinitialiserTable(){
        deplacement = 0;
        table = new HashMap<Entree,Symbole>();
    }

    /**
     * Méthode qui permet de récupérer le sommet de la pile sans le déplacer.
     * @return la valeur actuelle du déplacement
     */
    public int getDeplacement(){
        return deplacement;
    }
}
