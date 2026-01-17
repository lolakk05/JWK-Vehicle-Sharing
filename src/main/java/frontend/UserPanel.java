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
        currentClient = Session.getCurrentUser();
        if (currentClient != null) {
            nameLabel.setText("Imię: " + currentClient.getImie());
            balanceLabel.setText(String.valueOf(currentClient.getSaldo()));
        }
    }

    public UserPanel(MainFrame mainFrame, ServiceUser serviceUser, ServiceRental serviceRental) {
        this.mainFrame = mainFrame;
        this.serviceUser = serviceUser;
        this.serviceRental = serviceRental;
        
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new FlowLayout());
        menuPanel.setBorder(getBorder());

        nameLabel = new JLabel("Username: ..");
        balanceLabel = new JLabel("Balance: 0");

        JTextField balanceField = new JTextField(15);
        JButton balanceButton = new JButton("Doładuj konto");
        balanceButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int sum = Integer.parseInt(balanceField.getText());
                currentClient.setSaldo(sum);
                getUserData();
                serviceUser.clientSaveData();
            }
        });

        JButton rentButton = new JButton("Wypożycz");
        rentButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainFrame.ChangeCard("RENT");
            }
        });

        JButton logoutButton = new JButton("Wyloguj Się");
        logoutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Session.logout();
                mainFrame.ChangeCard("LOGIN");
            }
        });

        menuPanel.add(nameLabel);
        menuPanel.add(balanceLabel);

        menuPanel.add(balanceField);
        menuPanel.add(balanceButton);
        menuPanel.add(rentButton);
        menuPanel.add(logoutButton);

        add(menuPanel, BorderLayout.NORTH);

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new GridLayout(2, 1));

        rentalListPanel = new JPanel();
        rentalListPanel.setLayout(new BoxLayout(rentalListPanel, BoxLayout.Y_AXIS));
        JLabel rentalLabel = new JLabel("Twoje wypożyczenia:");
        rentalListPanel.add(rentalLabel);

        JScrollPane rentalScrollPane = new JScrollPane(rentalListPanel);
        rentalScrollPane.getVerticalScrollBar().setUnitIncrement(16);

        add(rentalScrollPane, BorderLayout.CENTER);

        contentPanel.add(rentalListPanel);
        add(contentPanel, BorderLayout.CENTER);

        refreshRentalList();
    }

    public void refreshRentalList() {
        rentalListPanel.removeAll();

        if (currentClient == null) {
            currentClient = Session.getCurrentUser();
        }

        if (currentClient == null) {
            return; // Nie ma zalogowanego użytkownika
        }

        ArrayList<Wypozyczenie> rental = new ArrayList<>(serviceRental.getRepositoryRental().getAwaitingRentals());
        System.out.println("Ilość wypożyczeń: " + rental.size());
        System.out.println("Email obecnego użytkownika: " + currentClient.getEmail());

        for (Wypozyczenie r: rental) {
            Pojazd p = r.getPojazd();
            System.out.println("Email z wypożyczenia: " + r.getKlient().getEmail());
            if(r.getKlient().getEmail().equals(currentClient.getEmail())) {
                JPanel row = new JPanel(new FlowLayout(FlowLayout.LEFT));
                row.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));

                JLabel label = new JLabel(p.getMarka() + " " + p.getModel() + " | Koszt: " + String.format("%.2f", r.getKosztKoncowy()) + " PLN");
                JButton btnDetails = new JButton("Zwróć");
                btnDetails.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        serviceRental.returnRental(r);
                        serviceRental.getRepositoryRental().saveAwaiting();
                        serviceRental.getRepositoryRental().save();
                        refreshRentalList();
                    }
                });

                row.add(label);
                row.add(btnDetails);

                rentalListPanel.add(row);
            }
        }
        rentalListPanel.revalidate();
        rentalListPanel.repaint();
    }
}
