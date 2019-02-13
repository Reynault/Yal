package yal.analyse.tds;

import yal.analyse.tds.entree.Entree;
import yal.analyse.tds.symbole.Symbole;

import java.util.ArrayList;
import java.util.HashMap;

public class TDSLocale extends TableDesSymboles{
    /**
     * table pere dans l'arborecense a block
     * si null pere = tds
     */
    private TableDesSymboles pere;

    /**
     * tablefille correspondant aux block inclue dans le block courant
     */
    private ArrayList<TDSLocale> lesFilles;

    /**
     *Numero du block courant
     */
    private int numeroBlock;

    /**
     * Table assosiaive entré symbol
     */
    private HashMap<Entree, Symbole> table;

    public TDSLocale(int numeroBlock, TableDesSymboles pere){
        this.numeroBlock = numeroBlock;
        this.pere = pere;
    }

    @Override
    public void ajouter(Entree e, Symbole S){

    }

    @Override
    public Symbole identifier(Entree e) {
        return null;
    }

}
