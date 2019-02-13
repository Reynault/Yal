package yal.exceptions;

/**
 * Classe qui représente une exception de type lexicale
 */
public class AnalyseLexicaleException extends AnalyseException {
    /**
     * Constructeur
     * @param ligne numéro de la ligne
     * @param colonne numéro de la colonne
     * @param m message d'erreur
     */
    public AnalyseLexicaleException(int ligne, int colonne, String m) {
        super("ERREUR LEXICALE :\n\tligne " + ligne + " colonne " + colonne + "\n\t" + m + " : caractère non reconnu") ;
    }

}
