package osoba;

import pojazd.*;

import java.util.ArrayList;
import java.util.Scanner;

import static pojazd.Pojazd.saveVehicles;

public class Administrator extends Pracownik {
    public void przydzielNaprawe() {};
    public void dodajPojazd() {};
    public void usunPojazd() {};

    public Administrator(String imie, String nazwisko, String pesel, int wiek, String email, String haslo, String telefon, double pensja, String dzial) {
        super(imie, nazwisko, pesel, wiek, email, haslo, telefon, pensja, dzial);
    }

    @Override
    public String toString() {
        return super.toString() + " | [ADMINISTRATOR]";
    }

    public static void addVehicle(ArrayList<Pojazd> pojazdy) {
        System.out.println("-------------------------");
        System.out.println("1. Samochód osobowy");
        System.out.println("2. Ciężarówka");
        System.out.println("3. Motocykl");
        System.out.println("4. Rower");
        System.out.println("5. Hulajnoga elektryczna");
        System.out.println("-------------------------");

        System.out.print("Podaj nr typu pojazdu: ");
        Scanner sc = new Scanner(System.in);
        int type = sc.nextInt();
        sc.nextLine();

        switch (type) {
            case 1:
                System.out.print("Marka: ");
                String marka = sc.nextLine();
                System.out.print("Model: ");
                String model = sc.nextLine();
                System.out.print("Rok produkcji: ");
                int rokProdukcji = sc.nextInt();
                sc.nextLine();
                System.out.print("Kolor: ");
                String kolor = sc.nextLine();
                System.out.print("Waga: ");
                double waga = sc.nextDouble();
                sc.nextLine();
                System.out.print("CenaBazowa: ");
                double cenaBazowa = sc.nextDouble();
                sc.nextLine();
                System.out.print("Status: ");
                String status = sc.nextLine();
                System.out.print("WymaganeUprawnienia: ");
                String wymaganeUprawnienia = sc.nextLine();
                System.out.print("Nr VIN: ");
                String vin =  sc.nextLine();
                System.out.print("Nr rejestracyjny: ");
                String nrRejestracyjny = sc.nextLine();
                System.out.print("Pojemność silnika: ");
                double pojemnoscSilnika = sc.nextDouble();
                sc.nextLine();
                System.out.print("Liczba miejsc: ");
                int liczbaMiejsc = sc.nextInt();
                sc.nextLine();
                System.out.print("Paliwo: ");
                String paliwo = sc.nextLine();
                System.out.print("Przebieg: ");
                double przebieg = sc.nextDouble();
                sc.nextLine();
                System.out.print("Nadwozie: ");
                String nadwozie = sc.nextLine();
                System.out.print("Ilość drzwi: ");
                int iloscDrzwi = sc.nextInt();
                sc.nextLine();

                pojazdy.add(new SamochodOsobowy(marka, model, rokProdukcji, kolor, waga, cenaBazowa, status, wymaganeUprawnienia, vin, nrRejestracyjny, pojemnoscSilnika, liczbaMiejsc, paliwo, przebieg, nadwozie, iloscDrzwi));
                saveVehicles(pojazdy);
                break;

            case 2:
                System.out.print("Marka: ");
                marka = sc.nextLine();
                System.out.print("Model: ");
                model = sc.nextLine();
                System.out.print("Rok produkcji: ");
                rokProdukcji = sc.nextInt();
                sc.nextLine();
                System.out.print("Kolor: ");
                kolor = sc.nextLine();
                System.out.print("Waga: ");
                waga = sc.nextDouble();
                sc.nextLine();
                System.out.print("CenaBazowa: ");
                cenaBazowa = sc.nextDouble();
                sc.nextLine();
                System.out.print("Status: ");
                status = sc.nextLine();
                System.out.print("WymaganeUprawnienia: ");
                wymaganeUprawnienia = sc.nextLine();
                System.out.print("Nr VIN: ");
                vin =  sc.nextLine();
                System.out.print("Nr rejestracyjny: ");
                nrRejestracyjny = sc.nextLine();
                System.out.print("Pojemność silnika: ");
                pojemnoscSilnika = sc.nextDouble();
                sc.nextLine();
                System.out.print("Liczba miejsc: ");
                liczbaMiejsc = sc.nextInt();
                sc.nextLine();
                System.out.print("Paliwo: ");
                paliwo = sc.nextLine();
                System.out.print("Przebieg: ");
                przebieg = sc.nextDouble();
                sc.nextLine();
                System.out.print("Ładowność: ");
                double ladownosc = sc.nextDouble();
                sc.nextLine();
                System.out.print("Ilość osi: ");
                int iloscOsi = sc.nextInt();
                sc.nextLine();

                pojazdy.add(new Ciezarowka(marka, model, rokProdukcji, kolor, waga, cenaBazowa, status, wymaganeUprawnienia, vin, nrRejestracyjny, pojemnoscSilnika,liczbaMiejsc, paliwo, przebieg, ladownosc, iloscOsi));
                saveVehicles(pojazdy);
                break;

            case 3:
                System.out.print("Marka: ");
                marka = sc.nextLine();
                System.out.print("Model: ");
                model = sc.nextLine();
                System.out.print("Rok produkcji: ");
                rokProdukcji = sc.nextInt();
                sc.nextLine();
                System.out.print("Kolor: ");
                kolor = sc.nextLine();
                System.out.print("Waga: ");
                waga = sc.nextDouble();
                sc.nextLine();
                System.out.print("CenaBazowa: ");
                cenaBazowa = sc.nextDouble();
                sc.nextLine();
                System.out.print("Status: ");
                status = sc.nextLine();
                System.out.print("WymaganeUprawnienia: ");
                wymaganeUprawnienia = sc.nextLine();
                System.out.print("Nr VIN: ");
                vin =  sc.nextLine();
                System.out.print("Nr rejestracyjny: ");
                nrRejestracyjny = sc.nextLine();
                System.out.print("Pojemność silnika: ");
                pojemnoscSilnika = sc.nextDouble();
                sc.nextLine();
                System.out.print("Liczba miejsc: ");
                liczbaMiejsc = sc.nextInt();
                sc.nextLine();
                System.out.print("Paliwo: ");
                paliwo = sc.nextLine();
                System.out.print("Przebieg: ");
                przebieg = sc.nextDouble();
                sc.nextLine();
                System.out.print("Czy ma kufry [true/false]: ");
                boolean czyMaKufry = sc.nextBoolean();
                System.out.print("Typ: ");
                String typ = sc.nextLine();

                pojazdy.add(new Motocykl(marka, model, rokProdukcji, kolor, waga, cenaBazowa, status, wymaganeUprawnienia, vin, nrRejestracyjny, pojemnoscSilnika,liczbaMiejsc, paliwo, przebieg, czyMaKufry, typ));
                saveVehicles(pojazdy);
                break;

            case 4:
                System.out.print("Marka: ");
                marka = sc.nextLine();
                System.out.print("Model: ");
                model = sc.nextLine();
                System.out.print("Rok produkcji: ");
                rokProdukcji = sc.nextInt();
                sc.nextLine();
                System.out.print("Kolor: ");
                kolor = sc.nextLine();
                System.out.print("Waga: ");
                waga = sc.nextDouble();
                sc.nextLine();
                System.out.print("CenaBazowa: ");
                cenaBazowa = sc.nextDouble();
                sc.nextLine();
                System.out.print("Status: ");
                status = sc.nextLine();
                System.out.print("WymaganeUprawnienia: ");
                wymaganeUprawnienia =  sc.nextLine();
                System.out.print("Rozmiar kół: ");
                int rozmiarKol = sc.nextInt();
                sc.nextLine();
                System.out.print("Typ: ");
                typ = sc.nextLine();

                pojazdy.add(new Rower(marka, model, rokProdukcji, kolor, waga, cenaBazowa, status, wymaganeUprawnienia, rozmiarKol, typ));
                saveVehicles(pojazdy);
                break;

            case 5:
                System.out.print("Marka: ");
                marka = sc.nextLine();
                System.out.print("Model: ");
                model = sc.nextLine();
                System.out.print("Rok produkcji: ");
                rokProdukcji = sc.nextInt();
                sc.nextLine();
                System.out.print("Kolor: ");
                kolor = sc.nextLine();
                System.out.print("Waga: ");
                waga = sc.nextDouble();
                sc.nextLine();
                System.out.print("CenaBazowa: ");
                cenaBazowa = sc.nextDouble();
                sc.nextLine();
                System.out.print("Status: ");
                status = sc.nextLine();
                System.out.print("WymaganeUprawnienia: ");
                wymaganeUprawnienia =  sc.nextLine();
                System.out.print("Pojemność baterii: ");
                int pojemnoscBaterii = sc.nextInt();
                sc.nextLine();
                System.out.print("Zasięg km: ");
                int zasiegKm = sc.nextInt();
                sc.nextLine();
                System.out.print("Maksymalna prędkość: ");
                double maxPredkosc = sc.nextDouble();
                sc.nextLine();

                pojazdy.add(new HulajnogaElektryczna(marka, model, rokProdukcji, kolor, waga, cenaBazowa, status, wymaganeUprawnienia, pojemnoscBaterii, zasiegKm, maxPredkosc));
                saveVehicles(pojazdy);
                break;
        }
    }
}
