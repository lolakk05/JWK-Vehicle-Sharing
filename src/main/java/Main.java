import osoba.Klient;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import pojazd.HulajnogaElektryczna;
import pojazd.Pojazd;
import pojazd.SamochodOsobowy;
import strategia.StrategiaGodzinowa;
import strategia.StrategiaMinutowa;
import wypozyczenie.Wypozyczenie;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Date;
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

        // a'la test strategii (?)
        // utworzenie pojazdów
        Pojazd bmw_car = new SamochodOsobowy("BMW", "320i", 2022, "Czarny", 1600, 150.00, "Dostępny", "B", "VIN123", "DW 55555", 2.0, 5, "Benzyna", 50000, "Sedan", 4);

        // Cena bazowa Hulajnogi: 0.80 PLN (traktujemy to jako cenę za minutę)
        Pojazd xiaomi_scooter = new HulajnogaElektryczna("Xiaomi", "Pro 2", 2023, "Szary", 14, 0.80, "Dostępny", "Brak", 474, 45, 25.0);

        System.out.println(bmw_car);
        System.out.println(xiaomi_scooter);
        System.out.println();

        // symulacja dat
        Date teraz = new Date();

        // Symulacja: Oddajemy auto po 2 dniach i 3 godzinach, 51h
        Date zaDwaDni = new Date(teraz.getTime() + (51L * 60 * 60 * 1000));

        // Symulacja: Oddajemy hulajnogę po 30 minutach
        Date zaPolGodziny = new Date(teraz.getTime() + (30L * 60 * 1000));

        // A) Wypożyczenie Auta -> Strategia Dobowa
        // System wie, że 150 PLN to stawka za dobę
        Wypozyczenie wypozyczenieAuta = new Wypozyczenie(bmw_car, teraz, zaDwaDni, new StrategiaGodzinowa());

        // B) Wypożyczenie Hulajnogi -> Strategia Minutowa
        // System wie, że 0.80 PLN to stawka za minutę
        Wypozyczenie wypozyczenieHulajnogi = new Wypozyczenie(xiaomi_scooter, teraz, zaPolGodziny, new StrategiaMinutowa());

        System.out.println("--- RAPORT FINANSOWY ---");
        System.out.println(wypozyczenieAuta);
        System.out.println("-> Powinno wyjść 450.00 PLN (3 doby)");
        //dziala chyba
        System.out.println("\n" + wypozyczenieHulajnogi);
        System.out.println("-> Powinno wyjść 24.00 PLN");
        System.out.println();


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
