package fr.uranium;

import java.io.*;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class downloadZip {

    public static void start() {
        String zipUrl = "http://burning-cardboard.fr/assets_and_files_of_tfc.zip";
        String outputDirectory = minecraftFolder.pathToMinecraft();

        try {
            downloadAndExtractZip(zipUrl, outputDirectory);
            System.out.println("Téléchargement et extraction réussis !");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void downloadAndExtractZip(String zipUrl, String outputDirectory) throws IOException {
        // Step 1: Téléchargement du fichier zip
        URL url = new URL(zipUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        try (InputStream inputStream = connection.getInputStream();
             FileOutputStream outputStream = new FileOutputStream(outputDirectory + "downloadedFile.zip")) {

            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
        }

        connection.disconnect();
        supprimerDossier(new File(outputDirectory+"tfc"+System.getProperty("file.separator")+"mods"));
        supprimerDossier(new File(outputDirectory+"tfc"+System.getProperty("file.separator")+"kubejs"));
        supprimerDossier(new File(outputDirectory+"tfc"+System.getProperty("file.separator")+"fancymenu_data"));
        supprimerDossier(new File(outputDirectory+"tfc"+System.getProperty("file.separator")+"fancymenu_setups"));


        // Step 2: Extraction du fichier zip
        try (ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(outputDirectory + "downloadedFile.zip"))) {
            ZipEntry entry;
            while ((entry = zipInputStream.getNextEntry()) != null) {
                String entryName = entry.getName();
                String filePath = outputDirectory + System.getProperty("file.separator") +entryName;

                if (entry.isDirectory()) {
                    new File(filePath).mkdirs();
                } else {
                    try (OutputStream fileOutputStream = new FileOutputStream(filePath)) {
                        byte[] buffer = new byte[4096];
                        int bytesRead;
                        while ((bytesRead = zipInputStream.read(buffer)) != -1) {
                            fileOutputStream.write(buffer, 0, bytesRead);
                        }
                    }
                }

                zipInputStream.closeEntry();
            }
        }catch (ConnectException e){

        }
    }
    public static void supprimerDossier(File dossier) {
        // Vérifiez si le dossier existe
        if (dossier.exists()) {
            // Liste des fichiers dans le dossier
            File[] fichiers = dossier.listFiles();

            // Supprimez tous les fichiers dans le dossier
            if (fichiers != null) {
                for (File fichier : fichiers) {
                    if (fichier.isDirectory()) {
                        // Appel récursif si le fichier est un sous-dossier
                        supprimerDossier(fichier);
                    } else {
                        fichier.delete(); // Supprime le fichier
                    }
                }
            }

            // Supprimez le dossier lui-même une fois que tous les fichiers sont supprimés
            dossier.delete();
            System.out.println("Dossier supprimé avec succès.");
        } else {
            System.out.println("Le dossier spécifié n'existe pas.");
        }
    }
}
