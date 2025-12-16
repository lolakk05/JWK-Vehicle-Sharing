package osoba;

abstract public class Osoba {
    private String imie;
    private String nazwisko;
    private String pesel;
    private int wiek;
    private String email;
    private String haslo;
    private String telefon;

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
}
