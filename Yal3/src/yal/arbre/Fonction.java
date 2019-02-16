package yal.arbre;

public class Fonction extends BlocDInstructions{
    private String idf;
    /**
     * Constructeur
     *
     * @param n numéro de la ligne
     * @param idf identifiant de la fonction
     */
    public Fonction(int n, String idf) {
        super(n);
        this.idf = idf;
    }

    @Override
    public void verifier() {
        super.verifier();
    }

    @Override
    public String toMIPS() {
        return super.toMIPS();
    }
}
