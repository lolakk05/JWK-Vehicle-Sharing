package pojazd;

public class Motocykl extends PojazdSilnikowy {
    private boolean czyMaKufry;
    private String typ;

    public Motocykl(String marka, String model, int rokProdukcji, String kolor, double waga, double cenaBazowa, String status, String wymaganeUprawnienia, String vin, String nrRejestracyjny, double pojemnoscSilnika, int liczbaMiejsc, String paliwo, double przebieg, boolean czyMaKufry, String typ) {
        super(marka, model, rokProdukcji, kolor, waga, cenaBazowa, status, wymaganeUprawnienia, vin, nrRejestracyjny, pojemnoscSilnika, liczbaMiejsc, paliwo, przebieg);
        this.czyMaKufry = czyMaKufry;
        this.typ = typ;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" | [MOTOCYKL] Typ: %s | Kufry: %s",
                typ,
                czyMaKufry ? "Tak" : "Nie"
        );
    }
}
