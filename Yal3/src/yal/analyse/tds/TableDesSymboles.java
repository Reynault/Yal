package yal.analyse.tds;

import yal.analyse.tds.entree.Entree;
import yal.analyse.tds.symbole.Symbole;

abstract class TableDesSymboles {
    public abstract void ajouter(Entree e, Symbole S);
    public abstract Symbole identifier(Entree e);
}
