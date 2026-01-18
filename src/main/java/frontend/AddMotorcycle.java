package frontend;

import backend.ServiceVehicle;
import pojazd.Motocykl;
import pojazd.Pojazd;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;


public class AddMotorcycle extends JPanel {
    private MainFrame mainFrame;
    private ServiceVehicle serviceVehicle;

    public AddMotorcycle(MainFrame mainFrame, ServiceVehicle serviceVehicle) {
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

        JButton btnDodajPojazd = new JButton("Dodaj pojazd \u25BC");
        JPopupMenu popupPojazdy = new JPopupMenu();

        JMenuItem menuItemCar = new JMenuItem("Dodaj samochód");
        menuItemCar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.ChangeCard("ADD_CAR");
            }
        });
        popupPojazdy.add(menuItemCar);

        JMenuItem menuItemMotor = new JMenuItem("Dodaj motocykl");
        menuItemMotor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.ChangeCard("ADD_MOTORCYCLE");
            }
        });
        popupPojazdy.add(menuItemMotor);

        JMenuItem menuItemTir = new JMenuItem("Dodaj ciężarówkę");
        menuItemTir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.ChangeCard("ADD_TIR");
            }
        });
        popupPojazdy.add(menuItemTir);

        JMenuItem menuItemScooter = new JMenuItem("Dodaj hulajnogę");
        menuItemScooter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.ChangeCard("ADD_SCOOTER");
            }
        });
        popupPojazdy.add(menuItemScooter);

        JMenuItem menuItemBike = new JMenuItem("Dodaj rower");
        menuItemBike.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.ChangeCard("ADD_BIKE");
            }
        });
        popupPojazdy.add(menuItemBike);

        btnDodajPojazd.addActionListener(new  ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                popupPojazdy.show(btnDodajPojazd, 0, btnDodajPojazd.getHeight());
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
        optionsPanel.add(btnDodajPojazd);
        optionsPanel.add(removeVehicleButton);
        optionsPanel.add(addWorkerButton);
        optionsPanel.add(logoutButton);

        optionsPanel.setSize(50,50);

        add(optionsPanel);

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

        addCarPanel.add(new JLabel("Czy ma kufry: "));
        JCheckBox checkBox = new JCheckBox("Tak");

        addCarPanel.add(checkBox);

        addCarPanel.add(new JLabel("Typ: "));
        JTextField typ = new JTextField(15);
        addCarPanel.add(typ);

        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Dodaj");
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String[] moto = {marka.getText(), model.getText(), rokProdukcji.getText(), kolor.getText(), waga.getText(), cenaBazowa.getText(), "A", vin.getText(), nrRejestracyjny.getText(), pojemnoscSilnika.getText(), liczbaMiejsc.getText(), paliwo.getText(), "czy ma kufry", typ.getText()};

                serviceVehicle.addMotorcycle(moto);

                marka.setText(null);
                model.setText(null);
                rokProdukcji.setText(null);
                kolor.setText(null);
                waga.setText(null);
                cenaBazowa.setText(null);
                vin.setText(null);
                nrRejestracyjny.setText(null);
                pojemnoscSilnika.setText(null);
                liczbaMiejsc.setText(null);
                paliwo.setText(null);
                typ.setText(null);
                checkBox.setSelected(false);

                JOptionPane.showMessageDialog(null, "Pojazd dodany pomyślnie!");
                mainFrame.ChangeCard("ADD_MOTORCYCLE");
                repaint();
                revalidate();
            }
        });

        addCarPanel.add(addButton);

        add(addCarPanel, BorderLayout.CENTER);

        revalidate();
        repaint();
    }

    public static void czyPuste(String[] lista) throws Exception {
        for(String elem : lista){
            if(elem.isEmpty()) {
                throw new Exception("Żadne pole nie może być puste");
            }
        }
    }
}
