import osoba.Osoba;
import osoba.Administrator;
import osoba.Serwisant;
import osoba.Klient;

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

    public static void login(String email, String password, ArrayList<Klient> clients) {
        for(Klient client : clients) {
            if(client.getEmail().equals(email) && client.getHaslo().equals(password)) {
                System.out.println("jesteś zwykłym pionkiem");
            }
        }
    }

    public static void registerClient(ArrayList<Klient> clients) {
        Scanner userInput = new Scanner(System.in);
        System.out.print("Imie: ");
        String name = userInput.nextLine();
        System.out.print("Nazwisko: ");
        String surname = userInput.nextLine();
        System.out.print("Pesel: ");
        String pesel = userInput.nextLine();
        System.out.print("Wiek: ");
        int age = Integer.parseInt(userInput.nextLine());
        System.out.print("Email: ");
        String email = userInput.nextLine();
        System.out.print("Haslo: ");
        String password = userInput.nextLine();
        System.out.print("Telefon: ");
        String phone = userInput.nextLine();
        System.out.print("Posiadasz prawo jazdy? [T/N]: ");
        String license = userInput.nextLine();
        String driverLicenseNumber = null;
        List<String> category = new ArrayList<>();
        switch (license) {
            case "T":
                System.out.print("Numer prawa jazdy: ");
                driverLicenseNumber = userInput.nextLine();
                System.out.print("Ile kategorii?: ");
                int categories = Integer.parseInt(userInput.nextLine());
                for(int i = 0; i < categories; i++) {
                    System.out.print("Kategoria: ");
                    category.add(userInput.nextLine());
                }
        }
        clients.add(new Klient(name, surname, pesel, age, email, password, phone, driverLicenseNumber, category, 0));

        File clientsFile = new File("data/clients.json");

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(clientsFile));
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(clients, writer);
            writer.close();
            System.out.println("Użytkownik zarejestrowany prawidłowo!");
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void printMenu() {
        System.out.println("------------------------");
        System.out.println("1. Logowanie");
        System.out.println("2. Rejestracja");
        System.out.println("3. Dodaj pojazd");
        System.out.println("4. Zapisz i wyjdź");
        System.out.println("------------------------");
    }

    public static void processChoice(int choice){
        switch(choice){
            case 1:
                Scanner userInput = new Scanner(System.in);
                System.out.print("Email: ");
                String email = userInput.nextLine();
                System.out.print("Haslo: ");
                String password = userInput.nextLine();
                login(email, password, clients);
                break;
            case 2:
                registerClient(clients);
                break;
            case 3:
                addVehicle(pojazdy);

                System.out.println("Dostępne pojazdy:");
                for(Pojazd pojazd : pojazdy) {
                    System.out.println(pojazd);
                }

                break;
            case 4:
                System.exit(0);

        }
    }

    public static void main(String[] args) {
        while (true) {
            // wyswietlanie klientow
            printClients(clients);
            System.out.println();

            printMenu();
            Scanner userInput = new Scanner(System.in);
            System.out.print("Wybierz opcje: ");
            int choice = userInput.nextInt();
            processChoice(choice);
        }
    }
}
