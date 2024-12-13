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

public class Vol {

    //Creation du fichier csv pour enregistrer les infos
    private static void ecrireCSV(List<Map<String, String>> vols, String filePath) throws IOException {
        FileWriter fch = new FileWriter(filePath);

        CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
                .setHeader(new String[]{"Code", "Départ", "Arrivé", "Date", "Heure"})
                .setDelimiter('|')
                .build();
        CSVPrinter printer = new CSVPrinter(fch, csvFormat);
        for (Map<String, String> vol : vols) {
            printer.printRecord(
                    vol.get("Date"),
                    vol.get("Départ"),
                    vol.get("Arrivé"),
                    vol.get("Code"),
                    vol.get("Heure")
            );
        }
        fch.close(); // Attention, fichier EN ECRITURE non fermé si exception
    }

    //Methode de lecture
    public static List<Map<String, String>> lireCSV(String filePath) throws IOException {

       // Reader in = new FileReader(getClass().getClassLoader().getResource("vols.csv").getFile());
        Reader in = new FileReader(filePath);

        CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
                .setHeader(new String[]{"Code", "Départ", "Arrivé", "Date", "Heure"})
                .setSkipHeaderRecord(true)
                .setDelimiter('|')
                .setIgnoreSurroundingSpaces(true)
                .build();

        Iterable<CSVRecord> records = csvFormat.parse(in);

        List<Map<String,String>> vols = new ArrayList<>();
        for (CSVRecord record : records) {
            Map<String,String> vol = new HashMap<>();
            vol.put("Code", record.get("Code"));
            vol.put("Départ", record.get("Départ"));
            vol.put("Arrivé", record.get("Arrivé"));
            vol.put("Date", record.get("Date"));
            vol.put("Heure", record.get("Heure"));
            vols.add(vol);
        }

        in.close();
        //System.out.println(vols);
        return vols;
    }

    public static void main(String[] args) throws IOException {
        String filePath = "./liste_vols.csv";
        List<Map<String, String>> vols = new ArrayList<>();

        Map<String, String> vol1 = new HashMap<>();
        vol1.put("Date", "2024-12-15");
        vol1.put("Dép", "JFK");
        vol1.put("Arriv", "LAX");
        vol1.put("Code", "AB123");
        vol1.put("Heure", "08:00");
        vols.add(vol1);

        Map<String, String> vol2 = new HashMap<>();
        vol2.put("Date", "2024-12-16");
        vol2.put("Départ", "SFO");
        vol2.put("Arrivé", "ORD");
        vol2.put("Code", "CD456");
        vol2.put("Heure", "12:00");
        vols.add(vol2);


        try {
            ecrireCSV(vols, filePath);
            System.out.println("Le fichier CSV a été écrit dans " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Le fichier CSV a créé!");

        //Lire le fichier CSV
        try {
            List<Map<String, String>> readVols = lireCSV(filePath);
            System.out.println("Voici les données du fichier CSV :");
            for (Map<String, String> vol : readVols) {
                System.out.println(vol);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
