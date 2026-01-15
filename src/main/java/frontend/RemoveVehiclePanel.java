package frontend;

import pojazd.Pojazd;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class RemoveVehiclePanel extends JPanel {
    private MainFrame mainFrame;
    private JPanel containerPanel;
    private ArrayList<Pojazd> vehicles = new ArrayList<>();

    public void saveVehicle() {
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("data/vehicles.ser"))) {
            oos.writeInt(vehicles.size());
            for (Pojazd pojazd : vehicles) {
                oos.writeObject(vehicles);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public RemoveVehiclePanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        setLayout(new FlowLayout());
        vehicles = new ArrayList<>();

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

        containerPanel = new JPanel();
        containerPanel.setLayout(new BoxLayout(containerPanel, BoxLayout.Y_AXIS));

        JScrollPane scrollPane = new JScrollPane(containerPanel);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        add(scrollPane, BorderLayout.CENTER);

        refresh();
    }

    public void refresh() {
        containerPanel.removeAll();

        if (vehicles.isEmpty()) {
            containerPanel.add(new JLabel("Brak pojazdów w bazie."));
        } else {
            for (Pojazd pojazd : vehicles) {
                JPanel vehicleRow = new JPanel(new BorderLayout());
                vehicleRow.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));
                vehicleRow.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));

                JLabel rejestracjaLabel = new JLabel(pojazd.getMarka()+" | "+pojazd.getModel());
                rejestracjaLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0)); // Margines tekstu
                vehicleRow.add(rejestracjaLabel, BorderLayout.CENTER);

                JButton deleteButton = new JButton("USUŃ");
                deleteButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        vehicles.remove(pojazd);
                        saveVehicle();
                    }
                });

                vehicleRow.add(deleteButton, BorderLayout.EAST);
                containerPanel.add(vehicleRow);
            }
        }
        containerPanel.revalidate();
        containerPanel.repaint();
    }
}