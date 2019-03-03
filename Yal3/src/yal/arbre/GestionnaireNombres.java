package yal.arbre;

/**
 * Class singleton qui gère l'incrémentation des nombres utilisés
 * pour les structures mips (labels) et pour le nombre de blocs
 */
public class GestionnaireNombres {

    private int num_fonction;
    private int num_condition;
    private int num_iteration;
    private int num_expression;
    private int num_ecrire;
    private int id_bloc;
    private int compteur_blocs;

    /**
     * Instance unique
     */
    private static GestionnaireNombres instance = new GestionnaireNombres();

    /**
     * Constructeur
     */
    private GestionnaireNombres(){
        num_condition = 0;
        num_expression = 0;
        num_iteration = 0;
        num_ecrire = 0;
        num_fonction = 0;
        compteur_blocs = 0;
        id_bloc = 0;
    }
    public static GestionnaireNombres getInstance(){
        return instance;
    }

    public int getIdBlock(){
        return id_bloc;
    }

    public void incrementerIdBlock(){
        id_bloc++;
    }

    public void resetIdBlock(){
        id_bloc = 0;
    }

    public int nouvelleIteration(){
        num_iteration ++;
        return num_iteration;
    }

    public int nouvelleCondition(){
        num_condition ++;
        return num_condition;
    }

    public int nouvelleExpression(){
        num_expression++;
        return num_expression;
    }

    public int nouvelleEcriture(){
        num_ecrire++;
        return num_ecrire;
    }

    public int getCompteur_blocs() {
        return compteur_blocs;
    }

    public void incrementer(){
        compteur_blocs++;
    }

    public void decrementer(){
        compteur_blocs--;
    }

    public void resetBloc(){
        compteur_blocs = 0;
    }

    public int nouvelleFonction(){
        num_fonction++;
        return num_fonction;
    }
}
