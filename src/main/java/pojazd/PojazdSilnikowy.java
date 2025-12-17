package pojazd;

abstract public class PojazdSilnikowy extends Pojazd {
    protected String vin;
    protected String nrRejestracyjny;
    protected double pojemnoscSilnika;
    protected int liczbaMiejsc;
    protected String paliwo;
    protected double przebieg;

    public PojazdSilnikowy(String marka, String model, int rokProdukcji, String kolor, double waga, double cenaBazowa, String status, String wymaganeUprawnienia, String vin, String nrRejestracyjny, double pojemnoscSilnika, int liczbaMiejsc, String paliwo, double przebieg) {
        super(marka, model, rokProdukcji, kolor, waga, cenaBazowa, status, wymaganeUprawnienia);
        this.vin = vin;
        this.nrRejestracyjny = nrRejestracyjny;
        this.pojemnoscSilnika = pojemnoscSilnika;
        this.liczbaMiejsc = liczbaMiejsc;
        this.paliwo = paliwo;
        this.przebieg = przebieg;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" | Rej: %s | Silnik: %.1fL %s | Przebieg: %.0f km | Miejsc: %d",
                nrRejestracyjny,
                pojemnoscSilnika,
                paliwo,
                przebieg,
                liczbaMiejsc
        );
    }
}
