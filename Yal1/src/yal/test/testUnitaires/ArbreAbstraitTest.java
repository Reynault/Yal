package yal.test.testUnitaires;

import yal.analyse.tds.entree.EntreeVariable;
import yal.analyse.tds.symbole.Symbole;
import yal.analyse.tds.TDS;
import yal.analyse.tds.symbole.SymboleVariable;
import yal.arbre.BlocDInstructions;
import yal.arbre.Programme;
import yal.arbre.expressions.ConstanteEntiere;
import yal.arbre.expressions.Variable;
import yal.arbre.instructions.Ecrire;
import yal.arbre.instructions.Lire;
import yal.arbre.instructions.affectation.AffectationSimple;
import yal.exceptions.AnalyseSemantiqueException;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;

/**
 * Classe ArbreAbstrait test qui contient les tests unitaires liés à l'arbre abstrait
 */
public class ArbreAbstraitTest {

    @org.junit.Before
    public void initialisation(){
        TDS.getInstance().reinitialiserTable();
    }

    @org.junit.Test
    public void code_Null_toMIPS() throws Exception {
        BlocDInstructions bloc = new BlocDInstructions(1);
        String code = bloc.toMIPS();
        assertFalse("Code fourni != de null",code == null);
    }

    @org.junit.Test
    public void programme_Vide_toMIPS() throws Exception {
        TDS instance = TDS.getInstance();
        BlocDInstructions bloc = new BlocDInstructions(1);
        Programme p = new Programme(0,bloc);
        String code = p.toMIPS();
        String codeAttendu = "# Code généré par Yal\n" +
                ".data\n" +
                "# Caractère de fin de ligne\n" +
                "finLigne:     .asciiz \"\\n\"\n" +
                "              .align 2\n"+
                "# Début du programme\n" +
                ".text\n" +
                "main :\n" +
                "\t# Initialisation de s7 avec sp\n" +
                "\tmove $s7, $sp\n"+
                "\t# Réservation de l'espace dans la pile\n"+
                "\taddi $sp, $sp, 0\n"+
                "end :\n" +
                "    li $v0, 10\n" +
                "    syscall\n" ;
        assertEquals("Code attendu != code fourni",code,codeAttendu);
    }


    @org.junit.Test
    public void ecriture_Constante_Entiere_toMIPS() throws Exception {
        TDS instance = TDS.getInstance();
        BlocDInstructions bloc = new BlocDInstructions(1);
        bloc.ajouter(new Ecrire(new ConstanteEntiere("4",2),2));
        Programme p = new Programme(0,bloc);
        String code = p.toMIPS();
        String codeAttendu = "# Code généré par Yal\n" +
                ".data\n" +
                "# Caractère de fin de ligne\n" +
                "finLigne:     .asciiz \"\\n\"\n" +
                "              .align 2\n"+
                "# Début du programme\n" +
                ".text\n" +
                "main :\n" +
                "\t# Initialisation de s7 avec sp\n" +
                "\tmove $s7, $sp\n"+
                "\t# Réservation de l'espace dans la pile\n"+
                "\taddi $sp, $sp, 0\n"+
                "\t# affichage de l'expression\n" +
                "\t# Chargement immédiat d'une constante entière\n"+
                "\tli $v0, "+
                "4" +
                "\n" +
                "\tmove $a0, $v0\n" +
                "\tli $v0, 1\n" +
                "\tsyscall\n" +
                "\tli $v0, 4      # retour à la ligne\n" +
                "\tla $a0, finLigne\n" +
                "\tsyscall\n"+
                "end :\n" +
                "    li $v0, 10\n" +
                "    syscall\n" ;
        assertEquals("Code attendu != code fourni",code,codeAttendu);
    }

    @org.junit.Test
    public void stockage_Constante_Entiere_toMIPS() throws Exception {
        BlocDInstructions bloc = new BlocDInstructions(1);
        bloc.ajouter(new ConstanteEntiere("4",2));
        Programme p = new Programme(0,bloc);
        String code = p.toMIPS();
        String codeAttendu = "# Code généré par Yal\n" +
                ".data\n" +
                "# Caractère de fin de ligne\n" +
                "finLigne:     .asciiz \"\\n\"\n" +
                "              .align 2\n"+
                "# Début du programme\n" +
                ".text\n" +
                "main :\n" +
                "\t# Initialisation de s7 avec sp\n" +
                "\tmove $s7, $sp\n"+
                "\t# Réservation de l'espace dans la pile\n"+
                "\taddi $sp, $sp, 0\n"+
                "\t# Chargement immédiat d'une constante entière\n"+
                "\tli $v0, "+
                "4" +
                "\n" +
                "end :\n" +
                "    li $v0, 10\n" +
                "    syscall\n" ;
        assertEquals("Code attendu != code fourni",code,codeAttendu);
    }

