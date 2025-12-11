import java.util.Date;

public class Wypozyczenie {
    private Date dataRozpoczecia;
    private Date dataZakonczenia;
    private double kosztKoncowy;
    private int strategia;

    public Wypozyczenie(Date dataRozpoczecia, Date dataZakonczenia, double kosztKoncowy, int strategia) {
        this.dataRozpoczecia = dataRozpoczecia;
        this.dataZakonczenia = dataZakonczenia;
        this.kosztKoncowy = kosztKoncowy;
        this.strategia = strategia;
    }
}
