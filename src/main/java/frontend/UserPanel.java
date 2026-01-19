package frontend;

import backend.*;
import obserwator.StatsControler;
import osoba.Klient;
import pojazd.Pojazd;
import wypozyczenie.Status;
import wypozyczenie.Wypozyczenie;
import zlecenieNaprawy.ZlecenieNaprawy;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;
import java.util.concurrent.Flow;

public class UserPanel extends JPanel {
    private MainFrame mainFrame;
    private ServiceUser serviceUser;
    private ServiceRental serviceRental;
    private ServiceWorker serviceWorker;
    private Klient currentClient;
    private ServiceVehicle serviceVehicle;
    private StatsControler statsControler;

    private JLabel balanceLabel;

    private JPanel rentalListPanel;
    private JLabel nameLabel;
    
    private JTextField searchField;
    private JComboBox<String> statusFilter;
    private String currentSortOrder = "date_desc"; 

    private JPanel statsPanel;

    public void getUserData() {
        currentClient =(Klient) Session.getCurrentUser();
        if (currentClient != null) {
            nameLabel.setText("Zalogowano jako: " + currentClient.getImie());
            balanceLabel.setText("Saldo: " + String.valueOf(currentClient.getSaldo() + " PLN"));
        }
    }

    public UserPanel(MainFrame mainFrame, ServiceUser serviceUser, ServiceRental serviceRental, ServiceWorker serviceWorker, ServiceVehicle serviceVehicle, StatsControler statsControler) {
        this.mainFrame = mainFrame;
        this.serviceUser = serviceUser;
        this.serviceRental = serviceRental;
        this.serviceWorker = serviceWorker;
        this.serviceVehicle = serviceVehicle;
        this.statsControler = statsControler;
        
        setLayout(new BorderLayout());
        
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 15, 10));
        menuPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10),
                            BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY)));

        nameLabel = new JLabel("Username: ..");
        nameLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        balanceLabel = new JLabel("Balance: 0");
        balanceLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));

        JTextField balanceField = new JTextField(10);
        JButton balanceButton = new JButton("Doładuj konto");
        balanceButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int sum = Integer.parseInt(balanceField.getText());
                currentClient.setSaldo(sum);
                getUserData();
                serviceUser.clientSaveData();
                balanceField.setText(null);
            }
        });

        JButton rentButton = new JButton("Wypożycz pojazd");
        rentButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainFrame.ChangeCard("RENT");
                int[] tab = statsControler.getStats();
            }
        });

        JButton logoutButton = new JButton("Wyloguj");
        logoutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Session.logout();
                mainFrame.ChangeCard("LOGIN");
            }
        });

        menuPanel.add(nameLabel);
        menuPanel.add(new JLabel(" | "));
        menuPanel.add(balanceLabel);
        menuPanel.add(new JLabel(" | "));
        menuPanel.add(new JLabel("Doładuj:"));
        menuPanel.add(balanceField);
        menuPanel.add(balanceButton);
        menuPanel.add(rentButton);
        menuPanel.add(logoutButton);
        
        menuPanel.setPreferredSize(new Dimension(800, 100));

        add(menuPanel, BorderLayout.NORTH);

        JPanel filterPanel = new JPanel();
        filterPanel.setLayout(new GridLayout(2, 1, 5, 5));
        filterPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel topRow = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel searchLabel = new JLabel("Szukaj:");
        searchField = new JTextField(20);
        searchField.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            public void changedUpdate(javax.swing.event.DocumentEvent e) { refreshRentalList(); }
            public void removeUpdate(javax.swing.event.DocumentEvent e) { refreshRentalList(); }
            public void insertUpdate(javax.swing.event.DocumentEvent e) { refreshRentalList(); }
        });
        
        JLabel filterLabel = new JLabel("Status:");
        String[] status = {"Wszystkie", Status.OCZEKUJACE.getDisplayName(), Status.AKTYWNE.getDisplayName(), Status.ZAKONCZONE.getDisplayName()};
        statusFilter = new JComboBox<>(status);
        statusFilter.addActionListener(e -> refreshRentalList());
        
        topRow.add(searchLabel);
        topRow.add(searchField);
        topRow.add(filterLabel);
        topRow.add(statusFilter);

        JPanel bottomRow = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel sortLabel = new JLabel("Sortuj:");
        JButton sortDateDesc = new JButton("Data ↓");
        sortDateDesc.addActionListener(e -> {
            currentSortOrder = "date_desc";
            refreshRentalList();
        });
        JButton sortDateAsc = new JButton("Data ↑");
        sortDateAsc.addActionListener(e -> {
            currentSortOrder = "date_asc";
            refreshRentalList();
        });
        JButton sortPriceDesc = new JButton("Cena ↓");
        sortPriceDesc.addActionListener(e -> {
            currentSortOrder = "price_desc";
            refreshRentalList();
        });
        JButton sortPriceAsc = new JButton("Cena ↑");
        sortPriceAsc.addActionListener(e -> {
            currentSortOrder = "price_asc";
            refreshRentalList();
        });
        
        bottomRow.add(sortLabel);
        bottomRow.add(sortDateDesc);
        bottomRow.add(sortDateAsc);
        bottomRow.add(sortPriceDesc);
        bottomRow.add(sortPriceAsc);

        filterPanel.add(topRow);
        filterPanel.add(bottomRow);

        rentalListPanel = new JPanel();
        rentalListPanel.setLayout(new BoxLayout(rentalListPanel, BoxLayout.Y_AXIS));
        rentalListPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout());
        centerPanel.add(filterPanel, BorderLayout.NORTH);
        
        JScrollPane rentalScrollPane = new JScrollPane(rentalListPanel);
        rentalScrollPane.getVerticalScrollBar().setUnitIncrement(16);
        rentalScrollPane.setBorder(BorderFactory.createEmptyBorder());
        
        centerPanel.add(rentalScrollPane, BorderLayout.CENTER);

        add(centerPanel, BorderLayout.CENTER);

        refreshRentalList();
    }

    public void refreshRentalList() {
        rentalListPanel.removeAll();

        if (currentClient == null) {
            currentClient = (Klient) Session.getCurrentUser();
        }

        if (currentClient == null) {
            return; 
        }

        JLabel titleLabel = new JLabel("Twoje wypożyczenia:");
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 15, 5));
        titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        rentalListPanel.add(titleLabel);

        String searchText = searchField != null ? searchField.getText() : "";
        String selectedStatus = statusFilter != null ? (String) statusFilter.getSelectedItem() : "Wszystkie";
        
        ArrayList<Wypozyczenie> filteredRentals = serviceRental.getFilteredAndSortedRentals(
            currentClient, 
            searchText, 
            selectedStatus, 
            currentSortOrder
        );
        
        boolean hasRentals = !filteredRentals.isEmpty();
        for (Wypozyczenie r: filteredRentals) {
                Pojazd p = r.getPojazd();
                
                JPanel row = new JPanel();
                row.setLayout(new BorderLayout(10, 5));
                row.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY),
                    BorderFactory.createEmptyBorder(10, 15, 10, 15)
                ));
                row.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));
                row.setAlignmentX(Component.LEFT_ALIGNMENT);

                JPanel infoPanel = new JPanel();
                infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
                
                JLabel vehicleLabel = new JLabel(p.getMarka() + " " + p.getModel() + ", Początek: " + r.getDataRozpoczecia() + " Koniec: " + r.getDataZakonczenia());
                vehicleLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
                
                JLabel priceLabel = new JLabel("Koszt: " + String.format("%.2f", r.getKosztKoncowy()) + " PLN" + " Status: " + r.getStatus().getDisplayName());
                priceLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
                if(r.getStatus().equals(Status.ZAKONCZONE)) {
                    priceLabel.setForeground(new Color(255, 0, 0));
                }
                else if(r.getStatus().equals(Status.OCZEKUJACE)) {
                    priceLabel.setForeground(new Color(255, 165, 0));
                }
                else {
                    priceLabel.setForeground(new Color(60, 120, 60));
                }
                
                
                infoPanel.add(vehicleLabel);
                infoPanel.add(Box.createVerticalStrut(5));
                infoPanel.add(priceLabel);

                JPanel actionPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
                actionPanel.setOpaque(false);

                if (r.getStatus() == Status.OCZEKUJACE) {
                    JButton btnReturn = new JButton("Anuluj");
                    btnReturn.setPreferredSize(new Dimension(120, 35));
                    btnReturn.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            serviceRental.cancelRental(r);
                            serviceRental.getRepositoryRental().save();
                            serviceVehicle.zwolnijPojazd(r.getPojazd());
                            statsControler.update(r.getPojazd(),1);
                            refreshRentalList();
                        }
                    });
                    actionPanel.add(btnReturn);
                }

                if (r.getStatus() == Status.AKTYWNE) {
                    JButton btnReturn = new JButton("Zwróć");
                    btnReturn.setPreferredSize(new Dimension(120, 35));
                    btnReturn.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            serviceRental.returnRental(r);
                            serviceVehicle.zwolnijPojazd(r.getPojazd());
                            serviceRental.getRepositoryRental().save();
                            statsControler.update(r.getPojazd(),1);
                            refreshRentalList();
                        }
                    });
                    actionPanel.add(btnReturn);
                    JButton btnRepair = new JButton("Zgłoś naprawę");
                    btnRepair.setPreferredSize(new Dimension(120, 35));
                    btnRepair.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            String opis = JOptionPane.showInputDialog(null, "Opisz usterkę:");
                            if (opis != null && !opis.isEmpty()) {
                                ZlecenieNaprawy zlecenie = new ZlecenieNaprawy(opis, new Date(), 0.0, r.getPojazd());
                                serviceWorker.dodajZlecenie(zlecenie);
                                r.setStatus(Status.W_NAPRAWIE);
                                serviceRental.getRepositoryRental().save();
                                JOptionPane.showMessageDialog(null, "Zgłoszono naprawę dla pojazdu: " + p.getMarka() + " " + p.getModel());
                                refreshRentalList();
                            }
                        }
                    });
                    actionPanel.add(btnRepair);
                }

                row.add(infoPanel, BorderLayout.CENTER);
                row.add(actionPanel, BorderLayout.EAST);

                rentalListPanel.add(row);
        }
        
        if (!hasRentals) {
            JLabel noRentalsLabel = new JLabel("Brak wypożyczeń spełniających kryteria");
            noRentalsLabel.setFont(new Font("SansSerif", Font.ITALIC, 14));
            noRentalsLabel.setForeground(Color.GRAY);
            noRentalsLabel.setBorder(BorderFactory.createEmptyBorder(20, 15, 20, 15));
            rentalListPanel.add(noRentalsLabel);
        }
        
        rentalListPanel.revalidate();
        rentalListPanel.repaint();
    }
}
