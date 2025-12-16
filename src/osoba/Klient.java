package osoba;

import wypozyczenie.Wypozyczenie;
import java.util.ArrayList;
import java.util.List;

public class Klient extends Osoba {
    private String numerPrawaJazdy;
    private List<String> kategoria;
    private List<Wypozyczenie> wypozyczenia;
    private double saldo;

    public Klient(String imie, String nazwisko, String pesel, int wiek, String email, String haslo, String telefon, String numerPrawaJazdy, List<String> kategoria, double saldo) {
        super(imie, nazwisko, pesel, wiek, email, haslo, telefon);
        this.numerPrawaJazdy = numerPrawaJazdy;
        this.kategoria = kategoria;
        this.wypozyczenia = new ArrayList<>();
        this.saldo = saldo;
    }

    public void wypozycz() {
        //logika wypozyczenia
    }

    public void wplacPieniadze() {
        //logika wplaty pieniazkow
    }
}
