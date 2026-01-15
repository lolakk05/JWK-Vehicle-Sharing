package backend;

import osoba.Pracownik;
import osoba.Serwisant;

import java.io.*;
import java.util.ArrayList;

public class RepositoryWorker {
    private ArrayList<Pracownik> workers;

    public RepositoryWorker() {
        this.workers = new ArrayList<>();

        load();
    }

    public void load() {
        try(ObjectInputStream is = new ObjectInputStream(new FileInputStream("data/workers.ser"))) {
            int size = is.readInt();
            for(int i = 0; i < size; i++) {
                workers.add((Pracownik) is.readObject());
            }
        }
        catch(FileNotFoundException e) {
            System.out.println("Brak pliku z pracownikami (to normalne przy pierwszym uruchomieniu).");
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void save() {
        try(ObjectOutputStream so = new ObjectOutputStream(new FileOutputStream("data/workers.ser"))) {
            so.writeInt(workers.size());
            for(Pracownik worker : workers) {
                so.writeObject(worker);
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Pracownik> getWorkers() {
        return workers;
    }

    public void upload(Pracownik worker) {
        workers.add(worker);
        save();
    }
}