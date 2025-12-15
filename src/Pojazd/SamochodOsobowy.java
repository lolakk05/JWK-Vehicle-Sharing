public class SamochodOsobowy extends PojazdSilnikowy {
    private String nadwozie;
    private int iloscDrzwi;

    public SamochodOsobowy(String marka, String model, int rokProdukcji, String kolor, double waga, double cenaBazowa, String status, String wymaganeUprawnienia, String vin, String nrRejestracyjny, double pojemnoscSilnika, int liczbaMiejsc, String paliwo, double przebieg, String nadwozie, int iloscDrzwi) {
        super(marka, model, rokProdukcji, kolor, waga, cenaBazowa, status, wymaganeUprawnienia, vin, nrRejestracyjny, pojemnoscSilnika, liczbaMiejsc, paliwo, przebieg);
        this.nadwozie = nadwozie;
        this.iloscDrzwi = iloscDrzwi;
    }
}
