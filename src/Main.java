import osoba.Osoba;
import osoba.Administrator;
import osoba.Serwisant;
import osoba.Klient;

import serialization.UserSerialize;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public void login(String email, String password, ArrayList<Osoba> osoby) {
        for(Osoba osoba : osoby) {
            if(osoba.getEmail().equals(email) && osoba.getHaslo().equals(password)) {
                if(osoba instanceof Administrator) {
                    System.out.println("jupi jesteś admin");
                    //tutaj pewnie jakies wywolanie metody ktora bedzie miala panel admin
                }
                else if (osoba instanceof Serwisant) {
                    System.out.println("serwisuj mi turbo w moim 1.9tdi");
                }
                else {
                    System.out.println("jesteś zwykłym pionkiem");
                }
            }
        }
    }

    public void register(ArrayList<Osoba> users) {
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
        System.out.print("Posiadasz prawo jazdy?: ");
        String license = userInput.nextLine();
        String driverLicenseNumber = null;
        List<String> category = new ArrayList<>();
        switch (license) {
            case "Tak":
                System.out.print("Numer prawa jazdy: ");
                driverLicenseNumber = userInput.nextLine();
                System.out.print("Ile kategorii?: ");
                int categories = Integer.parseInt(userInput.nextLine());
                for(int i = 0; i < categories; i++) {
                    System.out.print("Kategoria: ");
                    category.add(userInput.nextLine());
                }
        }
        users.add(new Klient(name, surname, pesel, age, email, password, phone, driverLicenseNumber, category, 0));
    }

    public void main(String[] args) {
        ArrayList<Osoba> users = new ArrayList<>();
        register(users);
        Scanner userInput = new Scanner(System.in);
        System.out.print("Email: ");
        String email = userInput.nextLine();
        System.out.print("Haslo: ");
        String password = userInput.nextLine();
        login(email, password, users);
    }
}
