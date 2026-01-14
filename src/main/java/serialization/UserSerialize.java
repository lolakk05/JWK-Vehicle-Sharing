package serialization;

import com.google.gson.reflect.TypeToken;
import osoba.Klient;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import com.google.gson.*;

public class UserSerialize {
    public static ArrayList<Klient> loadClients() {
        ArrayList<Klient> clients = new ArrayList<>();
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("data/clients.ser"))){
            int rozmiar = ois.readInt();
            for(int i = 0; i < rozmiar; i++){
                clients.add((Klient) ois.readObject());
            }
            return clients;
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
