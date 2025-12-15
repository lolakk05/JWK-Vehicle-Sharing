package Osoba;

public class Administrator extends Pracownik {
    public void przydzielNaprawe() {};
    public void dodajPojazd() {};
    public void usunPojazd() {};

    public Administrator(String imie, String nazwisko, String pesel, int wiek, String email, String haslo, String telefon, double pensja, String dzial) {
        super(imie, nazwisko, pesel, wiek, email, haslo, telefon, pensja, dzial);
    }
}
