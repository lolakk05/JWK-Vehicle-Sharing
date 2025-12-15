public class Rower extends Pojazd {
    private int rozmiarKol;
    private String typ;

    public Rower(String marka, String model, int rokProdukcji, String kolor, double waga, double cenaBazowa, String status, String wymaganeUprawnienia, int rozmiarKol, String typ) {
        super(marka, model, rokProdukcji, kolor, waga, cenaBazowa, status, wymaganeUprawnienia);
        this.rozmiarKol = rozmiarKol;
        this.typ = typ;
    }
}
