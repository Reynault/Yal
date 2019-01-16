package yal.test.simulateur;

import yal.Yal;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class mainSimulateur {
    public static void main(String[] args) {
        int nbFiles = new File("sources/").listFiles().length;
        for (int i = 0; i < nbFiles; i++) {
            Yal.main(new String[]{"sources/test" + i + ".yal"});
        }
        for (int i = 0; i < nbFiles; i++) {
            try {
                Files.move(Paths.get("sources/test" + i + ".mips"), Paths.get("generer/test" + i + ".mips"), StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
