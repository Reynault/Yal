package yal.arbre;

import yal.analyse.tds.TDS;
import yal.arbre.instructions.Retourne;
import yal.exceptions.AnalyseSemantiqueException;

import java.util.ArrayList;

public class Fonction extends ArbreAbstrait{
    private BlocDInstructions bloc;
    private BlocDInstructions declaration;
    private int numeroFonction;
    private int numBloc;
    private int deplacements;
    private int nbparam;

    /**
     * Constructeur
     *
     * @param n numéro de la ligne
     * @param numero le numéro de la fonction
     * @param bloc le bloc d'instructions
     */
    public Fonction(int n, int numero, BlocDInstructions bloc, int nbparam, BlocDInstructions declaration) {
        super(n);
        this.declaration = declaration;
        numeroFonction = numero;
        this.bloc = bloc;
        this.nbparam = nbparam;
    }

    @Override
    public ArrayList<Retourne> get_retourne() {
        return null;
    }

    @Override
    public int get_nb_retourne() {
        return 0;
    }

    @Override
    public void verifier() {
        TDS instance = TDS.getInstance();
        instance.entreBlockVerif();
        numBloc = GestionnaireNombres.getInstance().getCompteur_blocs();
        deplacements = instance.getDeplacement();
        bloc.verifier();
        int nbRetourne = bloc.get_nb_retourne();
        ArrayList<Retourne> liste_retourne = bloc.get_retourne();
        if(nbRetourne == 0){
            throw new AnalyseSemantiqueException(noLigne, "Une fonction doit contenir au moins un retourne");
        }else{
            for (Retourne r : liste_retourne){
                r.setDeplacement(deplacements);
                r.setNbParam(nbparam);
            }
        }
        instance.sortieBlockVerif();
    }

    @Override
    public String toMIPS() {
        StringBuilder sb = new StringBuilder();
        GestionnaireNombres gn = GestionnaireNombres.getInstance();
        // Étiquette de la fonction : identifiant plus numéro de bloc
        sb.append("\tj FONCFIN"+numeroFonction+"\n");
        sb.append("FONC"+numeroFonction+":\n");
        // On empile l'adresse de retour
        sb.append("\tsw $ra, 0($sp)\n");
        sb.append("\taddi $sp, $sp, -4\n");
        // On empile la base du bloc d'avant : Chainage dynamique
        sb.append("\tsw $s7, 0($sp)\n");
        sb.append("\taddi $sp, $sp, -4\n");
        // On empile le numéro de bloc
        sb.append("\tli $t8, "+numBloc+"\n");
        sb.append("\tsw $t8, 0($sp)\n");
        sb.append("\taddi $sp, $sp, -4\n");
        // On met à jour cette base
        sb.append("\tmove $s7, $sp\n");
        // On initialise les variables du bloc
        sb.append("\taddi $sp, $sp, "+deplacements+"\n");
        //initialisation des variables/tableau
        sb.append(declaration.toMIPS());
        // Instructions du bloc
        sb.append(bloc.toMIPS());
        sb.append("\tFONCFIN"+numeroFonction+":\n");
        return sb.toString();
    }
}
