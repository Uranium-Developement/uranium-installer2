package fr.uranium;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class downloadZip {

    public static void main() {
        String zipUrl = "http://burning-cardboard.duckdns.org/assets_and_files_of_uranium.zip";
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
        }
    }
}
