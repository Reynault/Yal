package yal.analyse.tds;

import yal.analyse.tds.entree.Entree;
import yal.analyse.tds.symbole.Symbole;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Classe qui représente une table locale, un bloc dans le programme
 */
public class TDSLocale extends TableDesSymboles {
    /**
     * table pere dans l'arborecense a block
     * si null pere = racine
     */
    private TDSLocale pere;

    /**
     * tablefille correspondant aux block inclue dans le block courant
     */
    private ArrayList<TDSLocale> lesFilles;

    /**
     * Numero du block courant
     */
    private int numeroBlock;

    private int idBlock;

    /**
     * Deplacement qui correspond aux variables du bloc courant
     */
    private int deplacement = 0;

    /**
     * Deplacement des paramètres
     */
    private int deplacementParam = 0 ;
    /**
     * Table assosiaive entré symbol
     */
    private HashMap<Entree, Symbole> table;

    /**
     * Constructeur qui prend trois paramètres
     * @param numeroBlock le numéro du block
     * @param pere la table père
     * @param id l'id du block
     */
    public TDSLocale(int numeroBlock, TDSLocale pere, int id) {
        this.numeroBlock = numeroBlock;
        this.pere = pere;
        this.lesFilles = new ArrayList<>();
        this.table = new HashMap<Entree, Symbole>();
        this.idBlock = id;
    }

    /**
     * Ajout d'une entrée et d'un symbole
     * @param e l'entrée
     * @param S le symbole
     */
    @Override
    public void ajouter(Entree e, Symbole S) {
        //System.out.println(this.getNumeroBlock() + ": " + e.getNom());
        table.put(e,S);
    }

    /**
     * Méthode d'identification d'une entrée
     * @param e l'entrée à identifier
     * @return Le symbole de l'entrée
     */
    @Override
    public Symbole identifier(Entree e) {
        Symbole s = null;
        // On commence par regarder dans la table locale, si
        // on ne trouve pas, on regarde le père
        if(table.containsKey(e)){
            s = table.get(e);
        }else{
            if(pere != null){
                s = pere.table.get(e);
            }
        }
        return s;
    }

    /**
     * Méthode qui permet de savoir si une entrée existe
     * @param e l'entrée à vérifier
     * @return un booléen qui indique si l'entrée existe
     */
    @Override
    public boolean existe(Entree e) {
        // On regarde si la table locale contient l'entrée,
        // si ce n'est pas le cas, on regarde pour le père
        if (table.containsKey(e)) {
            return true;
        } else {
            if (pere != null) {
                return pere.existe(e);
            }
        }
        return false;
    }

    /**
     * Méthode d'ajout d'un fils
     * @param tdsLocale le fils à ajouter
     */
    public void ajouterFils(TDSLocale tdsLocale) {
        this.lesFilles.add(tdsLocale);
    }

    /**
     * Getteur du père
     * @return le père
     */
    public TDSLocale getPere() {
        return pere;
    }

    /**
     * Getteur des filles
     * @return les filles de la tds locale
     */
    public ArrayList<TDSLocale> getLesFilles() {
        return lesFilles;
    }

    /**
     * Méthode de réinitialisation de la table
     */
    @Override
    public void reinitialiserTable() {
        table = new HashMap<Entree, Symbole>();
        for (TableDesSymboles tds : lesFilles){
            tds.reinitialiserTable();
        }
    }

    /**
     * Création d'un déplacement dans la table locale
     * @return le nouveau déplacement
     */
    @Override
    public int creerDeplacement() {
        int temp = deplacement;
        this.deplacement = deplacement - 4;
        return temp;
    }

    public int creerDeplacementParam(int nbparam){
        return 16 + nbparam * 4;
    }

    @Override
    public int getDeplacement() {
        return deplacement;
    }

    public int getNumeroBlock() {
        return numeroBlock;
    }

    public int getIdBlock() {
        return idBlock;
    }

    @Override
    public String toString() {
        return "numBlock : " + this.getNumeroBlock() + "\n" + table.toString();
    }
}
