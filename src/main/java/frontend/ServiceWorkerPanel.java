package frontend;

import backend.ServiceWorker;
import backend.Session;
import osoba.Serwisant;
import pojazd.Rower;
import pojazd.SamochodOsobowy;
import zlecenieNaprawy.ZlecenieNaprawy;
import pojazd.Pojazd;

import javax.swing.*;
import java.awt.*;
import java.util.Date;
import java.util.List;

public class ServiceWorkerPanel extends JPanel {
    private MainFrame mainFrame;
    private ServiceWorker serviceWorker;
    private Serwisant currentSerwisant;

    private JLabel welcomeLabel;
    private DefaultListModel<ZlecenieNaprawy> freeOrdersModel;
    private JList<ZlecenieNaprawy> freeOrdersList;
    private DefaultListModel<ZlecenieNaprawy> myOrdersModel;
    private JList<ZlecenieNaprawy> myOrdersList;

    public ServiceWorkerPanel(MainFrame mainFrame, ServiceWorker serviceWorker) {
        this.mainFrame = mainFrame;
        this.serviceWorker = serviceWorker;

        setLayout(new BorderLayout(10, 10));

        // Top panel
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        welcomeLabel = new JLabel("Panel Serwisanta");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 16));
        JButton logoutButton = new JButton("Wyloguj");
        logoutButton.addActionListener(e -> {
            Session.logout();
            mainFrame.ChangeCard("LOGIN");
        });
        topPanel.add(welcomeLabel);
        topPanel.add(logoutButton);
        add(topPanel, BorderLayout.NORTH);

        // Center panel with two lists
        JPanel centerPanel = new JPanel(new GridLayout(1, 2, 10, 10));

        // Free orders
        JPanel freeOrdersPanel = new JPanel(new BorderLayout());
        freeOrdersPanel.setBorder(BorderFactory.createTitledBorder("Wolne zgłoszenia"));
        freeOrdersModel = new DefaultListModel<>();
        freeOrdersList = new JList<>(freeOrdersModel);
        freeOrdersPanel.add(new JScrollPane(freeOrdersList), BorderLayout.CENTER);
        JButton pickButton = new JButton("Przyjmij zlecenie");
        pickButton.addActionListener(e -> pickOrder());
        freeOrdersPanel.add(pickButton, BorderLayout.SOUTH);


        // My orders
        JPanel myOrdersPanel = new JPanel(new BorderLayout());
        myOrdersPanel.setBorder(BorderFactory.createTitledBorder("Moje aktywne zlecenia"));
        myOrdersModel = new DefaultListModel<>();
        myOrdersList = new JList<>(myOrdersModel);
        myOrdersPanel.add(new JScrollPane(myOrdersList), BorderLayout.CENTER);
        JButton finishButton = new JButton("Zakończ naprawę");
        finishButton.addActionListener(e -> finishOrder());
        myOrdersPanel.add(finishButton, BorderLayout.SOUTH);

        centerPanel.add(freeOrdersPanel);
        centerPanel.add(myOrdersPanel);
        add(centerPanel, BorderLayout.CENTER);
    }

    public void refreshData() {
        if (Session.getCurrentUser() instanceof Serwisant) {
            currentSerwisant = (Serwisant) Session.getCurrentUser();
            welcomeLabel.setText("Serwisant: " + currentSerwisant.getImie() + " " + currentSerwisant.getNazwisko());
        }

        freeOrdersModel.clear();
        List<ZlecenieNaprawy> freeOrders = serviceWorker.getWolneZlecenia();
        for (ZlecenieNaprawy z : freeOrders) {
            freeOrdersModel.addElement(z);
        }

        myOrdersModel.clear();
        if (currentSerwisant != null) {
            List<ZlecenieNaprawy> myOrders = serviceWorker.getZleceniaSerwisanta(currentSerwisant);
            for (ZlecenieNaprawy z : myOrders) {
                myOrdersModel.addElement(z);
            }
        }
    }

    private void pickOrder() {
        ZlecenieNaprawy selected = freeOrdersList.getSelectedValue();
        if (selected != null && currentSerwisant != null) {
            serviceWorker.przypiszZlecenie(selected, currentSerwisant);
            refreshData();
        } else {
            JOptionPane.showMessageDialog(this, "Wybierz zlecenie z listy wolnych zgłoszeń.");
        }
    }

    private void finishOrder() {
        ZlecenieNaprawy selected = myOrdersList.getSelectedValue();
        if (selected != null) {
            serviceWorker.zakonczZlecenie(selected);
            refreshData();
        } else {
            JOptionPane.showMessageDialog(this, "Wybierz zlecenie z listy Twoich zleceń.");
        }
    }
}
