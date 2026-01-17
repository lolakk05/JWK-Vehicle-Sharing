package pojazd;
import java.util.Objects;

abstract public class PojazdSilnikowy extends Pojazd {
    protected String vin;
    protected String nrRejestracyjny;
    protected double pojemnoscSilnika;
    protected int liczbaMiejsc;
    protected String paliwo;

    public PojazdSilnikowy(String marka, String model, int rokProdukcji, String kolor, double waga, double cenaBazowa, String status, String wymaganeUprawnienia, String vin, String nrRejestracyjny, double pojemnoscSilnika, int liczbaMiejsc, String paliwo) {
        super(marka, model, rokProdukcji, kolor, waga, cenaBazowa, status, wymaganeUprawnienia);
        this.vin = vin;
        this.nrRejestracyjny = nrRejestracyjny;
        this.pojemnoscSilnika = pojemnoscSilnika;
        this.liczbaMiejsc = liczbaMiejsc;
        this.paliwo = paliwo;
    }

    @Override
    public String toString() {
        return super.toString() + String.format("\nVIN: %s \nNr rejestracyjny: %s \nPojemność silnika: %.1f cm³ \nLiczba miejsc: %d \nPaliwo: %s",
                vin,
                nrRejestracyjny,
                pojemnoscSilnika,
                liczbaMiejsc,
                paliwo
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        PojazdSilnikowy that = (PojazdSilnikowy) o;
        return Objects.equals(vin, that.vin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), vin);
    }
}
