package frontend;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import pojazd.HulajnogaElektryczna;
import pojazd.Rower;
import pojazd.SamochodOsobowy;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import static app.Main.vehicles;

public class AddScooter extends JPanel {
    private MainFrame mainFrame;
    private int liczba_stworzonych = 0;

    public void saveVehicle() {
        File clientsFile = new File("data/vehicles.json");

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(clientsFile));
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(vehicles, writer);
            writer.close();
            System.out.println("Pojazd zarejestrowany prawidłowo!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public AddScooter(MainFrame mainFrame) {
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
                String[] car = {marka.getText(), model.getText(), rokProdukcji.getText(), kolor.getText(), waga.getText(), cenaBazowa.getText(), wymaganeUprawnienia.getText(), pojemnoscBaterii.getText(), zasiegKm.getText(), maxPredkosc.getText()};
                try {
                    czyPuste(car);

                    int int_rokProdukcji;
                    try {
                        int_rokProdukcji = Integer.parseInt(rokProdukcji.getText());
                    } catch(Exception ex) {
                        JOptionPane.showMessageDialog(null, "Rok produkcji musi być liczbą");
                        throw new Exception("Rok produkcji musi być liczbą");
                    }

                    double d_waga;
                    try {
                        d_waga = Double.parseDouble(waga.getText());
                    } catch(Exception ex) {
                        JOptionPane.showMessageDialog(null, "Waga musi być liczbą z przecinkiem");
                        throw new Exception("Waga zły format");
                    }

                    double d_cena;
                    try {
                        d_cena = Double.parseDouble(cenaBazowa.getText());
                    } catch(Exception ex) {
                        JOptionPane.showMessageDialog(null, "Cena bazowa musi być liczbą z przecinkiem");
                        throw new Exception("Cena bazowa zły format");
                    }

                    int int_pojemnoscBaterii;
                    try {
                        int_pojemnoscBaterii = Integer.parseInt(pojemnoscBaterii.getText());
                    } catch(Exception ex) {
                        JOptionPane.showMessageDialog(null, "Pojemność baterii musi być liczbą");
                        throw new Exception("Pojemność baterii musi być liczbą");
                    }

                    int int_zasiegKm;
                    try {
                        int_zasiegKm = Integer.parseInt(zasiegKm.getText());
                    } catch(Exception ex) {
                        JOptionPane.showMessageDialog(null, "Zasięg musi być liczbą");
                        throw new Exception("Zasięg musi być liczbą");
                    }

                    double d_maxPredkosc;
                    try {
                        d_maxPredkosc = Double.parseDouble(maxPredkosc.getText());
                    } catch(Exception ex) {
                        JOptionPane.showMessageDialog(null, "Maksymalna prędkość musi być liczbą z przecinkiem");
                        throw new Exception("Max prędkość zły format");
                    }

                } catch(Exception ex) {
                    throw new RuntimeException();
                }

                vehicles.add(new HulajnogaElektryczna(marka.getText(), model.getText(), Integer.parseInt(rokProdukcji.getText()), kolor.getText(), Double.parseDouble(waga.getText()), Double.parseDouble(cenaBazowa.getText()), "wolny", wymaganeUprawnienia.getText(), Integer.parseInt(pojemnoscBaterii.getText()), Integer.parseInt(zasiegKm.getText()), Double.parseDouble(maxPredkosc.getText())));
                JOptionPane.showMessageDialog(null, "Hulajnoga dodana pomyślnie!");
                saveVehicle();

                marka.setText(null);
                model.setText(null);
                rokProdukcji.setText(null);
                kolor.setText(null);
                waga.setText(null);
                cenaBazowa.setText(null);
                wymaganeUprawnienia.setText(null);
                pojemnoscBaterii.setText(null);
                zasiegKm.setText(null);
                maxPredkosc.setText(null);

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

    public static void czyPuste(String[] lista) throws Exception {
        for(String elem : lista){
            if(elem.isEmpty()) {
                throw new Exception("Żadne pole nie może być puste");
            }
        }
    }
}
