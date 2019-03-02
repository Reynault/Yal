package yal.arbre;

import yal.analyse.tds.TDS;
import yal.arbre.instructions.Retourne;

public class Fonction extends ArbreAbstrait{
    private BlocDInstructions bloc;
    private Retourne re;
    private int numeroFonction;
    private int numBloc;
    private int deplacements;
    /**
     * Constructeur
     *
     * @param n numéro de la ligne
     * @param numero le numéro de la fonction
     * @param bloc le bloc d'instructions
     */
    public Fonction(int n, int numero, BlocDInstructions bloc, Retourne retourne) {
        super(n);
        numeroFonction = numero;
        this.re = retourne;
        this.bloc = bloc;
    }

    @Override
    public void verifier() {
        TDS instance = TDS.getInstance();
        instance.entreBlockVerif();
        numBloc = GestionnaireNombres.getInstance().getCompteur_blocs();
        deplacements = instance.getDeplacement();
        bloc.verifier();
        re.verifier();
        re.setDeplacement(deplacements);
        instance.sortieBlockVerif();
    }

    @Override
    public String toMIPS() {
        StringBuilder sb = new StringBuilder();
        GestionnaireNombres gn = GestionnaireNombres.getInstance();
        // Étiquette de la fonction : identifiant plus numéro de bloc
        sb.append("FONC"+numeroFonction+":\n");
        // On empile l'adresse de retour
        sb.append("\rsw $ra, $sp\n");
        sb.append("\raddi $sp, $sp, -4\n");
        // On empile la base du bloc d'avant : Chainage dynamique
        sb.append("\rsw $s7, 0($sp)\n");
        sb.append("\raddi $sp, $sp, -4\n");
        // On empile le numéro de bloc
        sb.append("\rli $t8, "+numBloc+"\n");
        sb.append("\rsw $t8, 0($sp)");
        sb.append("\raddi $sp, $sp, -4\n");
        // On met à jour cette base
        sb.append("\rmove $s7, $sp\n");
        // On initialise les variables du bloc
        sb.append("\raddi $sp, $sp, "+deplacements+"\n");
        // Instructions du bloc
        sb.append(bloc.toMIPS());
        // Return
        re.toMIPS();
        return sb.toString();
    }
}
