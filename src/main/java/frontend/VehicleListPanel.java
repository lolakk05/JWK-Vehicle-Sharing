package frontend;

import backend.RepositoryWorker;
import backend.ServiceVehicle;
import backend.Session;
import osoba.Klient;
import pojazd.Pojazd;
import wypozyczenie.Status;

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
    
    private JTextField searchField;
    private String currentSortOrder = "name_asc";

    public void getClientData() {
        if (Session.getCurrentUser() instanceof Klient) {
            currentClient = (Klient) Session.getCurrentUser();
        }
    }
    
    private int countAvailableVehicles() {
        ArrayList<Pojazd> pojazdy = new ArrayList<>(serviceVehicle.getVehicles());
        int count = 0;
        for (Pojazd p : pojazdy) {
            if (Objects.equals(p.getStatus(), "wolny")) {
                count++;
            }
        }
        return count;
    }

    public VehicleListPanel(MainFrame mainFrame, ServiceVehicle serviceVehicle) {
        this.mainFrame = mainFrame;
        this.serviceVehicle = serviceVehicle;

        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout(15, 10));
        topPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 20));
        
        JPanel titlePanel = new JPanel();
        titlePanel.setOpaque(false);
        
        JLabel titleLabel = new JLabel("Lista dostępnych pojazdów");
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        
        titlePanel.add(titleLabel);

        JButton backButton = new JButton("Powrót");
        backButton.setFont(new Font("SansSerif", Font.PLAIN, 12));
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainFrame.ChangeCard("USER");
            }
        });
        
        topPanel.add(titlePanel, BorderLayout.WEST);
        topPanel.add(backButton, BorderLayout.EAST);
        add(topPanel, BorderLayout.NORTH);

        JPanel filterPanel = new JPanel();
        filterPanel.setLayout(new GridLayout(2, 1, 5, 5));
        filterPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel topRow = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel searchLabel = new JLabel("Szukaj:");
        searchField = new JTextField(25);
        searchField.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            public void changedUpdate(javax.swing.event.DocumentEvent e) { refreshList(); }
            public void removeUpdate(javax.swing.event.DocumentEvent e) { refreshList(); }
            public void insertUpdate(javax.swing.event.DocumentEvent e) { refreshList(); }
        });
        
        topRow.add(searchLabel);
        topRow.add(searchField);

        JPanel bottomRow = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel sortLabel = new JLabel("Sortuj:");
        JButton sortNameAsc = new JButton("Nazwa ↑");
        sortNameAsc.addActionListener(e -> {
            currentSortOrder = "name_asc";
            refreshList();
        });
        JButton sortNameDesc = new JButton("Nazwa ↓");
        sortNameDesc.addActionListener(e -> {
            currentSortOrder = "name_desc";
            refreshList();
        });
        JButton sortPriceAsc = new JButton("Cena ↑");
        sortPriceAsc.addActionListener(e -> {
            currentSortOrder = "price_asc";
            refreshList();
        });
        JButton sortPriceDesc = new JButton("Cena ↓");
        sortPriceDesc.addActionListener(e -> {
            currentSortOrder = "price_desc";
            refreshList();
        });
        
        bottomRow.add(sortLabel);
        bottomRow.add(sortNameAsc);
        bottomRow.add(sortNameDesc);
        bottomRow.add(sortPriceAsc);
        bottomRow.add(sortPriceDesc);

        filterPanel.add(topRow);
        filterPanel.add(bottomRow);

        vehicleListPanel = new JPanel();
        vehicleListPanel.setLayout(new BoxLayout(vehicleListPanel, BoxLayout.Y_AXIS));

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout());
        centerPanel.add(filterPanel, BorderLayout.NORTH);
        
        JScrollPane scrollPane = new JScrollPane(vehicleListPanel);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        
        centerPanel.add(scrollPane, BorderLayout.CENTER);

        add(centerPanel, BorderLayout.CENTER);

        refreshList();
    }

    public void refreshList() {
        vehicleListPanel.removeAll();

        String searchText = searchField != null ? searchField.getText() : "";
        ArrayList<Pojazd> filteredVehicles = serviceVehicle.getFilteredAndSortedVehicles(searchText, currentSortOrder);

        for (Pojazd p : filteredVehicles) {
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
            
            JLabel priceLabel = new JLabel("Koszt: " + String.format("%.2f", p.getCenaBazowa()) + " PLN");
            priceLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
            priceLabel.setForeground(new Color(60, 120, 60));
            
            
            infoPanel.add(vehicleLabel);
            infoPanel.add(Box.createVerticalStrut(5));
            infoPanel.add(priceLabel);
            row.add(infoPanel, BorderLayout.CENTER);
            JButton btnReturn = new JButton("Zobacz szczegóły");
            btnReturn.setPreferredSize(new Dimension(200, 35));
            btnReturn.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    mainFrame.setVehicle(p);
                    mainFrame.ChangeCard("VEHICLE");
                }
            });
            row.add(btnReturn, BorderLayout.EAST);
            vehicleListPanel.add(row);
            }
        vehicleListPanel.revalidate();
        vehicleListPanel.repaint();
    }
};