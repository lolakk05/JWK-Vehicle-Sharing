package app;

import frontend.MainFrame;
import osoba.*;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import pojazd.Pojazd;
import pojazd.SamochodOsobowy;

import javax.swing.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static osoba.Administrator.addVehicle;
import static pojazd.Pojazd.loadVehicles;
import static serialization.UserSerialize.*;

public class Main {
    public static ArrayList<Klient> clients = loadClients();
    public static ArrayList<Pojazd> pojazdy = loadVehicles();

    public static boolean login(String email, String password) {
        for (Klient client : clients) {
            if (client.getEmail().equals(email) && client.getHaslo().equals(password)) {
                Session.login(client);
                return true;
            }
        }
        return false;
    }

    public boolean registerClient(String name, String surname, String pesel, String ageStr,
                                  String email, String password, String phone,
                                  boolean hasLicense, String driverLicenseNumber, List<String> categories) throws Exception {

        int age;
        try {
            age = Integer.parseInt(ageStr);
        } catch (NumberFormatException e) {
            throw new Exception("Wiek musi być liczbą!");
        }

        PeselException.ValidatePesel(pesel);

        EmailException.ValidateEmail(email);

        if (!hasLicense) {
            categories.clear();
            driverLicenseNumber = null;
        }

        Klient newClient = new Klient(name, surname, pesel, age, email, password, phone, driverLicenseNumber, categories, 0);

        clients.add(newClient);

        try {
            saveClients();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return true;
    }

    public void saveClients() {
        File clientsFile = new File("data/clients.json");

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(clientsFile));
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(clients, writer);
            writer.close();
            System.out.println("Użytkownik zarejestrowany prawidłowo!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void startGUI() {
        new MainFrame(this);
    }

    public static void main(String[] args) {
        for(int i = 0; i < 50; i++ ) {
            pojazdy.add(new SamochodOsobowy(
                    "Toyota",           // marka
                    "Corolla",          // model
                    2023,               // rokProdukcji
                    "Srebrny",          // kolor
                    1350.0,             // waga
                    250.0,              // cenaBazowa
                    "wolny",            // status
                    "B",                // wymaganeUprawnienia
                    "TMJK1234567890",   // vin
                    "DW 12345",         // nrRejestracyjny
                    1.8,                // pojemnoscSilnika
                    5,                  // liczbaMiejsc
                    "Hybryda",          // paliwo
                    12500.0,            // przebieg
                    "Sedan",
                    4
            ));
        }
        for(Pojazd pojazd : pojazdy) {
            System.out.println(pojazd.getMarka());
        }
        SwingUtilities.invokeLater(() -> {
            new Main().startGUI();
        });
    }
}