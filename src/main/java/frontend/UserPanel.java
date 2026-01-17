package frontend;

import backend.ServiceRental;
import backend.ServiceUser;
import backend.Session;
import osoba.Klient;
import pojazd.Pojazd;
import wypozyczenie.Wypozyczenie;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.Flow;

public class UserPanel extends JPanel {
    private MainFrame mainFrame;
    private ServiceUser serviceUser;
    private ServiceRental serviceRental;
    private Klient currentClient;

    private JLabel nameLabel;
    private JLabel balanceLabel;

    private JPanel rentalListPanel;

    public void getUserData() {
        currentClient =(Klient) Session.getCurrentUser();
        if (currentClient != null) {
            nameLabel.setText("Zalogowano jako: " + currentClient.getImie());
            balanceLabel.setText("Saldo: " + String.valueOf(currentClient.getSaldo() + " PLN"));
        }
    }

    public UserPanel(MainFrame mainFrame, ServiceUser serviceUser, ServiceRental serviceRental) {
        this.mainFrame = mainFrame;
        this.serviceUser = serviceUser;
        this.serviceRental = serviceRental;
        
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
            }
        });

        JButton rentButton = new JButton("Wypożycz pojazd");
        rentButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainFrame.ChangeCard("RENT");
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

        rentalListPanel = new JPanel();
        rentalListPanel.setLayout(new BoxLayout(rentalListPanel, BoxLayout.Y_AXIS));
        rentalListPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        JLabel rentalLabel = new JLabel("Twoje oczekujące wypożyczenia:");
        rentalLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        rentalLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 15, 5));
        rentalListPanel.add(rentalLabel);

        JScrollPane rentalScrollPane = new JScrollPane(rentalListPanel);
        rentalScrollPane.getVerticalScrollBar().setUnitIncrement(16);
        rentalScrollPane.setBorder(BorderFactory.createEmptyBorder());

        add(rentalScrollPane, BorderLayout.CENTER);

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

        ArrayList<Wypozyczenie> rental = new ArrayList<Wypozyczenie>(serviceRental.getRentals());
        
        boolean hasRentals = false;
        for (Wypozyczenie r: rental) {
            if(r.getKlient().getEmail().equals(currentClient.getEmail())) {
                hasRentals = true;
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
                
                JLabel priceLabel = new JLabel("Koszt: " + String.format("%.2f", r.getKosztKoncowy()) + " PLN" + " Status: " + r.getStatus());
                priceLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
                priceLabel.setForeground(new Color(60, 120, 60));
                
                infoPanel.add(vehicleLabel);
                infoPanel.add(Box.createVerticalStrut(5));
                infoPanel.add(priceLabel);

                JButton btnReturn = new JButton("Zwróć pojazd");
                btnReturn.setPreferredSize(new Dimension(120, 35));
                btnReturn.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        serviceRental.returnRental(r);
                        serviceRental.getRepositoryRental().save();
                        refreshRentalList();
                    }
                });

                row.add(infoPanel, BorderLayout.CENTER);
                row.add(btnReturn, BorderLayout.EAST);

                rentalListPanel.add(row);
            }
        }
        
        if (!hasRentals) {
            JLabel noRentalsLabel = new JLabel("Brak wypożyczeń");
            noRentalsLabel.setFont(new Font("SansSerif", Font.ITALIC, 14));
            noRentalsLabel.setForeground(Color.GRAY);
            noRentalsLabel.setBorder(BorderFactory.createEmptyBorder(20, 15, 20, 15));
            rentalListPanel.add(noRentalsLabel);
        }
        
        rentalListPanel.revalidate();
        rentalListPanel.repaint();
    }
}
