abstract public class PojazdSilnikowy extends Pojazd {
    private String vin;
    private String nrRejestracyjny;
    private double pojemnoscSilnika;
    private int liczbaMiejsc;
    private String paliwo;
    private double przebieg;

    public PojazdSilnikowy(String marka, String model, int rokProdukcji, String kolor, double waga, double cenaBazowa, String status, String wymaganeUprawnienia, String vin, String nrRejestracyjny, double pojemnoscSilnika, int liczbaMiejsc, String paliwo, double przebieg) {
        super(marka, model, rokProdukcji, kolor, waga, cenaBazowa, status, wymaganeUprawnienia);
        this.vin = vin;
        this.nrRejestracyjny = nrRejestracyjny;
        this.pojemnoscSilnika = pojemnoscSilnika;
        this.liczbaMiejsc = liczbaMiejsc;
        this.paliwo = paliwo;
        this.przebieg = przebieg;
    }
}
