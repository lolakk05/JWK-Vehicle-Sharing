package serialization;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import osoba.Klient;
import pojazd.Pojazd;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;

import static app.Main.vehicles;

public class VehicleSerialization {
    public ArrayList<Pojazd> loadVehicles() {
        try {
            File vehiclesFile = new File("data/vehicles.json");
            BufferedReader reader = new BufferedReader(new FileReader(vehiclesFile));

            Gson gson = new GsonBuilder().setPrettyPrinting().create();;
            Type vehicleListType = new TypeToken<ArrayList<Pojazd>>() {}.getType();
            ArrayList<Pojazd> loadedVehicles = gson.fromJson(reader, vehicleListType);
            reader.close();
            return loadedVehicles;
        }catch (Exception e){
            return new ArrayList<>();
        }
    }
}
