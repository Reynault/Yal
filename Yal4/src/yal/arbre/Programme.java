package yal.arbre;

import yal.analyse.tds.TDS;

/**
 * Classe qui représente un programme yal
 */
public class Programme extends ArbreAbstrait {
    // Bloc d'instructions
    protected BlocDInstructions fonctions;
    protected BlocDInstructions instructions;

    // Zone du code qui stocke les données utilisées
    protected static String zoneData = "# Code généré par Yal\n" +
            ".data\n" +
            "# Caractère de fin de ligne\n" +
            "finLigne:     .asciiz \"\\n\"\n" +
            "booleenVrai:   .asciiz \"vrai\"\n"+
            "booleenFaux:   .asciiz \"faux\"\n"+
            "              .align 2\n" ;
    // Début du programme
    protected static String debutCode = "# Début du programme\n" +
            ".text\n" +
            "main :\n" +
            "\t# Initialisation de s7 avec sp\n" +
            "\tli $v0, 0\n" +
            "\tsw $v0, 0($sp)\n" +
            "\taddi $sp, $sp, -4\n" +
            "\tmove $s7, $sp\n";
    // Fin du programme
    protected static String finCode = "end :\n" +
            "    li $v0, 10\n" +
            "    syscall\n" ;

    /**
     * Constructeur
     *
     * @param n numéro de la ligne
     */
    public Programme(int n,BlocDInstructions bloc) {
        super(n);
        instructions = bloc;
    }

    @Override
    public void verifier() {
        instructions.verifier();
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
        // Instructions du programme
        sb.append(instructions.toMIPS());
        sb.append(finCode);
        return sb.toString();
    }
}
