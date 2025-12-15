package Pojazd;

abstract public class Pojazd {
    private String marka;
    private String model;
    private int rokProdukcji;
    private String kolor;
    private double waga;
    private double cenaBazowa;
    private String status;
    private String wymaganeUprawnienia;

    public Pojazd(String marka, String model, int rokProdukcji, String kolor, double waga, double cenaBazowa, String status, String wymaganeUprawnienia) {
        this.marka = marka;
        this.model = model;
        this.rokProdukcji = rokProdukcji;
        this.kolor = kolor;
        this.waga = waga;
        this.cenaBazowa = cenaBazowa;
        this.status = status;
        this.wymaganeUprawnienia = wymaganeUprawnienia;
    }
}
