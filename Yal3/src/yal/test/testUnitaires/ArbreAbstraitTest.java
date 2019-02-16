package yal.test.testUnitaires;

import org.junit.Test;
import yal.analyse.tds.entree.EntreeVariable;
import yal.analyse.tds.TDS;
import yal.analyse.tds.symbole.SymboleVariable;
import yal.arbre.BlocDInstructions;
import yal.arbre.Programme;
import yal.arbre.expressions.ConstanteEntiere;
import yal.arbre.expressions.Variable;
import yal.arbre.expressions.expressionBinaire.expressionArithmetique.Addition;
import yal.arbre.expressions.expressionBinaire.expressionArithmetique.ExpressionArithmetiqueBinaire;
import yal.arbre.expressions.expressionBinaire.expressionLogique.Different;
import yal.arbre.expressions.expressionBinaire.expressionLogique.Et;
import yal.arbre.expressions.expressionBinaire.expressionLogique.Inferieur;
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
        int depl = instance.creerDeplacement();
        instance.ajouter(new EntreeVariable("a",0,2),new SymboleVariable(depl,"entier"));
        // Instructions
        BlocDInstructions b = new BlocDInstructions(1);
        b.ajouter(new Lire(new Variable(2,"a"),2));
        b.verifier();
    }

    @org.junit.Test
    public void affectation_variable_declarer_verifier() throws Exception {
        // Table des symboles
        TDS instance = TDS.getInstance();
        int depl = instance.creerDeplacement();
        instance.ajouter(new EntreeVariable("a",0,1),new SymboleVariable(instance.creerDeplacement(),"entier"));
        depl = instance.creerDeplacement();
        instance.ajouter(new EntreeVariable("b",0,1),new SymboleVariable(instance.creerDeplacement(),"entier"));
        // Instructions
        BlocDInstructions b = new BlocDInstructions(1);
        b.ajouter(new AffectationSimple(2,new Variable(2,"b"),new Variable(2,"a")));
        b.verifier();
    }

    @org.junit.Test
    public void affectation_constante_verifier() throws Exception {
        // Table des symboles
        TDS instance = TDS.getInstance();
        int depl = instance.creerDeplacement();
        instance.ajouter(new EntreeVariable("a",0,1),new SymboleVariable(instance.creerDeplacement(),"entier"));
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
        int depl = instance.creerDeplacement();
        instance.ajouter(new EntreeVariable("b",0,1),new SymboleVariable(instance.creerDeplacement(),"entier"));
        // Instructions
        BlocDInstructions b = new BlocDInstructions(1);
        b.ajouter(new AffectationSimple(2,new Variable(2,"b"),new Variable(2,"a")));
        b.verifier();
    }

    @org.junit.Test
    public void ecriture_variable_declarer_verifier() throws Exception {
        // Table des symboles
        TDS instance = TDS.getInstance();
        int depl = instance.creerDeplacement();
        instance.ajouter(new EntreeVariable("a",0,1),new SymboleVariable(instance.creerDeplacement(),"entier"));
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

    @org.junit.Test (expected = AnalyseSemantiqueException.class)
    public void declarer_variable_deja_declare(){
        TDS instance = TDS.getInstance();
        instance.ajouter(new EntreeVariable("b",0,1),new SymboleVariable(instance.creerDeplacement(),"entier"));
        instance.ajouter(new EntreeVariable("b",0,1),new SymboleVariable(instance.creerDeplacement(),"entier"));
    }

    @Test (expected = AnalyseSemantiqueException.class)
    public void operande_equivalent(){
        BlocDInstructions b = new BlocDInstructions(2);
        b.ajouter(new Ecrire(new Different(2,new ConstanteEntiere("5",2),
                new Different(2,new ConstanteEntiere("5",2),new ConstanteEntiere("2",2))),2));
        Programme p = new Programme(1,b);
        p.verifier();
    }

    @Test (expected = AnalyseSemantiqueException.class)
    public void operande_entier(){
        BlocDInstructions b = new BlocDInstructions(2);
        b.ajouter(new Inferieur(2,new ConstanteEntiere("5",2),
                new Different(2,new ConstanteEntiere("5",2),new ConstanteEntiere("2",2))));
        Programme p = new Programme(1,b);
        p.verifier();
    }

    @Test (expected = AnalyseSemantiqueException.class)
    public void operande_booleen(){
        BlocDInstructions b = new BlocDInstructions(2);
        b.ajouter(new Et(2,new ConstanteEntiere("5",2),
                new Different(2,new ConstanteEntiere("5",2),new ConstanteEntiere("2",2))));
        Programme p = new Programme(1,b);
        p.verifier();
    }

    @Test (expected = AnalyseSemantiqueException.class)
    public void expression_arithmetique(){
        BlocDInstructions b = new BlocDInstructions(2);
        b.ajouter(new Addition(2,new ConstanteEntiere("5",2),
                new Different(2,new ConstanteEntiere("5",2),new ConstanteEntiere("2",2))));
        Programme p = new Programme(1,b);
        p.verifier();
    }

    @Test (expected = AnalyseSemantiqueException.class)
    public void affectation_logique(){
        TDS tds = TDS.getInstance();
        tds.ajouter(new EntreeVariable("a",0,2), new SymboleVariable(tds.creerDeplacement(),"entier"));
        BlocDInstructions b = new BlocDInstructions(2);
        b.ajouter(new AffectationSimple(2, new Different(2,new ConstanteEntiere("5",2),
                new ConstanteEntiere("2",2)),new Variable(2,"a")));
        Programme p = new Programme(1,b);
        p.verifier();
    }
}