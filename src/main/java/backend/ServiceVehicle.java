package backend;


import pojazd.Pojazd;
import pojazd.SamochodOsobowy;

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

            int int_rokProdukcji;
            try {
                int_rokProdukcji = Integer.parseInt(car[2]);
            } catch(Exception ex) {
                JOptionPane.showMessageDialog(null, "Rok produkcji musi być liczbą");
                throw new Exception("Rok produkcji musi być liczbą");
            }

            double d_waga;
            try {
                d_waga = Double.parseDouble(car[4]);
            } catch(Exception ex) {
                JOptionPane.showMessageDialog(null, "Waga musi być liczbą z przecinkiem");
                throw new Exception("Waga zły format");
            }

            double d_cena;
            try {
                d_cena = Double.parseDouble(car[5]);
            } catch(Exception ex) {
                JOptionPane.showMessageDialog(null, "Cena bazowa musi być liczbą z przecinkiem");
                throw new Exception("Cena bazowa zły format");
            }

            double d_pojemnoscSilnika;
            try {
                d_pojemnoscSilnika = Double.parseDouble(car[9]);
            } catch(Exception ex) {
                JOptionPane.showMessageDialog(null, "Pojemność silnika musi być liczbą z przecinkiem");
                throw new Exception("Pojemność silnika zły format");
            }

            int int_liczbaMiejsc;
            try {
                int_liczbaMiejsc = Integer.parseInt(car[10]);
            } catch(Exception ex) {
                JOptionPane.showMessageDialog(null, "Liczba miejsc musi być liczbą");
                throw new Exception("Liczba miejsc musi być liczbą");
            }

            int int_iloscDrzwi;
            try {
                int_iloscDrzwi = Integer.parseInt(car[13]);
            } catch(Exception ex) {
                JOptionPane.showMessageDialog(null, "Ilość drzwi musi być liczbą");
                throw new Exception("Ilość drzwi musi być liczbą");
            }

        } catch(Exception ex) {
            throw new RuntimeException();
        }

        repositoryVehicle.upload(new SamochodOsobowy(car[0], car[1], Integer.parseInt(car[2]), car[3], Double.parseDouble(car[4]), Double.parseDouble(car[5]), "wolny", car[6], car[7], car[8], Double.parseDouble(car[9]), Integer.parseInt(car[10]), car[11], car[12], Integer.parseInt(car[13])));
    }

    public ArrayList<Pojazd> getVehicles() {
        return repositoryVehicle.getVehicles();
    }
}
