package yal.arbre;

import yal.analyse.Symbole;
import yal.analyse.TDS;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/**
 * 21 novembre 2018
 *
 * @author brigitte wrobel-dautcourt
 */

public class BlocDInstructions extends ArbreAbstrait {
    
    protected ArrayList<ArbreAbstrait> programme ;
    
    protected static String zoneData = "# Code généré par Yal\n" +
            ".data\n" +
            "# Caractère de fin de ligne\n" +
                                            "finLigne:     .asciiz \"\\n\"\n" +
                                            "              .align 2\n" ;
    
    protected static String debutCode = "# Début du programme\n" +
            ".text\n" +
            "main :\n" +
            "\t# Initialisation de s7 avec sp\n" +
            "\tmove $s7, $sp\n" ;
    protected static String finCode = "end :\n" +
                                      "    li $v0, 10\n" +
                                      "    syscall\n" ;

    public BlocDInstructions(int n) {
        super(n) ;
        programme = new ArrayList<>() ;
    }
    
    public void ajouter(ArbreAbstrait a) {
        programme.add(a) ;
    }
    
    @Override
    public String toString() {
        return programme.toString() ;
    }

    @Override
    public void verifier() {
        for (ArbreAbstrait a : programme) {
            a.verifier() ;
        }
    }
    
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
