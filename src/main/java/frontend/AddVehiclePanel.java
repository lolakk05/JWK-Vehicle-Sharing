package frontend;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;


public class AddVehiclePanel extends JPanel {
    private MainFrame mainFrame;

    public AddVehiclePanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        setLayout(new FlowLayout());

        JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(new GridLayout(1,4));

        JButton acceptButton = new JButton("Strona główna");
        acceptButton.setSize(new Dimension(30,30));
        acceptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.ChangeCard("ACCEPT_LOAN");
            }
        });

        JButton addVehicleButton = new JButton("Dodaj pojazd");
        addVehicleButton.setSize(new Dimension(30,30));
        addVehicleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.ChangeCard("ADD_VEHICLE_PANEL");
            }
        });

        JButton removeVehicleButton = new JButton("Usuń pojazd");
        removeVehicleButton.setSize(new Dimension(30,30));
        removeVehicleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.ChangeCard("REMOVE_VEHICLE_PANEL");
            }
        });

        JButton addWorkerButton = new JButton("Dodaj serwisanta");
        addWorkerButton.setSize(new Dimension(30,30));
        addWorkerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.ChangeCard("ADD_WORKER_PANEL");
            }
        });

        JButton logoutButton = new JButton("Wyloguj");
        logoutButton.setSize(new Dimension(30,30));
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.ChangeCard("LOGIN");
            }
        });

        optionsPanel.add(acceptButton);
        optionsPanel.add(addVehicleButton);
        optionsPanel.add(removeVehicleButton);
        optionsPanel.add(addWorkerButton);
        optionsPanel.add(logoutButton);

        optionsPanel.setSize(50,50);

        add(optionsPanel, BorderLayout.CENTER);

        JPanel chooseVehiclePanel = new JPanel();
        chooseVehiclePanel.setLayout(new GridLayout(1,5));

        JButton carButton = new JButton("Samochód");
        carButton.setSize(new Dimension(30,30));
        carButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            mainFrame.ChangeCard("ADD_CAR");
            }
        });

        JButton motorcycleButton = new JButton("Motocykl");
        motorcycleButton.setSize(new Dimension(30,30));
        motorcycleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.ChangeCard("ADD_MOTORCYCLE");
            }
        });

        JButton tirButton = new JButton("Ciężarówka");
        tirButton.setSize(new Dimension(30,30));
        tirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.ChangeCard("ADD_TIR");
            }
        });

        JButton scooterButton = new JButton("Hulajnoga");
        scooterButton.setSize(new Dimension(30,30));
        scooterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.ChangeCard("ADD_SCOOTER");
            }
        });

        JButton bikeButton = new JButton("Rower");
        bikeButton.setSize(new Dimension(30,30));
        bikeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.ChangeCard("ADD_BIKE");
            }
        });

        chooseVehiclePanel.add(carButton);
        chooseVehiclePanel.add(motorcycleButton);
        chooseVehiclePanel.add(tirButton);
        chooseVehiclePanel.add(scooterButton);
        chooseVehiclePanel.add(bikeButton);

        chooseVehiclePanel.setSize(50,50);

        add(chooseVehiclePanel, BorderLayout.CENTER);
    }

    public static void czyPuste(String[] lista) throws Exception {
        for(String elem : lista){
            if(elem.isEmpty()) {
                throw new Exception("Żadne pole nie może być puste");
            }
        }
    }
}
