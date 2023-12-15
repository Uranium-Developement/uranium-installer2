package fr.uranium;

import java.io.File;

public class minecraftFolder {
    public static String pathToMinecraft() {
        String os = System.getProperty("os.name").toLowerCase();
        String home = System.getProperty("user.home");

        String dossierMinecraft = ".minecraft";

        if (os.contains("win")) {
            // Système d'exploitation Windows
            return home + "\\AppData\\Roaming\\" + dossierMinecraft;
        } else if (os.contains("nix") || os.contains("nux") || os.contains("mac")) {
            // Système d'exploitation Unix/Linux/Mac
            return home + "/." + dossierMinecraft;
        } else {
            // Système d'exploitation inconnu, gestion par défaut
            return home + "/" + dossierMinecraft;
        }
    }
}