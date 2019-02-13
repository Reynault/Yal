package yal.exceptions;

/**
 * Classe qui représente une exception de type syntaxique
 */
public class AnalyseSyntaxiqueException extends AnalyseException {
    /**
     * Constructeur
     * @param m message d'erreur
     */
    public AnalyseSyntaxiqueException(String m) {
        super("ERREUR SYNTAXIQUE :\n\t" + m) ;
    }

}
