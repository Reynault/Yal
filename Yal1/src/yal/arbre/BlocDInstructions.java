package yal.arbre;

import yal.analyse.TDS;

import java.util.ArrayList;

/**
 * 21 novembre 2018
 *
 * @author brigitte wrobel-dautcourt
 */

public class BlocDInstructions extends ArbreAbstrait {
    // Liste des instructions du programme
    protected ArrayList<ArbreAbstrait> programme ;
    // Zone du code qui stocke les données utilisées
    protected static String zoneData = "# Code généré par Yal\n" +
            ".data\n" +
            "# Caractère de fin de ligne\n" +
                                            "finLigne:     .asciiz \"\\n\"\n" +
                                            "              .align 2\n" ;
    // Début du programme
    protected static String debutCode = "# Début du programme\n" +
            ".text\n" +
            "main :\n" +
            "\t# Initialisation de s7 avec sp\n" +
            "\tmove $s7, $sp\n" ;
    // Fin du programme
    protected static String finCode = "end :\n" +
                                      "    li $v0, 10\n" +
                                      "    syscall\n" ;
    /**
     * Constructeur
     * @param n numéro de la ligne
     */
    public BlocDInstructions(int n) {
        super(n) ;
        programme = new ArrayList<>() ;
    }

    /**
     * Méthode qui permet d'ajouter un arbre abstrait
     * @param a le nouvel arbre
     */
    public void ajouter(ArbreAbstrait a) {
        programme.add(a) ;
    }

    /**
     * Méthode toString
     * @return
     */
    @Override
    public String toString() {
        return programme.toString() ;
    }

    /**
     * Méthode vérifier qui vérifie la sémantique
     */
    @Override
    public void verifier() {
        for (ArbreAbstrait a : programme) {
            a.verifier() ;
        }
    }

    /**
     * Méthode qui permet de traduire en mips
     * @return
     */
    @Override
    public String toMIPS() {
        StringBuilder sb = new StringBuilder("") ;
        sb.append(zoneData) ;
        sb.append(debutCode) ;
        // On initialise toutes les variables utilisées
        TDS instance = TDS.getInstance();
        sb.append("\t# Réservation de l'espace dans la pile\n");
        sb.append("\taddi $sp, $sp, "+instance.getDeplacement()+"\n");
        for (ArbreAbstrait a : programme) {
            sb.append(a.toMIPS()) ;
        }
        sb.append(finCode) ;
        return sb.toString() ;
    }

}
