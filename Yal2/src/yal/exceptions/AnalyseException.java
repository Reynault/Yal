package yal.exceptions;

/**
 * Classe qui représente une exception propre à l'application
 */
public abstract class AnalyseException extends RuntimeException {
    /**
     * Constructeur
     * @param m message d'erreur
     */
    protected AnalyseException(String m) {
        super(m) ;
    }

}
