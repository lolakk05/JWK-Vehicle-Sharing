package strategia;

public class StrategiaDobowa implements StrategiaCenowa {

    @Override
    public double wyliczKoszt(long czasTrwania, double stawkaBazowa) {
        long liczbaDob = (long) Math.ceil(czasTrwania / (24.0 * 60.0));
        if(liczbaDob == 0) {
            liczbaDob = 1;
        }
        return liczbaDob * stawkaBazowa;
    }
}
