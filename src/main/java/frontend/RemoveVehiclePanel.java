package frontend;

import app.Main;
import pojazd.Pojazd;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Objects;

import static app.Main.vehicles;

public class RemoveVehiclePanel extends JPanel {
    private MainFrame mainFrame;

    public RemoveVehiclePanel(MainFrame mainFrame) {
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
        JPanel containerPanel = new JPanel(new GridLayout(0, 3, 15, 15));
        containerPanel.setBorder(new EmptyBorder(20, 20, 20, 20)); // Margines dookoła całej siatki
        containerPanel.setBackground(new Color(240, 240, 240)); // Jasnoszare tło pod kafelkami

        for (Pojazd m : vehicles) {
            containerPanel.add(new MotorTile(m));
        }

        JScrollPane scrollPane = new JScrollPane(containerPanel);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.setBorder(null);

        add(scrollPane);
        add(containerPanel);
    }

    public void refreshList(JPanel vehicleListPanel) {
        ArrayList<Pojazd> pojazdy = vehicles;

        for (Pojazd p : pojazdy) {
            if(Objects.equals(p.getStatus(), "wolny")) {
                JPanel row = new JPanel(new FlowLayout(FlowLayout.LEFT));
                row.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));

                JLabel label = new JLabel(p.getMarka() + " " + p.getModel() + " (" + p.getStatus() + ")");
                JButton btnDetails = new JButton("Szczegóły");
                btnDetails.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        mainFrame.setVehicle(p);
                        mainFrame.ChangeCard("VEHICLE");
                    }
                });

                row.add(label);
                row.add(btnDetails);

                vehicleListPanel.add(row);
            }
        }
        vehicleListPanel.revalidate();
        vehicleListPanel.repaint();
    }
}

class MotorTile extends JPanel {

    public MotorTile(Pojazd p) {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 1), // Szara ramka
                new EmptyBorder(15, 15, 15, 15) // Wewnętrzny margines (padding)
        ));

        // Wymuszenie preferowanego rozmiaru (opcjonalne, ale pomaga w GridLayout)
        setPreferredSize(new Dimension(200, 120));

        // Górna część: Nazwa
        JLabel nameLabel = new JLabel(p.getModel());
        nameLabel.setFont(new Font("Arial", Font.BOLD, 16));
        nameLabel.setForeground(new Color(50, 50, 50));
        add(nameLabel, BorderLayout.NORTH);

//        // Środek: Szczegóły
//        JTextArea details = new JTextArea("Typ: " + .typ + "\nSilnik: " + motor.pojemnosc);
//        details.setFont(new Font("Segoe UI", Font.PLAIN, 14));
//        details.setForeground(Color.GRAY);
//        details.setEditable(false);
//        details.setOpaque(false); // Przezroczyste tło
//        details.setBorder(new EmptyBorder(10, 0, 10, 0));
//        add(details, BorderLayout.CENTER);

        // Dół: Przycisk akcji
        JButton btn = new JButton("Szczegóły");
        btn.setFocusable(false);
        add(btn, BorderLayout.SOUTH);
    }
}