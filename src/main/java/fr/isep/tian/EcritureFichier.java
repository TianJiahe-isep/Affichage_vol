package fr.isep.tian;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class EcritureFichier {
    public static void main(String[] args) {
    String filePath = "./fichier.txt";
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath,true))) // `true` pour ajouter à la fin
    {
        writer.write("Ceci est un exemple de texte.");
        writer.newLine();
        writer.write("Une autre ligne de texte.");// Ajoute une nouvelle ligne writer.write("Une autre ligne de texte.");
    }
    catch (IOException e) {
        System.err.println("Erreur lors de l'écriture dans le fichier : " +
                e.getMessage());
    }
}

}