import java.util.Date;

public class ZlecenieNaprawy {
    private String opisUsterki;
    private Date dataZgloszenia;
    private int priorytet;
    private double kosztCzesci;

    public ZlecenieNaprawy(String opisUsterki, Date dataZgloszenia, int priorytet, double kosztCzesci) {
        this.opisUsterki = opisUsterki;
        this.dataZgloszenia = dataZgloszenia;
        this.priorytet = priorytet;
        this.kosztCzesci = kosztCzesci;
    }
}
