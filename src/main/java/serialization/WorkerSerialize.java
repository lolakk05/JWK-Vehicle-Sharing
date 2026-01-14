package serialization;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import osoba.Klient;
import osoba.Serwisant;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class WorkerSerialize {
    public static ArrayList<Serwisant> loadWorkers() {
        ArrayList<Serwisant> workers = new ArrayList<>();
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("data/workers.ser"))){
            int rozmiar = ois.readInt();
            for(int i = 0; i < rozmiar; i++){
                workers.add((Serwisant) ois.readObject());
            }
            return workers;
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
