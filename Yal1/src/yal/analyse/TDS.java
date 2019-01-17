package yal.analyse;

import java.util.HashMap;

public class TDS {
    private static TDS instance = new TDS();

    private HashMap<String,Symbole> table;

    private TDS(){}

    public static TDS getInstance() {
        return instance;
    }
    public void ajouter(String entree, Symbole deplacement){
        table.put(entree, deplacement);
    }
    public Symbole identifier(String entree){
        return table.get(entree);
    }
}
