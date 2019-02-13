package yal.analyse.tds;

import yal.analyse.tds.entree.Entree;
import yal.analyse.tds.symbole.Symbole;

import java.util.ArrayList;
import java.util.HashMap;

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

    /**
     * Table assosiaive entré symbol
     */
    private HashMap<Entree, Symbole> table;

    public TDSLocale(int numeroBlock, TDSLocale pere) {
        this.numeroBlock = numeroBlock;
        this.pere = pere;
        this.lesFilles = new ArrayList<>();
        this.table = new HashMap<Entree, Symbole>();
    }

    @Override
    public void ajouter(Entree e, Symbole S) {

    }

    @Override
    public Symbole identifier(Entree e) {
        return null;
    }

    @Override
    public boolean existe(Entree e) {
        if (table.containsKey(e)) {
            return true;
        } else {
            if (pere != null) {
                return pere.existe(e);
            }
        }
        return false;
    }

    public void ajouterFils(TDSLocale tdsLocale) {
        this.lesFilles.add(tdsLocale);
    }

    public TDSLocale getPere() {
        return pere;
    }

    public ArrayList<TDSLocale> getLesFilles() {
        return lesFilles;
    }

    @Override
    public void reinitialiserTable() {
        table = new HashMap<Entree, Symbole>();
        for (TableDesSymboles tds : lesFilles){
            tds.reinitialiserTable();
        }
    }

    public int getNumeroBlock() {
        return numeroBlock;
    }
}