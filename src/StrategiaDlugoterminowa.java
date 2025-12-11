public class StrategiaDlugoterminowa implements StrategiaCenowa {
    public double rabat;

    public StrategiaDlugoterminowa(double rabat) {
        this.rabat = rabat;
    }

    @Override
    public double wyliczKoszt(long czasTrwania, double stawkaBazowa) {
        long liczbaDob = (long) Math.ceil(czasTrwania / (24*60));
        double kosztPodstawowy = liczbaDob * stawkaBazowa;
        return kosztPodstawowy * (1 - rabat);
    }
}
