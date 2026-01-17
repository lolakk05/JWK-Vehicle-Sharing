package frontend;

import backend.RepositoryRental;
import backend.ServiceRental;
import pojazd.Pojazd;
import wypozyczenie.Status;
import wypozyczenie.Wypozyczenie;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Objects;

public class AcceptLoanPanel extends JPanel {
    private MainFrame mainFrame;
    private JPanel vehicleListPanel;
    private ServiceRental serviceRental;

    public AcceptLoanPanel(MainFrame mainFrame, ServiceRental serviceRental) {
        this.mainFrame = mainFrame;
        this.serviceRental = serviceRental;
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

        JButton btnDodajPojazd = new JButton("Dodaj pojazd ▼");
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

        JPanel containerPanel = new JPanel();

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

        ArrayList<Wypozyczenie> rentals  = new ArrayList<>(serviceRental.getRentals());

        int rentalsWaiting = 0;
        for(Wypozyczenie wp : rentals){
            if(wp.getStatus() == Status.OCZEKUJACE){
                rentalsWaiting++;
            }
        }

        if(rentalsWaiting == 0){
            JLabel emptyLabel = new JLabel("Brak oczekujących zamówień");
            vehicleListPanel.add(emptyLabel);
        } else {
            for (Wypozyczenie wp : rentals) {
                if (Objects.equals(wp.getStatus(), Status.OCZEKUJACE)) {
                    JPanel row = new JPanel(new FlowLayout(FlowLayout.LEFT));
                    row.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));

                    Pojazd currentVehicle = wp.getPojazd();

                    JLabel label = new JLabel(currentVehicle.getMarka() + " " + currentVehicle.getModel() + " " + wp.getDataRozpoczecia() + " - " + wp.getDataZakonczenia());
                    JButton deleteButton = new JButton("Akceptuj");
                    deleteButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            serviceRental.acceptRental(wp);
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
