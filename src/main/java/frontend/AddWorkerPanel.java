package frontend;

import backend.ServiceWorker;
import osoba.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

public class AddWorkerPanel extends JPanel {
    private MainFrame mainFrame;
    private ServiceWorker serviceWorker;

    public AddWorkerPanel(MainFrame mainFrame, ServiceWorker serviceWorker) {
        this.mainFrame = mainFrame;
        this.serviceWorker = serviceWorker;
        setLayout(new FlowLayout());

        JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(new GridLayout(1, 4));

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

        optionsPanel.setSize(50, 50);

        add(optionsPanel, BorderLayout.CENTER);

        JPanel addWorkerPanel = new JPanel();
        addWorkerPanel.setLayout(new GridLayout(0, 2, 10, 10));
        addWorkerPanel.setBorder(new EmptyBorder(50, 0, 0, 0));

        addWorkerPanel.add(new JLabel("Imie: "));
        JTextField name = new JTextField(15);
        addWorkerPanel.add(name);

        addWorkerPanel.add(new JLabel("Nazwisko: "));
        JTextField surname = new JTextField(15);
        addWorkerPanel.add(surname);

        addWorkerPanel.add(new JLabel("Pesel"));
        JTextField pesel = new JTextField(15);
        addWorkerPanel.add(pesel);

        addWorkerPanel.add(new JLabel("Wiek:"));
        JTextField age = new JTextField(15);
        addWorkerPanel.add(age);

        addWorkerPanel.add(new JLabel("Email:"));
        JTextField email = new JTextField(15);
        addWorkerPanel.add(email);

        addWorkerPanel.add(new JLabel("Hasło:"));
        JPasswordField password = new JPasswordField(15);
        addWorkerPanel.add(password);

        addWorkerPanel.add(new JLabel("Numer telefonu:"));
        JTextField phone = new JTextField(15);
        addWorkerPanel.add(phone);

        addWorkerPanel.add(new JLabel("Specjalizacja:"));
        JTextField spec = new JTextField(15);
        addWorkerPanel.add(spec);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

        JButton addButton = new JButton("Dodaj");
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String[] worker = {name.getText(), surname.getText(), pesel.getText(), age.getText(), email.getText(), new String(password.getPassword()), phone.getText(), spec.getText()};
                try {
                    for (String elem : worker) {
                        if (elem.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Żadne pole nie może pozostać puste");
                            throw new Exception("Puste pole");
                        }
                    }

                    try {
                        PeselException.ValidatePesel(pesel.getText());
                    } catch (PeselException ex) {
                        JOptionPane.showMessageDialog(null, "Niepoprawny pesel");
                        throw new Exception("Nieprawidłowy pesel");
                    }

                    try {
                        EmailException.ValidateEmail(email.getText());
                    } catch (EmailException ex) {
                        JOptionPane.showMessageDialog(null, "Niepoprawny email");
                        throw new Exception("Niepoprawny email");
                    }

                    try {
                        PhoneNumberException.ValidateNumber(phone.getText());
                    } catch (PhoneNumberException ex) {
                        JOptionPane.showMessageDialog(null, "Niepoprawny numer telefonu");
                        throw new Exception("Niepoprawny numer telefonu");
                    }

                    int int_age;
                    try {
                        int_age = Integer.parseInt(age.getText());
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Wiek musi być liczbą");
                        throw new Exception("Wiek musi być liczbą");
                    }

                    name.setText(null);
                    surname.setText(null);
                    pesel.setText(null);
                    age.setText(null);
                    email.setText(null);
                    password.setText(null);
                    phone.setText(null);
                    spec.setText(null);

                    Serwisant newSerwisant = new Serwisant(worker[0], worker[1], worker[2], int_age, worker[4], worker[5], worker[6], "Serwis", worker[7], new ArrayList<>());
                    serviceWorker.dodajPracownika(newSerwisant);
                    JOptionPane.showMessageDialog(null, "Dodano serwisanta");

                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        buttonPanel.add(addButton);
        addWorkerPanel.add(buttonPanel);

        add(addWorkerPanel, BorderLayout.CENTER);
    }
}
