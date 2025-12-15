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

    public void zalogujSie() {
        //tutaj tresc metody (czytaj jakies wybranie odpowiedniego panelu w swingu i weryfikacja danych)
    }
}
