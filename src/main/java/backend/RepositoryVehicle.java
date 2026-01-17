package backend;

import pojazd.Pojazd;

import java.io.*;
import java.util.ArrayList;

public class RepositoryVehicle {
    private ArrayList<Pojazd> vehicles;

    public RepositoryVehicle() {
        this.vehicles = new ArrayList<>();

        load();
    }

    public ArrayList<Pojazd> load() {
        ArrayList<Pojazd> vehicles = new ArrayList<>();
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("data/vehicles.ser"))){
            int rozmiar = ois.readInt();
            for(int i = 0; i < rozmiar; i++){
                this.vehicles.add((Pojazd) ois.readObject());
            }
        }
        catch(java.io.FileNotFoundException e) {
            // File doesn't exist on first run - this is normal
        }
        catch(java.io.EOFException e) {
            // File is empty or corrupted - this is normal on first run
        }
        catch (Exception e){
            e.printStackTrace();
            return new ArrayList<>();
        }
        return vehicles;
    }

    public void save() {
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("data/vehicles.ser"))) {
            oos.writeInt(vehicles.size());
            for (Pojazd pojazd : vehicles) {
                oos.writeObject(pojazd);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Pojazd> getVehicles() {
        return vehicles;
    }

    public void upload(Pojazd vehicle) {
        vehicles.add(vehicle);
        save();
    }

    public void delete(Pojazd vehicle) {
        vehicles.remove(vehicle);
        save();
    }
}
