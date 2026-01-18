package frontend;

import backend.ServiceVehicle;
import pojazd.HulajnogaElektryczna;
import pojazd.Pojazd;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class AddScooter extends JPanel {
    private MainFrame mainFrame;
    private ServiceVehicle serviceVehicle;

    public AddScooter(MainFrame mainFrame, ServiceVehicle serviceVehicle) {
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

        addCarPanel.add(new JLabel("Pojemność baterii: "));
        JTextField pojemnoscBaterii = new JTextField(15);
        addCarPanel.add(pojemnoscBaterii);

        addCarPanel.add(new JLabel("Zasięg: "));
        JTextField zasiegKm = new JTextField(15);
        addCarPanel.add(zasiegKm);

        addCarPanel.add(new JLabel("Max prędkość: "));
        JTextField maxPredkosc = new JTextField(15);
        addCarPanel.add(maxPredkosc);


        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Dodaj");
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String[] scooter = {marka.getText(), model.getText(), rokProdukcji.getText(), kolor.getText(), waga.getText(), cenaBazowa.getText(), "Karta rowerowa", pojemnoscBaterii.getText(), zasiegKm.getText(), maxPredkosc.getText()};

                serviceVehicle.addScooter(scooter);

                marka.setText(null);
                model.setText(null);
                rokProdukcji.setText(null);
                kolor.setText(null);
                waga.setText(null);
                cenaBazowa.setText(null);
                pojemnoscBaterii.setText(null);
                zasiegKm.setText(null);
                maxPredkosc.setText(null);

                JOptionPane.showMessageDialog(null, "Pojazd dodany pomyślnie!");
                mainFrame.ChangeCard("ADD_SCOOTER");
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
