package yal.arbre.expressions;

public class AppelFonction extends Expression{

    private int numeroFonction;

    /**
     * Constructeur qui prend le numéro de la ligne en paramètre
     *
     * @param n numéro de la ligne
     */
    protected AppelFonction(int n) {
        super(n);
    }

    @Override
    public void verifier() {
        super.verifier();
    }

    @Override
    public String toMIPS() {
        return super.toMIPS();
    }

    @Override
    public boolean isArithmetique() {
        return true;
    }
}
