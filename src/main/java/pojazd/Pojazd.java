package pojazd;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import osoba.Klient;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;

abstract public class Pojazd {
    protected String marka;
    protected String model;
    protected int rokProdukcji;
    protected String kolor;
    protected double waga;
    protected double cenaBazowa;
    protected String status;
    protected String wymaganeUprawnienia;

    public Pojazd(String marka, String model, int rokProdukcji, String kolor, double waga, double cenaBazowa, String status, String wymaganeUprawnienia) {
        this.marka = marka;
        this.model = model;
        this.rokProdukcji = rokProdukcji;
        this.kolor = kolor;
        this.waga = waga;
        this.cenaBazowa = cenaBazowa;
        this.status = status;
        this.wymaganeUprawnienia = wymaganeUprawnienia;
    }

    @Override
    public String toString() {
        return String.format("%s %s (%d) | Kolor: %s | Cena: %.2f PLN | Waga: %.1f kg | Wymagane: %s",
                marka,
                model,
                rokProdukcji,
                kolor,
                cenaBazowa,
                waga,
                wymaganeUprawnienia
        );
    }

    public static void saveVehicles(ArrayList<Pojazd> pojazdy) {
        File vehiclesFile = new File("data/pojazdy.json");
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(vehiclesFile));
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(pojazdy, bw);
            bw.close();
            System.out.println("Pojazd dodany prawidłowo!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Pojazd> loadVehicles() {
            ArrayList<Pojazd> loadedVehicles = new ArrayList<>();
        try {
            File vehiclesFile = new File("data/pojazdy.json");
            BufferedReader reader = new BufferedReader(new FileReader(vehiclesFile));

            Gson gson = new GsonBuilder().setPrettyPrinting().create();;
            Type vehicleListType = new TypeToken<ArrayList<Pojazd>>() {}.getType();
            ArrayList<Klient> loadedClients = gson.fromJson(reader, vehicleListType);
            reader.close();
            System.out.println("działa");
            return loadedVehicles;
        }catch (Exception e){
            return new ArrayList<>();
        }
    }

    public String getMarka() {
        return marka;
    }

    public void setMarka(String marka) {
        this.marka = marka;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getRokProdukcji() {
        return rokProdukcji;
    }

    public void setRokProdukcji(int rokProdukcji) {
        this.rokProdukcji = rokProdukcji;
    }

    public String getKolor() {
        return kolor;
    }

    public void setKolor(String kolor) {
        this.kolor = kolor;
    }

    public double getWaga() {
        return waga;
    }

    public void setWaga(double waga) {
        this.waga = waga;
    }

    public double getCenaBazowa() {
        return cenaBazowa;
    }

    public void setCenaBazowa(double cenaBazowa) {
        this.cenaBazowa = cenaBazowa;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getWymaganeUprawnienia() {
        return wymaganeUprawnienia;
    }

    public void setWymaganeUprawnienia(String wymaganeUprawnienia) {
        this.wymaganeUprawnienia = wymaganeUprawnienia;
    }
}