    @org.junit.Test
    public void ecriture_stockage_toMIPS() throws Exception {
        BlocDInstructions bloc = new BlocDInstructions(1);
        bloc.ajouter(new ConstanteEntiere("5",2));
        bloc.ajouter(new Ecrire(new ConstanteEntiere("4",3),3));
        Programme p = new Programme(0,bloc);
        String code = p.toMIPS();
        String codeAttendu = "# Code généré par Yal\n" +
                ".data\n" +
                "# Caractère de fin de ligne\n" +
                "finLigne:     .asciiz \"\\n\"\n" +
                "              .align 2\n"+
                "# Début du programme\n" +
                ".text\n" +
                "main :\n" +
                "\t# Initialisation de s7 avec sp\n" +
                "\tmove $s7, $sp\n"+
                "\t# Réservation de l'espace dans la pile\n"+
                "\taddi $sp, $sp, 0\n"+
                "\t# Chargement immédiat d'une constante entière\n"+
                "\tli $v0, "+
                "5" +
                "\n" +
                "\t# affichage de l'expression\n" +
                "\t# Chargement immédiat d'une constante entière\n"+
                "\tli $v0, "+
                "4" +
                "\n" +
                "\tmove $a0, $v0\n" +
                "\tli $v0, 1\n" +
                "\tsyscall\n" +
                "\tli $v0, 4      # retour à la ligne\n" +
                "\tla $a0, finLigne\n" +
                "\tsyscall\n"+
                "end :\n" +
                "    li $v0, 10\n" +
                "    syscall\n" ;
        assertEquals("Code attendu != code fourni",code,codeAttendu);
    }

    @org.junit.Test
    public void code_simple_verifier() throws Exception {
        BlocDInstructions b = new BlocDInstructions(1);
        b.verifier();
    }

    @org.junit.Test
    public void code_constante_verifier() throws Exception {
        BlocDInstructions b = new BlocDInstructions(1);
        b.ajouter(new ConstanteEntiere("5",2));
        b.verifier();
    }

    @org.junit.Test
    public void code_ecrire_verifier() throws Exception {
        BlocDInstructions b = new BlocDInstructions(1);
        b.ajouter(new Ecrire(new ConstanteEntiere("5",2),2));
        b.verifier();
    }

    @org.junit.Test (expected = AnalyseSemantiqueException.class)
    public void code_ecrire_valeurNonValide_verifier() throws Exception {
        BlocDInstructions b = new BlocDInstructions(1);
        b.ajouter(new Ecrire(new ConstanteEntiere("50000000000000000000000000000",2),2));
        b.verifier();
    }

    @org.junit.Test (expected = AnalyseSemantiqueException.class)
    public void lire_variable_non_declarer_verifier() throws Exception {
        BlocDInstructions b = new BlocDInstructions(1);
        b.ajouter(new Lire(new Variable(2,"a"),2));
        b.verifier();
    }

    @org.junit.Test
    public void lire_variable_declarer_verifier() throws Exception {
        // Table des symboles
        TDS instance = TDS.getInstance();
        int depl = instance.getDeplacement();
        instance.ajouter(2,new EntreeVariable("a"),new SymboleVariable(depl,"entier"));
        // Instructions
        BlocDInstructions b = new BlocDInstructions(1);
        b.ajouter(new Lire(new Variable(2,"a"),2));
        b.verifier();
    }

    @org.junit.Test
    public void affectation_variable_declarer_verifier() throws Exception {
        // Table des symboles
        TDS instance = TDS.getInstance();
        int depl = instance.getDeplacement();
        instance.ajouter(1,new EntreeVariable("a"),new SymboleVariable(instance.getDeplacement(),"entier"));
        depl = instance.getDeplacement();
        instance.ajouter(1,new EntreeVariable("b"),new SymboleVariable(instance.getDeplacement(),"entier"));
        // Instructions
        BlocDInstructions b = new BlocDInstructions(1);
        b.ajouter(new AffectationSimple(2,new Variable(2,"b"),new Variable(2,"a")));
        b.verifier();
    }

    @org.junit.Test
    public void affectation_constante_verifier() throws Exception {
        // Table des symboles
        TDS instance = TDS.getInstance();
        int depl = instance.getDeplacement();
        instance.ajouter(1,new EntreeVariable("a"),new SymboleVariable(instance.getDeplacement(),"entier"));
        // Instructions
        BlocDInstructions b = new BlocDInstructions(1);
        b.ajouter(new AffectationSimple(2,new ConstanteEntiere("5",2),new Variable(2,"a")));
        b.verifier();
    }

    @org.junit.Test (expected = AnalyseSemantiqueException.class)
    public void affectation_non_declarer_verifier() throws Exception {
        // Instructions
        BlocDInstructions b = new BlocDInstructions(1);
        b.ajouter(new AffectationSimple(2,new ConstanteEntiere("5",2),new Variable(2,"a")));
        b.verifier();
    }

