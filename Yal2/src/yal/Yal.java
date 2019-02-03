package yal;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import yal.analyse.AnalyseurLexical;
import yal.analyse.AnalyseurSyntaxique;
import yal.arbre.ArbreAbstrait;
import yal.exceptions.AnalyseException;

/**
 * Classe principale
 */
public class Yal {
    /**
     * Constructeur qui initialise l'analyseur syntaxique et lexicale.
     *
     * Qui contruit l'arbre abstrait et qui créer le fichier mips avec
     * le code généré.
     *
     * @param nomFichier nom du fichier yal
     */
    public Yal(String nomFichier) {
        try {
            // Récupération des analyseurs et création de l'arbre abstrait
            AnalyseurSyntaxique analyseur = new AnalyseurSyntaxique(new AnalyseurLexical(new FileReader(nomFichier)));
            ArbreAbstrait arbre = (ArbreAbstrait) analyseur.parse().value;

            // Vérification sémantique du code
            arbre.verifier() ;
            System.out.println("COMPILATION OK") ;

            // Création du fichier mips, et ecriture du code mips
            String nomSortie = nomFichier.replaceAll("[.]yal", ".mips") ;
            PrintWriter flot = new PrintWriter(new BufferedWriter(new FileWriter(nomSortie))) ;
            flot.println(arbre.toMIPS());
            flot.close() ;
        }
        // Gestion des erreurs liées au fichier
        catch (FileNotFoundException ex) {
            System.err.println("Fichier " + nomFichier + " inexistant") ;
        }
        // Gestion des erreurs de l'application
        catch (AnalyseException ex) {
            System.err.println(ex.getMessage());
        }
        // Gestion des autres exceptions
        catch (Exception ex) {
            Logger.getLogger(Yal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Méthode main
     * @param args
     */
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Nombre incorrect d'arguments") ;
            System.err.println("\tjava -jar yal.jar <fichierSource.yal>") ;
            System.exit(1) ;
        }
        int e = 50 + 50 / 10 * 10;
        System.out.println("TEST : "+e);
        new Yal(args[0]) ;
    }
    
}
