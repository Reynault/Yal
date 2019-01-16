package yal.test.simulateur;

import yal.Yal;

import java.io.File;

public class mainSimulateur {
    public static void main(String[] args) {
        int nbFiles = new File("sources/").listFiles().length;
        for (int i = 0; i < nbFiles; i++) {
            Yal.main(new String[]{"test" + i + ".yal"});
        }

    }
}
