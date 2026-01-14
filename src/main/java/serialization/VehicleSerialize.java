package serialization;

import osoba.Klient;
import osoba.Serwisant;
import pojazd.Pojazd;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class VehicleSerialize {
    public static ArrayList<Pojazd> loadVehicles() {
        ArrayList<Pojazd> vehicles = new ArrayList<>();
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("data/vehicles.ser"))){
            int rozmiar = ois.readInt();
            for(int i = 0; i < rozmiar; i++){
                vehicles.add((Pojazd) ois.readObject());
            }
            return vehicles;
        }catch (Exception e){
            return new ArrayList<>();
        }
    }

    public static void printClients(ArrayList<Klient> clients) {
        for (Klient client : clients) {
            System.out.println(client);
        }
    }
}
