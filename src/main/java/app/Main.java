package app;

import frontend.MainFrame;
import osoba.*;

import pojazd.Pojazd;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static serialization.UserSerialize.*;
import static serialization.VehicleSerialize.loadVehicles;
import static serialization.WorkerSerialize.loadWorkers;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class Main {
    public static ArrayList<Klient> clients = loadClients();
    public static ArrayList<Pojazd> vehicles = loadVehicles();
    public static ArrayList<Serwisant> workers = loadWorkers();


    public static boolean login(String email, String password) {
        for (Klient client : clients) {
            if (client.getEmail().equals(email) && BCrypt.checkpw(password, client.getHaslo())) {
                Session.login(client);
                return true;
            }
        }
        return false;
    }

    public boolean registerClient(Object[] user) throws Exception {

        int index = 0;
        for(Object obj : user) {
            if(obj instanceof List<?> && (boolean) user[7]) {
                if(((List<?>) obj).isEmpty()){
                    JOptionPane.showMessageDialog(null, "Żadne pole nie może pozostać puste");
                    throw new Exception("Puste pole");
                }
            } else if((boolean) user[7]) {
                if(obj instanceof String && ((String) obj).isEmpty()){
                    JOptionPane.showMessageDialog(null, "Żadne pole nie może pozostać puste");
                    throw new Exception("Puste pole");
                }
            }
            else {
                if(index !=7 && index != 8 && index != 9){
                    if(((String) obj).isEmpty()){
                        JOptionPane.showMessageDialog(null, "Żadne pole nie może pozostać puste");
                        throw new Exception("Puste pole");
                    }
                }
            }
            index++;
        }

        int age;
        try {
            age = Integer.parseInt((String) user[3]);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Wiek musi być liczbą");
            throw new Exception("Wiek musi być liczbą!");
        }

        try {
            PeselException.ValidatePesel((String) user[2]);
        } catch(Exception e) {
            JOptionPane.showMessageDialog(null, "Niepoprawny pesel");
            throw new Exception("Niepoprawny pesel!");
        }

        try {
            EmailException.ValidateEmail((String)user[4]);
        } catch(Exception e) {
            JOptionPane.showMessageDialog(null, "Niepoprawny email");
            throw new Exception("Niepoprawny email!");
        }

        try {
            PhoneNumberException.ValidateNumber((String)user[6]);
        } catch(Exception e) {
            JOptionPane.showMessageDialog(null, "Niepoprawny numer telefonu");
            throw new Exception("Niepoprawny numer telefonu!");
        }

        String password = (String) user[5];
        String salt = BCrypt.gensalt();
        String hashedPassword = BCrypt.hashpw(password, salt);

        Klient newClient = new Klient((String) user[0], (String) user[1], (String) user[2], age, (String) user[4], hashedPassword, (String) user[6], (String) user[8], (List<String>) user[9], 0);

        clients.add(newClient);

        try {
            saveClients();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return true;
    }

    public void saveClients() {
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("data/clients.ser"))) {
            oos.writeInt(clients.size());
            for (Klient client : clients) {
                oos.writeObject(client);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void startGUI() {
        new MainFrame(this);
    }

    public static void main(String[] args) {

        for(Pojazd pojazd : vehicles) {
            System.out.println(pojazd.getMarka());
        }
        SwingUtilities.invokeLater(() -> {
            new Main().startGUI();
        });
    }
}