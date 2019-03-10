package yal.analyse.tds;

import yal.analyse.tds.entree.Entree;
import yal.analyse.tds.symbole.Symbole;
import yal.arbre.GestionnaireNombres;
import yal.exceptions.AnalyseSemantiqueException;

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

    private TDSLocale racine;
    private TDSLocale tableCourante;


    /**
     * Constructeur de la classe
     */
    private TDS(){
        racine = new TDSLocale(0, null, GestionnaireNombres.getInstance().getIdBlock());
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
            throw new AnalyseSemantiqueException(entree.getLigne(), entree.type()+" déjà déclarée");
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
            throw new AnalyseSemantiqueException(entree.getLigne(), entree.type()+" non déclarée " + entree.getNom());
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
        return tableCourante.creerDeplacement();
    }

    /**
     * Méthode qui permet de récupérer le sommet de la pile sans le déplacer.
     * @return la valeur actuelle du déplacement
     */
    public int getDeplacement(){
        return tableCourante.getDeplacement();
    }

    /**
     * Méthode qui indique qu'on entre dans un block
     *
     * Elle met à jour la table courante.
     *
     * Créer une nouvelle table
     * Ajoute le nouveau fils à la table courante
     * Ajoute la table courante en tant que père de la nouvelle table
     * et remplace la table courante par la nouvelle table
     */
    public void entreBlock(){
        GestionnaireNombres.getInstance().incrementer();
        GestionnaireNombres.getInstance().incrementerIdBlock();
        TDSLocale tdsLocale = new TDSLocale(GestionnaireNombres.getInstance().getCompteur_blocs(), tableCourante,
                GestionnaireNombres.getInstance().getIdBlock());
        tableCourante.ajouterFils(tdsLocale);
        tableCourante = tdsLocale;
    }

    /**
     * Méthode qui permet de sortir d'un block
     *
     * Elle met à jour la table courante (En prenant le père)
     */
    public void sortieBlock(){
        GestionnaireNombres.getInstance().decrementer();
        tableCourante = this.tableCourante.getPere();
    }

    /**
     * Méthode qui permet d'entrée dans un block, dans la vérification de l'arbre
     */
    public void entreBlockVerif(){
        GestionnaireNombres.getInstance().incrementer();
        GestionnaireNombres.getInstance().incrementerIdBlock();
        Iterator it = tableCourante.getLesFilles().iterator();
        boolean trouver = false;
        // On cherche le prochain block, et on entre dedans en mettant à jour la table courante
        while (it.hasNext() && !trouver){
            TDSLocale tdsLocale = (TDSLocale) it.next();
            if (tdsLocale.getIdBlock() == GestionnaireNombres.getInstance().getIdBlock()){
                tableCourante = tdsLocale;
                trouver = true;
            }
        }
    }

    /**
     * Méthode de réinitialisation de la table
     */
    @Override
    public void reinitialiserTable() {
        racine.reinitialiserTable();
    }

    /**
     * Méthode qui permet de sortir d'une table lorsqu'on est dans la vérification de l'arbre
     */
    public void sortieBlockVerif(){
        GestionnaireNombres.getInstance().decrementer();
        tableCourante = this.tableCourante.getPere();
    }

    @Override
    public String toString() {
        return "TDS{" +
                "racine=\n" + racine +
                ", \ntableCourante=\n" + tableCourante +
                '}';
    }
}
