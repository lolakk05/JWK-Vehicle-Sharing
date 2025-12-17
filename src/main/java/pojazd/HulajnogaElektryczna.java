package pojazd;

public class HulajnogaElektryczna extends Pojazd {
    private int pojemnoscBaterii;
    private int zasiegKm;
    private double maxPredkosc;

    public HulajnogaElektryczna(String marka, String model, int rokProdukcji, String kolor, double waga, double cenaBazowa, String status, String wymaganeUprawnienia, int pojemnoscBaterii, int zasiegKm, double maxPredkosc) {
        super(marka, model, rokProdukcji, kolor, waga, cenaBazowa, status, wymaganeUprawnienia);
        this.pojemnoscBaterii = pojemnoscBaterii;
        this.zasiegKm = zasiegKm;
        this.maxPredkosc = maxPredkosc;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" | [HULAJNOGA] Bateria: %d | Zasięg: %d km | V-max: %.1f km/h",
                pojemnoscBaterii,
                zasiegKm,
                maxPredkosc
        );
    }
}
