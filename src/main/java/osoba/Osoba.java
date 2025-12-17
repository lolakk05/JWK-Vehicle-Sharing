package osoba;

abstract public class Osoba {
    protected String imie;
    protected String nazwisko;
    protected String pesel;
    protected int wiek;
    protected String email;
    protected String haslo;
    protected String telefon;

    public Osoba(String imie, String nazwisko, String pesel, int wiek, String email, String haslo, String telefon) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.pesel = pesel;
        this.wiek = wiek;
        this.email = email;
        this.haslo = haslo;
        this.telefon = telefon;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHaslo() {
        return haslo;
    }

    public void setHaslo(String haslo) {
        this.haslo = haslo;
    }

    @Override
    public String toString() {
        return String.format("%s %s (lat: %d) | PESEL: %s | Email: %s | Tel: %s",
                imie,
                nazwisko,
                wiek,
                pesel,
                email,
                telefon
        );
    }
}
