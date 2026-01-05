package app;

import osoba.*;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import pojazd.Pojazd;

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

        return true;
    }

//    public static void printMenu() {
//        System.out.println("------------------------");
//        System.out.println("1. Logowanie");
//        System.out.println("2. Rejestracja");
//        System.out.println("3. Dodaj pojazd");
//        System.out.println("4. Zapisz i wyjdź");
//        System.out.println("------------------------");
//    }
//
//    public static void processChoice(int choice){
//        switch(choice){
//            case 1:
//                Scanner userInput = new Scanner(System.in);
//                System.out.print("Email: ");
//                String email = userInput.nextLine();
//                System.out.print("Haslo: ");
//                String password = userInput.nextLine();
//                login(email, password);
//                break;
//            case 2:
//                registerClient(clients);
//                break;
//            case 3:
//                addVehicle(pojazdy);
//
//                System.out.println("Dostępne pojazdy:");
//                for(Pojazd pojazd : pojazdy) {
//                    System.out.println(pojazd);
//                }
//
//                break;
//            case 4:
//                System.exit(0);
//
//        }
//    }
//
//    public static void main(String[] args)  {
//        while (true) {
//            // wyswietlanie klientow
//            printClients(clients);
//            System.out.println();
//
//            printMenu();
//            Scanner userInput = new Scanner(System.in);
//            System.out.print("Wybierz opcje: ");
//            int choice = userInput.nextInt();
//            processChoice(choice);
//        }
//    }
}