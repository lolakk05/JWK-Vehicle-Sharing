package zlecenieNaprawy;

import osoba.Serwisant;
import pojazd.Pojazd;
import java.io.Serializable;
import java.util.Date;

public class ZlecenieNaprawy implements Serializable {
    private static int nextId = 1;

    public static void setNextId(int nextId) {
        ZlecenieNaprawy.nextId = nextId;
    }

    private int id;
    private String opisUsterki;
    private Date dataZgloszenia;
    private double kosztCzesci;
    private Pojazd pojazd;
    private boolean czyZakonczone;
    private Serwisant serwisant;

    public ZlecenieNaprawy(String opisUsterki, Date dataZgloszenia, double kosztCzesci, Pojazd pojazd) {
        this.id = nextId++;
        this.opisUsterki = opisUsterki;
        this.dataZgloszenia = dataZgloszenia;
        this.kosztCzesci = kosztCzesci;
        this.pojazd = pojazd;
        this.czyZakonczone = false;
        this.serwisant = null;
    }

    public int getId() {
        return id;
    }

    public String getOpisUsterki() {
        return opisUsterki;
    }

    public Date getDataZgloszenia() {
        return dataZgloszenia;
    }

    public double getKosztCzesci() {
        return kosztCzesci;
    }

    public Pojazd getPojazd() {
        return pojazd;
    }

    public boolean isCzyZakonczone() {
        return czyZakonczone;
    }

    public void setCzyZakonczone(boolean czyZakonczone) {
        this.czyZakonczone = czyZakonczone;
    }

    public Serwisant getSerwisant() {
        return serwisant;
    }

    public void setSerwisant(Serwisant serwisant) {
        this.serwisant = serwisant;
    }

    @Override
    public String toString() {
        return String.format("Zlecenie #%d | %s | Pojazd: %s %s",
                id, opisUsterki, pojazd.getMarka(), pojazd.getModel());
    }
}
