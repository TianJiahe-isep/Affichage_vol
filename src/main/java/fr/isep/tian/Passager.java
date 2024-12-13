package fr.isep.tian;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Passager {

    //Creation du fichier csv pour enregistrer les infos
    private static void ecrireCSV(List<Map<String, String>> passagers, String filePath) throws IOException {
        FileWriter fch = new FileWriter(filePath);

        CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
                .setHeader(new String[]{"Identifiant", "Nom", "Adresse", "Contact", "Passeport"})
                .setDelimiter('|')
                .build();
        CSVPrinter printer = new CSVPrinter(fch, csvFormat);
        for (Map<String, String> passager : passagers) {
            printer.printRecord(
                    passager.get("Identifiant"),
                    passager.get("Nom"),
                    passager.get("Adresse"),
                    passager.get("Contact"),
                    passager.get("Passeport")
            );
        }
        fch.close(); // Attention, fichier EN ECRITURE non fermé si exception
    }

    //Methode de lecture
    public static List<Map<String, String>> lireCSV(String filePath) throws IOException {

        // Reader in = new FileReader(getClass().getClassLoader().getResource("vols.csv").getFile());
        Reader in = new FileReader(filePath);

        CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
                .setHeader(new String[]{"Identifiant", "Nom", "Adresse", "Contact", "Passeport"})
                .setSkipHeaderRecord(true)
                .setDelimiter('|')
                .setIgnoreSurroundingSpaces(true)
                .build();

        Iterable<CSVRecord> records = csvFormat.parse(in);

        List<Map<String,String>> passagers = new ArrayList<>();
        for (CSVRecord record : records) {
            Map<String,String> passager = new HashMap<>();
            passager.put("Identifiant", record.get("Identifiant"));
            passager.put("Nom", record.get("Nom"));
            passager.put("Adresse", record.get("Adresse"));
            passager.put("Contact", record.get("Contact"));
            passager.put("Passeport", record.get("Passeport"));
            passagers.add(passager);
        }

        in.close();
        //System.out.println(vols);
        return passagers;
    }

    public static void main(String[] args) throws IOException {
        String filePath = "./liste_passagers.csv";
        List<Map<String, String>> passagers = new ArrayList<>();

        Map<String, String> passager1 = new HashMap<>();
        passager1.put("Identifiant", "01");
        passager1.put("Nom", "Emily");
        passager1.put("Adresse", "1 Rue de Jean Jaures");
        passager1.put("Contact", "12 34 56 78 90");
        passager1.put("Passeport", "FR0000001");
        passagers.add(passager1);

        Map<String, String> passager2 = new HashMap<>();
        passager2.put("Identifiant", "02");
        passager2.put("Nom", "Michael");
        passager2.put("Adresse", "2 Rue de Jean Jaures");
        passager2.put("Contact", "12 34 56 78 91");
        passager2.put("Passeport", "FR0000002");
        passagers.add(passager2);


        Map<String, String> passager3 = new HashMap<>();
        passager3.put("Identifiant", "03");
        passager3.put("Nom", "James");
        passager3.put("Adresse", "3 Rue de Jean Jaures");
        passager3.put("Contact", "12 34 56 78 92");
        passager3.put("Passeport", "FR0000003");
        passagers.add(passager3);

        Map<String, String> passager4 = new HashMap<>();
        passager4.put("Identifiant", "04");
        passager4.put("Nom", "Mia");
        passager4.put("Adresse", "4 Rue de Jean Jaures");
        passager4.put("Contact", "12 34 56 78 93");
        passager4.put("Passeport", "FR0000004");
        passagers.add(passager4);

        try {
            ecrireCSV(passagers, filePath);
            System.out.println("Le fichier CSV a été écrit dans " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Le fichier CSV a créé!");

        //Lire le fichier CSV
        try {
            List<Map<String, String>> readPassagers = lireCSV(filePath);
            System.out.println("Voici les données du fichier CSV :");
            for (Map<String, String> vol : readPassagers) {
                System.out.println(vol);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
