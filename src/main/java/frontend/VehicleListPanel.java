package frontend;

import app.Main;
import app.Session;
import osoba.Klient;
import pojazd.Pojazd;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Objects;

public class VehicleListPanel extends JPanel {
    private MainFrame mainFrame;
    private JPanel vehicleListPanel;

    private Klient currentClient;
    private JLabel nameLabel;

    public void getClientData() {
        currentClient = Session.getCurrentUser();
        nameLabel.setText("Zalogowano jako: " + currentClient.getImie());
    }

    public VehicleListPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout(10, 10));
        topPanel.add(new JLabel("Lista dostępnych pojazdów"), BorderLayout.WEST);

        nameLabel = new JLabel();
        topPanel.add(nameLabel, BorderLayout.CENTER);

        JButton goToUser =  new JButton("Profil");
        goToUser.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainFrame.ChangeCard("USER");
            }
        });
        JButton backButton = new JButton("Wyloguj się");
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Session.logout();
                mainFrame.ChangeCard("LOGIN");
            }
        });
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(goToUser);
        buttonPanel.add(backButton);
        topPanel.add(buttonPanel, BorderLayout.EAST);
        add(topPanel, BorderLayout.NORTH);

        vehicleListPanel = new JPanel();
        vehicleListPanel.setLayout(new BoxLayout(vehicleListPanel, BoxLayout.Y_AXIS));

        JScrollPane scrollPane = new JScrollPane(vehicleListPanel);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        add(scrollPane, BorderLayout.CENTER);

        refreshList();
    }

    public void refreshList() {
        vehicleListPanel.removeAll();

        ArrayList<Pojazd> pojazdy = Main.vehicles;

        for (Pojazd p : pojazdy) {
            if(Objects.equals(p.getStatus(), "wolny")) {
                System.out.println(p);
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
