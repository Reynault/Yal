package yal.analyse.tds;

import yal.analyse.tds.entree.Entree;
import yal.analyse.tds.symbole.Symbole;
import yal.arbre.GestionnaireNombres;
import yal.exceptions.AnalyseSemantiqueException;

import java.util.HashMap;
import java.util.Iterator;

/**
 * Classe TDS qui représente la table des symboles
 *
 * elle contient un dictionnaire qui permet de retrouver les variables / fonctions qui ont été déclarées
 * dans le code source
 *
 * Classe Singleton
 */
public class TDS extends TableDesSymboles{
    // Instance de la classe
    private static TDS instance = new TDS();
    // Position actuelle dans la pile
    private int deplacement = 0;

    private TDSLocale racine;
    private TDSLocale tableCourante;


    /**
     * Constructeur de la classe
     */
    private TDS(){
        racine = new TDSLocale(0, null);
        this.tableCourante = racine;
    }

    /**
     * Getteur de l'instance
     * @return l'instance unique
     */
    public static TDS getInstance() {
        return instance;
    }

    public TDSLocale getTableCourante() {
        return tableCourante;
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
            tableCourante.ajouter(entree, deplacement);
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
            return tableCourante.identifier(entree);
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
        return tableCourante.existe(entree);
    }

    /**
     * Méthode qui permet de récupérer la position actuelle dans la pile
     * pour ajouter un nouveau int
     * @return position du nouveau int
     */
    public int creerDeplacement(){
        int temp = deplacement;
        this.deplacement = deplacement - 4;
        return temp;
    }

    /**
     * Méthode qui permet de récupérer le sommet de la pile sans le déplacer.
     * @return la valeur actuelle du déplacement
     */
    public int getDeplacement(){
        return deplacement;
    }

    public void entreBlock(){
        GestionnaireNombres.getInstance().incrementer();
        TDSLocale tdsLocale = new TDSLocale(GestionnaireNombres.getInstance().getCompteur_blocs(), tableCourante);
        tableCourante.ajouterFils(tdsLocale);
    }

    public void sortieBlock(){
        GestionnaireNombres.getInstance().decrementer();
        tableCourante = this.tableCourante.getPere();
    }

    public void entreBlockVerif(){
        GestionnaireNombres.getInstance().incrementer();
        Iterator it = tableCourante.getLesFilles().iterator();
        boolean trouver = false;
        while (it.hasNext() && !trouver){
            TDSLocale tdsLocale = (TDSLocale) it.next();
            if (tdsLocale.getNumeroBlock() == GestionnaireNombres.getInstance().getCompteur_blocs()){
                tableCourante = tdsLocale;
                trouver = true;
            }
        }
    }

    @Override
    public void reinitialiserTable() {
        racine.reinitialiserTable();
    }

    public void sortieBlockVerif(){
        GestionnaireNombres.getInstance().decrementer();
        tableCourante = this.tableCourante.getPere();
    }


}
