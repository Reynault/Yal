package yal.arbre;

public class GestionnaireNombres {

    private int num_condition;
    private int num_iteration;

    private static GestionnaireNombres instance = new GestionnaireNombres();
    private GestionnaireNombres(){
        num_condition = 0;
        num_iteration = 0;
    }
    public static GestionnaireNombres getInstance(){
        return instance;
    }

    public int nouvelleIteration(){
        num_iteration ++;
        return num_iteration;
    }

    public int nouvelleCondition(){
        num_condition ++;
        return num_condition;
    }
}