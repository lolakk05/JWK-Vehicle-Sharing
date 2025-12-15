package Pojazd;

public class Ciezarowka extends PojazdSilnikowy {
    private double ladownosc;
    private int iloscOsi;

    public Ciezarowka(String marka, String model, int rokProdukcji, String kolor, double waga, double cenaBazowa, String status, String wymaganeUprawnienia, String vin, String nrRejestracyjny, double pojemnoscSilnika, int liczbaMiejsc, String paliwo, double przebieg, double ladownosc, int iloscOsi) {
        super(marka, model, rokProdukcji, kolor, waga, cenaBazowa, status, wymaganeUprawnienia, vin, nrRejestracyjny, pojemnoscSilnika, liczbaMiejsc, paliwo, przebieg);
        this.ladownosc = ladownosc;
        this.iloscOsi = iloscOsi;
    }
}
