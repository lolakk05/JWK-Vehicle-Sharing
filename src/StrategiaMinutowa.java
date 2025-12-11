public class StrategiaMinutowa implements StrategiaCenowa {
    @Override
    public double wyliczKoszt(long czasTrwania, double stawkaBazowa) {
        return czasTrwania * stawkaBazowa;
    }
}
