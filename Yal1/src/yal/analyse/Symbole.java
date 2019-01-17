package yal.analyse;

public class Symbole {
    private int deplacement;
    private String type;

    public Symbole(int deplacement, String type) {
        this.deplacement = deplacement;
        this.type = type;
    }

    public int getDeplacement() {
        return deplacement;
    }

    public void setDeplacement(int deplacement) {
        this.deplacement = deplacement;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
