package yal.analyse;

import java.util.HashMap;

public class TDS {
    private static TDS instance = new TDS();

    private int deplacement = 0;

    private HashMap<String,Symbole> table;

    private TDS(){
        table = new HashMap<String,Symbole>();
    }

    public static TDS getInstance() {
        return instance;
    }

    public void ajouter(String entree, Symbole deplacement){
        table.put(entree, deplacement);
    }

    public Symbole identifier(String entree){
        return table.get(entree);
    }

    public int getDeplacement(){
        int temp = deplacement;
        deplacement = deplacement - 4;
        return temp;
    }
}
