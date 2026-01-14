package app;

import frontend.MainFrame;
import osoba.*;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import pojazd.Pojazd;
import pojazd.SamochodOsobowy;

import javax.swing.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import static pojazd.Pojazd.loadVehicles;
import static serialization.UserSerialize.*;
import static serialization.WorkerSerialize.loadWorkers;

public class Main {
    public static ArrayList<Klient> clients = loadClients();
    public static ArrayList<Pojazd> vehicles = loadVehicles();
    public static ArrayList<Serwisant> workers = loadWorkers();


    public static boolean login(String email, String password) {
        for (Klient client : clients) {
            if (client.getEmail().equals(email) && client.getHaslo().equals(password)) {
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
                if(((String) obj).isEmpty() && (index != 7)){
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

        Klient newClient = new Klient((String) user[0], (String) user[1], (String) user[2], age, (String) user[4], (String) user[5], (String) user[6], (String) user[8], (List<String>) user[9], 0);

        clients.add(newClient);

        try {
            saveClients();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return true;
    }

    public void saveClients() {
        File clientsFile = new File("data/clients.json");

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(clientsFile));
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(clients, writer);
            writer.close();
            System.out.println("Użytkownik zarejestrowany prawidłowo!");
        } catch (Exception e) {
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