package frontend;

import osoba.EmailException;
import osoba.PeselException;
import osoba.PhoneNumberException;
import osoba.Serwisant;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static app.Main.workers;

public class AcceptLoanPanel extends JPanel {
    private MainFrame mainFrame;

    public AcceptLoanPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        setLayout(new FlowLayout());

        JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(new GridLayout(1,4));

        JButton acceptButton = new JButton("Strona główna");
        acceptButton.setSize(new Dimension(20,20));
        acceptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.ChangeCard("ACCEPT_LOAN");
            }
        });

        JButton addVehicleButton = new JButton("Dodaj pojazd");
        addVehicleButton.setSize(new Dimension(20,20));
        addVehicleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.ChangeCard("ADD_VEHICLE_PANEL");
            }
        });

        JButton removeVehicleButton = new JButton("Usuń pojazd");
        removeVehicleButton.setSize(new Dimension(20,20));
        removeVehicleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.ChangeCard("REMOVE_VEHICLE_PANEL");
            }
        });

        JButton addWorkerButton = new JButton("Dodaj serwisanta");
        addWorkerButton.setSize(new Dimension(20,20));
        addWorkerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.ChangeCard("ADD_WORKER_PANEL");
            }
        });

        JButton logoutButton = new JButton("Wyloguj");
        logoutButton.setSize(new Dimension(20,20));
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
        add(addWorkerPanel, BorderLayout.CENTER);
    }
}