    @org.junit.Test (expected = AnalyseSemantiqueException.class)
    public void affectation_variable_non_declarer_verifier() throws Exception {
        // Table des symboles
        TDS instance = TDS.getInstance();
        int depl = instance.getDeplacement();
        instance.ajouter(1,new EntreeVariable("b"),new SymboleVariable(instance.getDeplacement(),"entier"));
        // Instructions
        BlocDInstructions b = new BlocDInstructions(1);
        b.ajouter(new AffectationSimple(2,new Variable(2,"b"),new Variable(2,"a")));
        b.verifier();
    }

    @org.junit.Test
    public void ecriture_variable_declarer_verifier() throws Exception {
        // Table des symboles
        TDS instance = TDS.getInstance();
        int depl = instance.getDeplacement();
        instance.ajouter(1,new EntreeVariable("a"),new SymboleVariable(instance.getDeplacement(),"entier"));
        // Instructions
        BlocDInstructions b = new BlocDInstructions(1);
        b.ajouter(new Ecrire(new Variable(2,"a"),2));
        b.verifier();
    }

    @org.junit.Test (expected = AnalyseSemantiqueException.class)
    public void ecriture_variable_non_declarer_verifier() throws Exception {
        // Instructions
        BlocDInstructions b = new BlocDInstructions(1);
        b.ajouter(new Ecrire(new Variable(2,"a"),2));
        b.verifier();
    }

    @org.junit.Test (expected = AnalyseSemantiqueException.class)
    public void ecriture_constante_invalide_verifier() throws Exception {
        // Instructions
        BlocDInstructions b = new BlocDInstructions(1);
        b.ajouter(new Ecrire(new ConstanteEntiere("500000000000000000000000000000000000",2),2));
        b.verifier();
    }

    @org.junit.Test
    public void lecture_variable(){
        TDS instance = TDS.getInstance();
        instance.ajouter(1,new EntreeVariable("a"),new SymboleVariable(instance.getDeplacement(),"entier"));
        BlocDInstructions i = new BlocDInstructions(1);
        i.ajouter(new Lire(new Variable(2,"a"),2));
        Programme p = new Programme(0,i);
        String code = p.toMIPS();
        String codeAttendu = "# Code généré par Yal\n" +
                ".data\n" +
                "# Caractère de fin de ligne\n" +
                "finLigne:     .asciiz \"\\n\"\n" +
                "              .align 2\n"+
                "# Début du programme\n" +
                ".text\n" +
                "main :\n" +
                "\t# Initialisation de s7 avec sp\n" +
                "\tmove $s7, $sp\n"+
                "\t# Réservation de l'espace dans la pile\n"+
                "\taddi $sp, $sp, -4\n"+
                "\t# Lire un entier\n"+
                "\tli $v0 , 5 \t# $v0 <- 5 (code du read entier)\n"+
                "\tsyscall \t# le résultat de la lecture est dans $V0 \n"+
                "\tsw $v0, 0($s7)"+
                "end :\n" +
                "    li $v0, 10\n" +
                "    syscall\n" ;
        assertEquals("Code != Code attendu",code,codeAttendu);
    }

    @org.junit.Test
    public void ecriture_variable(){
        TDS instance = TDS.getInstance();
        instance.ajouter(1,new EntreeVariable("a"),new SymboleVariable(instance.getDeplacement(),"entier"));
        BlocDInstructions i = new BlocDInstructions(1);
        i.ajouter(new Ecrire(new Variable(2,"a"),2));
        Programme p = new Programme(0,i);
        String code = p.toMIPS();
        String codeAttendu = "# Code généré par Yal\n" +
                ".data\n" +
                "# Caractère de fin de ligne\n" +
                "finLigne:     .asciiz \"\\n\"\n" +
                "              .align 2\n"+
                "# Début du programme\n" +
                ".text\n" +
                "main :\n" +
                "\t# Initialisation de s7 avec sp\n" +
                "\tmove $s7, $sp\n"+
                "\t# Réservation de l'espace dans la pile\n"+
                "\taddi $sp, $sp, -4\n"+
                "\t# affichage de l'expression\n" +
                "\t# Chargement de la valeur de la variable dans v0\n"+
                "\tlw $v0, 0($s7)\n"+
                "\tmove $a0, $v0\n" +
                "\tli $v0, 1\n" +
                "\tsyscall\n" +
                "\tli $v0, 4      # retour à la ligne\n" +
                "\tla $a0, finLigne\n" +
                "\tsyscall\n"+
                "end :\n" +
                "    li $v0, 10\n" +
                "    syscall\n" ;
        assertEquals("Code != Code attendu",code,codeAttendu);
    }

