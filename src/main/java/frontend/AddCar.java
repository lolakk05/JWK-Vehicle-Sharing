package frontend;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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

public class AddCar extends JPanel {
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

    public AddCar(MainFrame mainFrame) {
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

                    double d_pojemnoscSilnika;
                    try {
                        d_pojemnoscSilnika = Double.parseDouble(pojemnoscSilnika.getText());
                    } catch(Exception ex) {
                        JOptionPane.showMessageDialog(null, "Pojemność silnika musi być liczbą z przecinkiem");
                        throw new Exception("Pojemność silnika zły format");
                    }

                    int int_liczbaMiejsc;
                    try {
                        int_liczbaMiejsc = Integer.parseInt(liczbaMiejsc.getText());
                    } catch(Exception ex) {
                        JOptionPane.showMessageDialog(null, "Liczba miejsc musi być liczbą");
                        throw new Exception("Liczba miejsc musi być liczbą");
                    }

                    int int_iloscDrzwi;
                    try {
                        int_iloscDrzwi = Integer.parseInt(iloscDrzwi.getText());
                    } catch(Exception ex) {
                        JOptionPane.showMessageDialog(null, "Ilość drzwi musi być liczbą");
                        throw new Exception("Ilość drzwi musi być liczbą");
                    }

                } catch(Exception ex) {
                    throw new RuntimeException();
                }

                vehicles.add(new SamochodOsobowy(marka.getText(), model.getText(), Integer.parseInt(rokProdukcji.getText()), kolor.getText(), Double.parseDouble(waga.getText()), Double.parseDouble(cenaBazowa.getText()), "wolny", wymaganeUprawnienia.getText(), vin.getText(), nrRejestracyjny.getText(), Double.parseDouble(pojemnoscSilnika.getText()), Integer.parseInt(liczbaMiejsc.getText()), paliwo.getText(), nadwozie.getText(), Integer.parseInt(iloscDrzwi.getText())));
                JOptionPane.showMessageDialog(null, "Samochód dodany pomyślnie!");
                saveVehicle();

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

    public static void czyPuste(String[] lista) throws Exception {
        for(String elem : lista){
            if(elem.isEmpty()) {
                throw new Exception("Żadne pole nie może być puste");
            }
        }
    }
}
