package backend;

import wypozyczenie.Wypozyczenie;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class RepositoryRental {
    private ArrayList<Wypozyczenie> rentals;
    private ArrayList<Wypozyczenie> awaitingRentals;

    public RepositoryRental() {
        this.rentals = new ArrayList<>();
        this.awaitingRentals = new ArrayList<>();

        load();
        loadAwaiting();
    }

    public void load() {
        try(ObjectInputStream is = new ObjectInputStream(new FileInputStream("data/rentals.ser"))) {
            int size = is.readInt();
            for(int i = 0; i < size; i++) {
                rentals.add((Wypozyczenie) is.readObject());
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void loadAwaiting() {
        try(ObjectInputStream is = new ObjectInputStream(new FileInputStream("data/awaitingRentals.ser"))) {
            int size = is.readInt();
            for(int i = 0; i < size; i++) {
                awaitingRentals.add((Wypozyczenie) is.readObject());
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void save() {
        try(ObjectOutputStream so = new ObjectOutputStream(new FileOutputStream("data/rentals.ser"))) {
            so.writeInt(rentals.size());
            for(Wypozyczenie rental : rentals) {
                so.writeObject(rental);
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void saveAwaiting() {
        try(ObjectOutputStream so = new ObjectOutputStream(new FileOutputStream("data/awaitingRentals.ser"))) {
            so.writeInt(awaitingRentals.size());
            for(Wypozyczenie rental : awaitingRentals) {
                so.writeObject(rental);
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Wypozyczenie> getRentals() {
        return rentals;
    }

    public ArrayList<Wypozyczenie> getAwaitingRentals() {
        return awaitingRentals;
    }

    public void upload(Wypozyczenie rental) {
        rentals.add(rental);
        save();
    }

    public void uploadAwaiting(Wypozyczenie rental) {
        awaitingRentals.add(rental);
        saveAwaiting();
    }
}