package yal.exceptions;

/**
 * Classe qui représente une exception de type sémantique
 */
public class AnalyseSemantiqueException extends AnalyseException {
    /**
     * Constructeur
     * @param ligne numéro de la ligne
     * @param m message d'erreur
     */
    public AnalyseSemantiqueException(int ligne, String m) {
        super("ERREUR SEMANTIQUE : ligne " + ligne + "\n\t" + m + "\n") ;
    }

}
