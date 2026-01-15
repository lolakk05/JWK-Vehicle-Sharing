package frontend;

import backend.ServiceVehicle;
import pojazd.Pojazd;
import pojazd.SamochodOsobowy;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class AddCar extends JPanel {
    private MainFrame mainFrame;
    private ServiceVehicle serviceVehicle;

    public AddCar(MainFrame mainFrame, ServiceVehicle serviceVehicle) {
        this.mainFrame = mainFrame;
        this.serviceVehicle = serviceVehicle;
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

        JPanel addCarPanel = new JPanel();

        addCarPanel.setLayout(new GridLayout(0, 2, 10, 10));
        addCarPanel.setBorder(new EmptyBorder(40, 0, 0, 0));

        addCarPanel.add(new JLabel("Marka: "));
        JTextField marka = new JTextField(15);
        addCarPanel.add(marka);

        addCarPanel.add(new JLabel("Model: "));
        JTextField model = new JTextField(15);
        addCarPanel.add(model);

        addCarPanel.add(new JLabel("Rok produkcji: "));
        JTextField rokProdukcji = new JTextField(15);
        addCarPanel.add(rokProdukcji);

        addCarPanel.add(new JLabel("Kolor:"));
        JTextField kolor = new JTextField(15);
        addCarPanel.add(kolor);

        addCarPanel.add(new JLabel("Waga:"));
        JTextField waga = new JTextField(15);
        addCarPanel.add(waga);

        addCarPanel.add(new JLabel("Cena bazowa: "));
        JTextField cenaBazowa =  new JTextField(15);
        addCarPanel.add(cenaBazowa);

        addCarPanel.add(new JLabel("Wymagane uprawnienia: "));
        JTextField wymaganeUprawnienia = new JTextField(15);
        addCarPanel.add(wymaganeUprawnienia);

        addCarPanel.add(new JLabel("Nr VIN: "));
        JTextField vin = new JTextField(15);
        addCarPanel.add(vin);

        addCarPanel.add(new JLabel("Nr rejestracyjny: "));
        JTextField nrRejestracyjny =  new JTextField(15);
        addCarPanel.add(nrRejestracyjny);

        addCarPanel.add(new JLabel("Pojemność silnika: "));
        JTextField pojemnoscSilnika = new JTextField(15);
        addCarPanel.add(pojemnoscSilnika);

        addCarPanel.add(new JLabel("Liczba miejsc: "));
        JTextField liczbaMiejsc = new JTextField(15);
        addCarPanel.add(liczbaMiejsc);

        addCarPanel.add(new JLabel("Paliwo: "));
        JTextField paliwo = new JTextField(15);
        addCarPanel.add(paliwo);

        addCarPanel.add(new JLabel("Nadwozie: "));
        JTextField nadwozie = new JTextField(15);
        addCarPanel.add(nadwozie);

        addCarPanel.add(new JLabel("Ilość drzwi: "));
        JTextField iloscDrzwi = new JTextField(15);
        addCarPanel.add(iloscDrzwi);

        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Dodaj");
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String[] car = {marka.getText(), model.getText(), rokProdukcji.getText(), kolor.getText(), waga.getText(), cenaBazowa.getText(), wymaganeUprawnienia.getText(), vin.getText(), nrRejestracyjny.getText(), pojemnoscSilnika.getText(), liczbaMiejsc.getText(), paliwo.getText(), nadwozie.getText(), iloscDrzwi.getText()};

                serviceVehicle.addCar(car);

                marka.setText(null);
                model.setText(null);
                rokProdukcji.setText(null);
                kolor.setText(null);
                waga.setText(null);
                cenaBazowa.setText(null);
                wymaganeUprawnienia.setText(null);
                vin.setText(null);
                nrRejestracyjny.setText(null);
                pojemnoscSilnika.setText(null);
                liczbaMiejsc.setText(null);
                paliwo.setText(null);
                nadwozie.setText(null);
                iloscDrzwi.setText(null);

                mainFrame.ChangeCard("ADD_VEHICLE_PANEL");
                repaint();
                revalidate();
            }
        });

        addCarPanel.add(addButton);

        add(addCarPanel, BorderLayout.CENTER);

        revalidate();
        repaint();
    }
}
