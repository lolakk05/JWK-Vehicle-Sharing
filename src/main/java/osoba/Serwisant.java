package osoba;

import zlecenieNaprawy.ZlecenieNaprawy;

import java.util.List;

public class Serwisant extends Pracownik {
    private String specjalizacja;
    private List<ZlecenieNaprawy> aktywneZlecenia;

    public void przyjmijZlecenie() {};
    public void zakonczNaprawe() {};

    public Serwisant(String imie, String nazwisko, String pesel, int wiek, String email, String haslo, String telefon, String dzial, String specjalizacja, List<ZlecenieNaprawy> aktywneZlecenia) {
        super(imie, nazwisko, pesel, wiek, email, haslo, telefon, dzial);
        this.specjalizacja = specjalizacja;
        this.aktywneZlecenia = aktywneZlecenia;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" | Specjalizacja: %s | Aktywne zlecenia: %d",
                specjalizacja,
                aktywneZlecenia != null ? aktywneZlecenia.size() : 0
        );
    }
}
