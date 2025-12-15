package Osoba;

public class Pracownik extends Osoba {
    private double pensja;
    private String dzial;

    public Pracownik(String imie, String nazwisko, String pesel, int wiek, String email, String haslo, String telefon, double pensja, String dzial) {
        super(imie, nazwisko, pesel, wiek, email, haslo, telefon);
        this.pensja = pensja;
        this.dzial = dzial;
    }
}
