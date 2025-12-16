package strategia;

public class StrategiaDobowa implements StrategiaCenowa {
    @Override
    public double wyliczKoszt(long czasTrwania, double stawkaBazowa) {
        long liczbaDob = (long) Math.ceil(czasTrwania / (24*60));
        return liczbaDob * stawkaBazowa;
    }
}
