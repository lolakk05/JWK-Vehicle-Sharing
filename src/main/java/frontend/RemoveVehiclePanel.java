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
                    JPanel row = new JPanel(new FlowLayout(FlowLayout.LEFT));
                    row.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));

                    JLabel label = new JLabel(p.getMarka() + " " + p.getModel() + " (" + p.getStatus() + ")");
                    JButton deleteButton = new JButton("Usuń");
                    deleteButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            ServiceVehicle.removeVehicle(p);
                            refreshList();
                        }
                    });

                    row.add(label);
                    row.add(deleteButton);

                    vehicleListPanel.add(row);
                }
            }
        }
        vehicleListPanel.revalidate();
        vehicleListPanel.repaint();
    }
}