    @org.junit.Test
    public void affectation_constante_entiere(){
        TDS instance = TDS.getInstance();
        instance.ajouter(1,new EntreeVariable("a"),new SymboleVariable(instance.getDeplacement(),"entier"));
        BlocDInstructions i = new BlocDInstructions(1);
        i.ajouter(new AffectationSimple(2,new ConstanteEntiere("2",2),new Variable(2,"a")));
        Programme p = new Programme(0,i);
        String code = p.toMIPS();
        String codeAttendu = "# Code généré par Yal\n" +
                ".data\n" +
                "# Caractère de fin de ligne\n" +
                "finLigne:     .asciiz \"\\n\"\n" +
                "              .align 2\n"+
                "# Début du programme\n" +
                ".text\n" +
                "main :\n" +
                "\t# Initialisation de s7 avec sp\n" +
                "\tmove $s7, $sp\n"+
                "\t# Réservation de l'espace dans la pile\n"+
                "\taddi $sp, $sp, -4\n"+
                "\t# Chargement immédiat d'une constante entière\n"+
                "\tli $v0, "+
                "2"+
                "\n"+
                "\t# Affectation simple\n"+
                "\tsw $v0, 0($s7)\n"+
                "end :\n" +
                "    li $v0, 10\n" +
                "    syscall\n" ;
        assertEquals("Code != Code attendu",code,codeAttendu);
    }

    @org.junit.Test
    public void affectation_variable(){
        TDS instance = TDS.getInstance();
        instance.ajouter(1,new EntreeVariable("b"),new SymboleVariable(instance.getDeplacement(),"entier"));
        instance.ajouter(1,new EntreeVariable("a"),new SymboleVariable(instance.getDeplacement(),"entier"));
        BlocDInstructions i = new BlocDInstructions(1);
        i.ajouter(new AffectationSimple(2,new Variable(2,"a"),new Variable(2,"b")));
        i.verifier();
        Programme p = new Programme(0,i);
        String code = p.toMIPS();
        String codeAttendu = "# Code généré par Yal\n" +
                ".data\n" +
                "# Caractère de fin de ligne\n" +
                "finLigne:     .asciiz \"\\n\"\n" +
                "              .align 2\n"+
                "# Début du programme\n" +
                ".text\n" +
                "main :\n" +
                "\t# Initialisation de s7 avec sp\n" +
                "\tmove $s7, $sp\n"+
                "\t# Réservation de l'espace dans la pile\n"+
                "\taddi $sp, $sp, -8\n"+
                "\t# Chargement de la valeur de la variable dans v0\n"+
                "\tlw $v0, -4($s7)\n"+
                "\t# Affectation simple\n"+
                "\tsw $v0, 0($s7)\n"+
                "end :\n" +
                "    li $v0, 10\n" +
                "    syscall\n" ;
        assertEquals("Code != Code attendu",code,codeAttendu);
    }

    @org.junit.Test
    public void affectation_variable_inverse(){
        TDS instance = TDS.getInstance();
        instance.ajouter(1,new EntreeVariable("b"),new SymboleVariable(instance.getDeplacement(),"entier"));
        instance.ajouter(1,new EntreeVariable("a"),new SymboleVariable(instance.getDeplacement(),"entier"));
        BlocDInstructions i = new BlocDInstructions(1);
        i.ajouter(new AffectationSimple(2,new Variable(2,"b"),new Variable(2,"a")));
        Programme p = new Programme(0,i);
        p.verifier();
        String code = p.toMIPS();
        String codeAttendu = "# Code généré par Yal\n" +
                ".data\n" +
                "# Caractère de fin de ligne\n" +
                "finLigne:     .asciiz \"\\n\"\n" +
                "              .align 2\n"+
                "# Début du programme\n" +
                ".text\n" +
                "main :\n" +
                "\t# Initialisation de s7 avec sp\n" +
                "\tmove $s7, $sp\n"+
                "\t# Réservation de l'espace dans la pile\n"+
                "\taddi $sp, $sp, -8\n"+
                "\t# Chargement de la valeur de la variable dans v0\n"+
                "\tlw $v0, 0($s7)\n"+
                "\t# Affectation simple\n"+
                "\tsw $v0, -4($s7)\n"+
                "end :\n" +
                "    li $v0, 10\n" +
                "    syscall\n" ;
        assertEquals("Code != Code attendu",code,codeAttendu);
    }

    @org.junit.Test (expected = AnalyseSemantiqueException.class)
    public void declarer_variable_deja_declare(){
        TDS instance = TDS.getInstance();
        instance.ajouter(1,new EntreeVariable("b"),new SymboleVariable(instance.getDeplacement(),"entier"));
        instance.ajouter(1,new EntreeVariable("b"),new SymboleVariable(instance.getDeplacement(),"entier"));
    }
}