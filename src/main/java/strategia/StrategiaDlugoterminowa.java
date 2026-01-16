package strategia;

public class StrategiaDlugoterminowa implements StrategiaCenowa {

    @Override
    public double wyliczKoszt(long czasTrwania, double stawkaBazowa) {
        long liczbaDob = (long) Math.ceil(czasTrwania / (24.0 * 60.0));
        double kosztPodstawowy = liczbaDob * stawkaBazowa;
        
        if (liczbaDob > 10) {
            long dniPonizej10 = liczbaDob - 10;
            double rabat = dniPonizej10 * 0.02;
            if (rabat > 0.5) rabat = 0.5; 
            return kosztPodstawowy * (1 - rabat);
        }
        
        return kosztPodstawowy;
    }
}