package wypozyczenie;

import pojazd.Pojazd;
import osoba.Klient;
import strategia.StrategiaCenowa;

import java.io.Serializable;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Wypozyczenie implements Serializable {
    private Pojazd pojazd;
    private Klient klient;
    private Date dataRozpoczecia;
    private Date dataZakonczenia;
    private double kosztKoncowy;
    private StrategiaCenowa strategia;

    public Wypozyczenie(Pojazd pojazd,Klient klient, Date dataRozpoczecia, Date dataZakonczenia, StrategiaCenowa strategia) {
        this.pojazd = pojazd;
        this.klient = klient;
        this.dataRozpoczecia = dataRozpoczecia;
        this.dataZakonczenia = dataZakonczenia;
        this.strategia = strategia;

        przeliczKoszt(); // liczenie kosztu dla calego wypozyczenia
    }

    public void przeliczKoszt() {
        if (strategia != null && dataRozpoczecia != null && dataZakonczenia != null) {

            long diffInMillies = Math.abs(dataZakonczenia.getTime() - dataRozpoczecia.getTime());

            long diffInMinutes = TimeUnit.MINUTES.convert(diffInMillies, TimeUnit.MILLISECONDS);

            this.kosztKoncowy = strategia.wyliczKoszt(diffInMinutes, pojazd.getCenaBazowa());
        }
    }

    public Date getDataRozpoczecia() {
        return dataRozpoczecia;
    }

    public void setDataRozpoczecia(Date dataRozpoczecia) {
        this.dataRozpoczecia = dataRozpoczecia;
    }

    public Date getDataZakonczenia() {
        return dataZakonczenia;
    }

    public void setDataZakonczenia(Date dataZakonczenia) {
        this.dataZakonczenia = dataZakonczenia;
    }

    public double getKosztKoncowy() {
        return kosztKoncowy;
    }

    public void setKosztKoncowy(double kosztKoncowy) {
        this.kosztKoncowy = kosztKoncowy;
    }

    public StrategiaCenowa getStrategia() {
        return strategia;
    }

    public void setStrategia(StrategiaCenowa strategia) {
        this.strategia = strategia;
    }

    @Override
    public String toString() {
        return "Wypożyczenie: " + pojazd.getMarka() + " " + pojazd.getModel() + " | Koszt: " + String.format("%.2f", kosztKoncowy) + " PLN";
    }
}
