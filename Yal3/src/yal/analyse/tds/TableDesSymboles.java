package yal.analyse.tds;

import yal.analyse.tds.entree.Entree;
import yal.analyse.tds.symbole.Symbole;

abstract class TableDesSymboles {
    public abstract void ajouter(Entree e, Symbole S);
    public abstract Symbole identifier(Entree e);
    public abstract boolean existe(Entree e);
    public abstract void reinitialiserTable();
    public abstract int creerDeplacement();
    public abstract int getDeplacement();
}
