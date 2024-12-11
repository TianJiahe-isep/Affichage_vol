package fr.isep.tian;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LectureFichier {public static void main(String[] args) {
    String filePath = "./.gitignore";
    try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
    } catch (IOException e) {
        System.err.println("Erreur lors de la lecture du fichier : " +
                e.getMessage());
    }
}
}
