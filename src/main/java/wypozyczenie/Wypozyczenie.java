package wypozyczenie;

import pojazd.Pojazd;
import osoba.Klient;
import strategia.StrategiaCenowa;

import java.io.Serializable;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import java.text.SimpleDateFormat;

import wypozyczenie.*;

public class Wypozyczenie implements Serializable {
    private Pojazd pojazd;
    private Klient klient;
    private Date dataRozpoczecia;
    private Date dataZakonczenia;
    private double kosztKoncowy;
    private StrategiaCenowa strategia;
    private Status status;

    public Wypozyczenie(Pojazd pojazd,Klient klient, Date dataRozpoczecia, Date dataZakonczenia, StrategiaCenowa strategia, Status status) {
        this.pojazd = pojazd;
        this.klient = klient;
        this.dataRozpoczecia = dataRozpoczecia;
        this.dataZakonczenia = dataZakonczenia;
        this.strategia = strategia;
        this.status = status;

        przeliczKoszt(); 
    }

    public void przeliczKoszt() {
        if (strategia != null && dataRozpoczecia != null && dataZakonczenia != null) {

            long diffInMillies = Math.abs(dataZakonczenia.getTime() - dataRozpoczecia.getTime());

            long diffInMinutes = TimeUnit.MINUTES.convert(diffInMillies, TimeUnit.MILLISECONDS);

            this.kosztKoncowy = strategia.wyliczKoszt(diffInMinutes, pojazd.getCenaBazowa());
        }
    }

    public String getDataRozpoczecia() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        return formatter.format(dataRozpoczecia);
    }

    public void setDataRozpoczecia(Date dataRozpoczecia) {
        this.dataRozpoczecia = dataRozpoczecia;
    }

    public String getDataZakonczenia() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        return formatter.format(dataZakonczenia);
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

    public Klient getKlient() {
        return klient;
    }

    public Pojazd getPojazd() {
        return pojazd;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Wypożyczenie: " + pojazd.getMarka() + " " + pojazd.getModel() + " | Koszt: " + String.format("%.2f", kosztKoncowy) + " PLN";
    }
}
