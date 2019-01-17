package yal.analyse;

import java.util.HashMap;

public class TDS {
    private static TDS instance = new TDS();

    private HashMap<String,Integer> table;

    private TDS(){}

    public static TDS getInstance() {
        return instance;
    }
    public void ajouter(String entree, Symbole deplacement){

    }
    public Symbole identifier(String entree){
        return null;
    }
    private HashMap<String, Integer> getTable() {
        return table;
    }

    private void setTable(HashMap<String, Integer> table) {
        this.table = table;
    }
}
