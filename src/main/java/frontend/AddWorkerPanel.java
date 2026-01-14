package frontend;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import osoba.EmailException;
import osoba.PeselException;
import osoba.PhoneNumberException;
import osoba.Serwisant;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import static app.Main.vehicles;
import static app.Main.workers;

public class AddWorkerPanel extends JPanel {
    private MainFrame mainFrame;

    public void saveWorker() {
        File clientsFile = new File("data/workers.json");

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(clientsFile));
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(workers, writer);
            writer.close();
            System.out.println("Serwisant zarejestrowany prawidłowo!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public AddWorkerPanel(MainFrame mainFrame) {
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
        JTextField pesel =  new JTextField(15);
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
                for(String elem : worker) {
                    if(elem.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Żadne pole nie może pozostać puste");
                        throw new Exception("Puste pole");
                    }
                }

                    try{
                        PeselException.ValidatePesel(pesel.getText());
                    } catch (PeselException ex) {
                        JOptionPane.showMessageDialog(null, "Niepoprawny pesel");
                        throw new Exception("Nieprawidłowy pesel");
                    }

                    try {
                        EmailException.ValidateEmail(email.getText());
                    } catch(EmailException ex) {
                        JOptionPane.showMessageDialog(null, "Niepoprawny email");
                        throw new Exception("Niepoprawny email");
                    }

                    try{
                        PhoneNumberException.ValidateNumber(phone.getText());
                    } catch(PhoneNumberException ex) {
                        JOptionPane.showMessageDialog(null, "Niepoprawny numer telefonu");
                        throw new Exception("Niepoprawny numer telefonu");
                    }

                    int int_age;
                    try {
                        int_age = Integer.parseInt(age.getText());
                    } catch(Exception ex) {
                        JOptionPane.showMessageDialog(null, "Wiek musi być liczbą");
                        throw new Exception("Wiek musi być liczbą");
                    }

                    workers.add(new Serwisant(name.getText(), surname.getText(), pesel.getText(), int_age, email.getText(), new String(password.getPassword()), phone.getText(), "serwisant", spec.getText(), new ArrayList<>()));
                    JOptionPane.showMessageDialog(null, "Serwisant dodany pomyślnie!");
                    saveWorker();

                    name.setText(null);
                    surname.setText(null);
                    pesel.setText(null);
                    age.setText(null);
                    email.setText(null);
                    password.setText(null);
                    phone.setText(null);
                    spec.setText(null);

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
