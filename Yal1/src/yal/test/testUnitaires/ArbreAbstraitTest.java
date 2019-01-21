package yal.test.testUnitaires;

import yal.analyse.Symbole;
import yal.analyse.TDS;
import yal.arbre.BlocDInstructions;
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
        String code = bloc.toMIPS();
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
        String code = bloc.toMIPS();
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
        String code = bloc.toMIPS();
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
        String code = bloc.toMIPS();
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
        b.ajouter(new Lire("a",2));
        b.verifier();
    }

    @org.junit.Test
    public void lire_variable_declarer_verifier() throws Exception {
        // Table des symboles
        TDS instance = TDS.getInstance();
        int depl = instance.getDeplacement();
        instance.ajouter("a",new Symbole(depl,"entier"));
        // Instructions
        BlocDInstructions b = new BlocDInstructions(1);
        b.ajouter(new Lire("a",2));
        b.verifier();
    }

    @org.junit.Test
    public void affectation_variable_declarer_verifier() throws Exception {
        // Table des symboles
        TDS instance = TDS.getInstance();
        int depl = instance.getDeplacement();
        instance.ajouter("a",new Symbole(depl,"entier"));
        depl = instance.getDeplacement();
        instance.ajouter("b",new Symbole(depl,"entier"));
        // Instructions
        BlocDInstructions b = new BlocDInstructions(1);
        b.ajouter(new AffectationSimple(2,new Variable(2,"b"),"a"));
        b.verifier();
    }

    @org.junit.Test
    public void affectation_constante_verifier() throws Exception {
        // Table des symboles
        TDS instance = TDS.getInstance();
        int depl = instance.getDeplacement();
        instance.ajouter("a",new Symbole(depl,"entier"));
        // Instructions
        BlocDInstructions b = new BlocDInstructions(1);
        b.ajouter(new AffectationSimple(2,new ConstanteEntiere("5",2),"a"));
        b.verifier();
    }

    @org.junit.Test (expected = AnalyseSemantiqueException.class)
    public void affectation_non_declarer_verifier() throws Exception {
        // Instructions
        BlocDInstructions b = new BlocDInstructions(1);
        b.ajouter(new AffectationSimple(2,new ConstanteEntiere("5",2),"a"));
        b.verifier();
    }

    @org.junit.Test (expected = AnalyseSemantiqueException.class)
    public void affectation_variable_non_declarer_verifier() throws Exception {
        // Table des symboles
        TDS instance = TDS.getInstance();
        int depl = instance.getDeplacement();
        instance.ajouter("b",new Symbole(depl,"entier"));
        // Instructions
        BlocDInstructions b = new BlocDInstructions(1);
        b.ajouter(new AffectationSimple(2,new Variable(2,"b"),"a"));
        b.verifier();
    }

    @org.junit.Test
    public void ecriture_variable_declarer_verifier() throws Exception {
        // Table des symboles
        TDS instance = TDS.getInstance();
        int depl = instance.getDeplacement();
        instance.ajouter("a",new Symbole(depl,"entier"));
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
}