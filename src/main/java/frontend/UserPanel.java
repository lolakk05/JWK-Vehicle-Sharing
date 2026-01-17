package frontend;

import backend.ServiceUser;
import backend.Session;
import osoba.Klient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.Flow;

public class UserPanel extends JPanel {
    private MainFrame mainFrame;
    private ServiceUser serviceUser;
    private Klient currentClient;

    private JLabel nameLabel;
    private JLabel balanceLabel;

    public void getUserData() {
        if (Session.getCurrentUser() instanceof Klient) {
            currentClient = (Klient) Session.getCurrentUser();
            nameLabel.setText("Imię: " + currentClient.getImie());
            balanceLabel.setText(String.valueOf(currentClient.getSaldo()));
        }
    }

    public UserPanel(MainFrame mainFrame, ServiceUser serviceUser) {
        this.mainFrame = mainFrame;
        this.serviceUser = serviceUser;

        JPanel userPanel = new JPanel();
        
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new FlowLayout());

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

        userPanel.add(menuPanel, BorderLayout.NORTH);

        add(userPanel);
    }
}
