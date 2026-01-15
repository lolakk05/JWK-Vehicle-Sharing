package backend;

import osoba.Klient;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class RepositoryUser {
    private ArrayList<Klient> clients;

    public RepositoryUser() {
        this.clients = new ArrayList<>();

        load();
    }

    public void load() {
        try(ObjectInputStream is = new ObjectInputStream(new FileInputStream("data/clients.ser"))) {
            int size =is.readInt();
            for(int i = 0; i < size; i++) {
                clients.add((Klient) is.readObject());
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void save() {
        try(ObjectOutputStream so = new ObjectOutputStream(new FileOutputStream("data/clients.ser"))) {
            so.writeInt(clients.size());
            for(Klient client : clients) {
                so.writeObject(client);
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Klient> getClients() {
        return clients;
    }

    public void upload(Klient client) {
        clients.add(client);
        save();
    }
}
