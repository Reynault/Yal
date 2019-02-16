package yal.arbre;

import yal.analyse.tds.TDS;

public class Fonction extends ArbreAbstrait{
    private BlocDInstructions bloc;
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
    public Fonction(int n, int numero, BlocDInstructions bloc) {
        super(n);
        numeroFonction = numero;
        this.bloc = bloc;
    }

    @Override
    public void verifier() {
        TDS instance = TDS.getInstance();
        instance.entreBlockVerif();
        numBloc = GestionnaireNombres.getInstance().getCompteur_blocs();
        deplacements = instance.getDeplacement();
        bloc.verifier();
        instance.sortieBlockVerif();
    }

    @Override
    public String toMIPS() {
        StringBuilder sb = new StringBuilder();
        GestionnaireNombres gn = GestionnaireNombres.getInstance();
        // Étiquette de la fonction : identifiant plus numéro de bloc
        sb.append("FONC"+numeroFonction+":\n");
        // On empile l'adresse de retour
        sb.append("\rsw $ra, $sp");
        sb.append("\raddi $sp, $sp, -4");
        // On empile la base du bloc d'avant : Chainage dynamique
        sb.append("\rsw $v7, 0($sp)");
        sb.append("\raddi $sp, $sp, -4");
        // On empile la base du bloc englobant : Chainage Statique

        // On met à jour cette base
        sb.append("\rmove $s7, $sp");
        // On initialise les variables du bloc
        sb.append("\raddi $sp, $sp, "+deplacements);
        // Instructions du bloc
        sb.append(bloc.toMIPS());
        // On revient à l'ancienne base
        sb.append("\rlw $s7, 8($s7)");
        // Mise à jour du pointeur de la pile
        int d = (-deplacements)+12;
        sb.append("\raddi $sp, $sp, "+d);
        // Stockage de la valeur de retour
        sb.append("\rsw $v0, 4($sp)");
        // récupération de l'adresse de retour
        sb.append("\rlw $ra, $sp");
        // On revient à l'adresse de retour
        sb.append("\rjz $ra");
        return sb.toString();
    }
}
