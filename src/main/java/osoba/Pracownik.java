package osoba;

abstract public class Pracownik extends Osoba {
    protected double pensja;
    protected String dzial;

    public Pracownik(String imie, String nazwisko, String pesel, int wiek, String email, String haslo, String telefon, String dzial) {
        super(imie, nazwisko, pesel, wiek, email, haslo, telefon);
        this.dzial = dzial;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" | Dział: %s | Pensja: %.2f PLN",
                dzial,
                pensja
        );
    }
}
