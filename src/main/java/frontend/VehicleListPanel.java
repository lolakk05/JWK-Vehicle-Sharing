package frontend;

import backend.RepositoryWorker;
import backend.ServiceVehicle;
import backend.Session;
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
    private ServiceVehicle serviceVehicle;
    private JPanel vehicleListPanel;

    private Klient currentClient;
    private JLabel nameLabel;

    public void getClientData() {
        if (Session.getCurrentUser() instanceof Klient) {
            currentClient = (Klient) Session.getCurrentUser();
            nameLabel.setText("Zalogowano jako: " + currentClient.getImie());
        }
    }

    public VehicleListPanel(MainFrame mainFrame, ServiceVehicle serviceVehicle) {
        this.mainFrame = mainFrame;
        this.serviceVehicle = serviceVehicle;

        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout(10, 10));
        topPanel.add(new JLabel("Lista dostępnych pojazdów"), BorderLayout.WEST);

        nameLabel = new JLabel();
        topPanel.add(nameLabel, BorderLayout.CENTER);

        JButton backButton = new JButton("Powrót");
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainFrame.ChangeCard("USER");
            }
        });
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
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

        ArrayList<Pojazd> pojazdy = new ArrayList<>(serviceVehicle.getVehicles());

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
