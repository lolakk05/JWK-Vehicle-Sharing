package frontend;

import backend.ServiceVehicle;
import pojazd.Pojazd;
import wypozyczenie.Status;
import wypozyczenie.Wypozyczenie;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Objects;

public class RemoveVehiclePanel extends JPanel {
    private MainFrame mainFrame;
    private ServiceVehicle serviceVehicle;
    private JPanel vehicleListPanel;
    private JPanel containerPanel;

    private Pojazd currentVehicle;

    public RemoveVehiclePanel(MainFrame mainFrame, ServiceVehicle serviceVehicle) {
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

        add(optionsPanel, BorderLayout.CENTER);

        containerPanel = new JPanel();

        vehicleListPanel = new JPanel();
        vehicleListPanel.setLayout(new BoxLayout(vehicleListPanel, BoxLayout.Y_AXIS));

        JScrollPane scrollPane = new JScrollPane(vehicleListPanel);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        containerPanel.add(scrollPane);

        refreshList();

        containerPanel.add(vehicleListPanel);

        add(containerPanel, BorderLayout.CENTER);
    }

    public void refreshList() {
        vehicleListPanel.removeAll();

        ArrayList<Pojazd> pojazdy = new ArrayList<>(serviceVehicle.getVehicles());

        int freeVehicles = 0;
        for(Pojazd p: pojazdy){
            if(Objects.equals(p.getStatus(), "wolny")){
                freeVehicles++;
            }
        }

        if(freeVehicles == 0) {
            JLabel emptyLabel = new JLabel("Brak wolnych pojazdów do usunięcia");
            vehicleListPanel.add(emptyLabel);
        } else {
            for (Pojazd p : pojazdy) {
                if (Objects.equals(p.getStatus(), "wolny")) {
                    JPanel row = new JPanel();
                    row.setLayout(new BorderLayout(5, 10));
                    row.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY),
                        BorderFactory.createEmptyBorder(10, 15, 10, 15)
                    ));
                    row.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));
                    row.setAlignmentX(Component.LEFT_ALIGNMENT);

                    JPanel infoPanel = new JPanel();
                    infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
                    
                    JLabel vehicleLabel = new JLabel(p.getMarka() + " " + p.getModel() + " " + p.getRokProdukcji());
                    vehicleLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
                    
                    JLabel statusLabel = new JLabel("Status: " + p.getStatus());
                    statusLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
                    statusLabel.setForeground(new Color(60, 120, 60));
                    
                    infoPanel.add(vehicleLabel);
                    infoPanel.add(Box.createVerticalStrut(5));
                    infoPanel.add(statusLabel);

                    row.add(infoPanel, BorderLayout.CENTER);

                    JButton btnDelete = new JButton("Usuń");
                    btnDelete.setPreferredSize(new Dimension(200, 35));
                    btnDelete.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            ServiceVehicle.removeVehicle(p);
                            refreshList();
                        }
                    });
                    JPanel buttonPanelRow = new JPanel();
                    buttonPanelRow.setLayout(new FlowLayout(FlowLayout.RIGHT));
                    buttonPanelRow.add(btnDelete);
                    row.add(buttonPanelRow, BorderLayout.EAST);

                    vehicleListPanel.add(row);
                }
            }
        }
        vehicleListPanel.revalidate();
        vehicleListPanel.repaint();
    }
}