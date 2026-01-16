package backend;


import pojazd.*;

import javax.swing.*;
import java.util.ArrayList;

public class ServiceVehicle {
    private RepositoryVehicle repositoryVehicle;

    public ServiceVehicle() {
        this.repositoryVehicle = new RepositoryVehicle();
    }

    public static void czyPuste(String[] lista) throws Exception {
        for(String elem : lista){
            if(elem.isEmpty()) {
                throw new Exception("Żadne pole nie może być puste");
            }
        }
    }

    public void addCar(String[] car) {
        try {
            czyPuste(car);

            int int_rokProdukcji = parseInt(car[2], "Rok produkcji");
            double d_waga = parseDouble(car[4], "Waga");
            double d_cena = parseDouble(car[5], "Cena bazowa");
            double d_pojemnoscSilnika = parseDouble(car[9], "Pojemność silnika");
            int int_liczbaMiejsc = parseInt(car[10], "Liczba miejsc");
            int int_iloscDrzwi = parseInt(car[13], "Ilość drzwi");

            repositoryVehicle.upload(new SamochodOsobowy(
                    car[0], car[1], int_rokProdukcji, car[3], d_waga, d_cena, "wolny",
                    car[6], car[7], car[8], d_pojemnoscSilnika, int_liczbaMiejsc, car[11], car[12], int_iloscDrzwi
            ));

        } catch(Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public void addBike(String[] bike) {
        try {
            czyPuste(bike);

            int int_rokProdukcji = parseInt(bike[2], "Rok produkcji");
            double d_waga = parseDouble(bike[4], "Waga");
            double d_cena = parseDouble(bike[5], "Cena bazowa");
            int int_rozmiarKol = parseInt(bike[7], "Rozmiar kół");

            repositoryVehicle.upload(new Rower(
                    bike[0], bike[1], int_rokProdukcji, bike[3], d_waga, d_cena, "wolny",
                    bike[6], int_rozmiarKol, bike[8]
            ));

        } catch(Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public void addScooter(String[] scooter) {
        try {
            czyPuste(scooter);

            int int_rokProdukcji = parseInt(scooter[2], "Rok produkcji");
            double d_waga = parseDouble(scooter[4], "Waga");
            double d_cena = parseDouble(scooter[5], "Cena bazowa");
            int int_pojemnoscBaterii = parseInt(scooter[7], "Pojemność baterii");
            int int_zasieg = parseInt(scooter[8], "Zasięg");
            double d_maxPredkosc = parseDouble(scooter[9], "Prędkość maksymalna");

            repositoryVehicle.upload(new HulajnogaElektryczna(
                    scooter[0], scooter[1], int_rokProdukcji, scooter[3], d_waga, d_cena, "wolny",
                    scooter[6], int_pojemnoscBaterii, int_zasieg, d_maxPredkosc
            ));

        } catch(Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public void addTir(String[] tir) {
        try {
            czyPuste(tir);

            int int_rokProdukcji = parseInt(tir[2], "Rok produkcji");
            double d_waga = parseDouble(tir[4], "Waga");
            double d_cena = parseDouble(tir[5], "Cena bazowa");
            double d_pojemnoscSilnika = parseDouble(tir[9], "Pojemność silnika");
            int int_liczbaMiejsc = parseInt(tir[10], "Liczba miejsc");
            double d_ladownosc = parseDouble(tir[12], "Ładowność");
            int int_iloscOsi = parseInt(tir[13], "Ilość osi");

            repositoryVehicle.upload(new Ciezarowka(
                    tir[0], tir[1], int_rokProdukcji, tir[3], d_waga, d_cena, "wolny",
                    tir[6], tir[7], tir[8], d_pojemnoscSilnika, int_liczbaMiejsc, tir[11], d_ladownosc, int_iloscOsi
            ));

        } catch(Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public void addMotorcycle(String[] moto) {
        try {
            czyPuste(moto);

            int int_rokProdukcji = parseInt(moto[2], "Rok produkcji");
            double d_waga = parseDouble(moto[4], "Waga");
            double d_cena = parseDouble(moto[5], "Cena bazowa");
            double d_pojemnoscSilnika = parseDouble(moto[9], "Pojemność silnika");
            int int_liczbaMiejsc = parseInt(moto[10], "Liczba miejsc");

            boolean b_czyMaKufry = Boolean.parseBoolean(moto[12]) || moto[12].equalsIgnoreCase("Tak");

            repositoryVehicle.upload(new Motocykl(
                    moto[0], moto[1], int_rokProdukcji, moto[3], d_waga, d_cena, "wolny",
                    moto[6], moto[7], moto[8], d_pojemnoscSilnika, int_liczbaMiejsc, moto[11], b_czyMaKufry, moto[13]
            ));

        } catch(Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    private int parseInt(String value, String fieldName) throws Exception {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, fieldName + " musi być liczbą całkowitą!");
            throw e;
        }
    }

    private double parseDouble(String value, String fieldName) throws Exception {
        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, fieldName + " musi być liczbą (np. 10.5)!");
            throw e;
        }
    }

    public ArrayList<Pojazd> getVehicles() {
        return repositoryVehicle.getVehicles();
    }
    
    public RepositoryVehicle getRepositoryVehicle() {
        return repositoryVehicle;
    }
}
