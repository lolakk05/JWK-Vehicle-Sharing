package serialization;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import osoba.Klient;
import osoba.Serwisant;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class WorkerSerialize {
    public static ArrayList<Serwisant> loadWorkers() {
        try {
            File clientsFile = new File("data/workers.json");
            BufferedReader reader = new BufferedReader(new FileReader(clientsFile));

            Gson gson = new GsonBuilder().setPrettyPrinting().create();;
            Type workerListType = new TypeToken<ArrayList<Serwisant>>() {}.getType();
            ArrayList<Serwisant> loadedWorkers = gson.fromJson(reader, workerListType);
            reader.close();
            return loadedWorkers;
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
