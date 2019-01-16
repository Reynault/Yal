package yal.test.testUnitaires;

import yal.arbre.BlocDInstructions;
import yal.arbre.expressions.ConstanteEntiere;
import yal.arbre.instructions.Ecrire;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;

public class ArbreAbstraitTest {

    @org.junit.Test
    public void test0_toMIPS() throws Exception {
        BlocDInstructions bloc = new BlocDInstructions(1);
        String code = bloc.toMIPS();
        assertFalse("Code fourni != de null",code == null);
    }

    @org.junit.Test
    public void test1_toMIPS() throws Exception {
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
                "end :\n" +
                "    li $v0, 10\n" +
                "    syscall\n" ;
        assertEquals("Code attendu != code fourni",code,codeAttendu);
    }


    @org.junit.Test
    public void test2_toMIPS() throws Exception {
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
                "                   # affichage de l'expression\n" +
                "                   # Chargement immédiat d'une constante entière\n"+
                "    li $v0, 4\n"+
                "    move $a0, $v0\n" +
                "    li $v0, 1\n" +
                "    syscall\n" +
                "    li $v0, 4      # retour à la ligne\n" +
                "    la $a0, finLigne\n" +
                "    syscall\n"+
                "end :\n" +
                "    li $v0, 10\n" +
                "    syscall\n" ;
        assertEquals("Code attendu != code fourni",code,codeAttendu);
    }

    @org.junit.Test
    public void test3_toMIPS() throws Exception {
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
                "                   # Chargement immédiat d'une constante entière\n"+
                "    li $v0, 4\n"+
                "end :\n" +
                "    li $v0, 10\n" +
                "    syscall\n" ;
        assertEquals("Code attendu != code fourni",code,codeAttendu);
    }

    @org.junit.Test
    public void test4_toMIPS() throws Exception {
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
                "                   # affichage de l'expression\n" +
                "                   # Chargement immédiat d'une constante entière\n"+
                "    li $v0, 4\n"+
                "    move $a0, $v0\n" +
                "    li $v0, 1\n" +
                "    syscall\n" +
                "    li $v0, 4      # retour à la ligne\n" +
                "    la $a0, finLigne\n" +
                "    syscall\n"+
                "end :\n" +
                "    li $v0, 10\n" +
                "    syscall\n" ;
        assertEquals("Code attendu != code fourni",code,codeAttendu);
    }
}