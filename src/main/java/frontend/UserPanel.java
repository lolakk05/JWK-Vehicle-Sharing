package frontend;

import app.Main;
import app.Session;
import osoba.Klient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserPanel extends JPanel {
    private MainFrame mainFrame;
    private Main appLogic;
    private Klient currentClient;

    private JLabel nameLabel;
    private JLabel balanceLabel;

    public void getUserData() {
        currentClient = Session.getCurrentUser();
        nameLabel.setText("Imię: " + currentClient.getImie());
        balanceLabel.setText(String.valueOf(currentClient.getSaldo()));
    }

    public UserPanel(MainFrame mainFrame, Main appLogic) {
        this.mainFrame = mainFrame;

        setLayout(new GridBagLayout());

        JPanel userPanel = new JPanel();
        userPanel.setLayout(new GridBagLayout());

        nameLabel = new JLabel("Username: ..");
        balanceLabel = new JLabel("Balance: 0");

        JTextField balanceField = new JTextField(15);
        JButton balanceButton = new JButton("Doładuj konto");
        balanceButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int sum = Integer.parseInt(balanceField.getText());
                currentClient.setSaldo(sum);
                getUserData();
                appLogic.saveClients();
            }
        });

        JButton returnButton = new JButton("POWRUT");
        returnButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainFrame.ChangeCard("MAIN");
            }
        });

        userPanel.add(nameLabel);
        userPanel.add(balanceLabel);

        userPanel.add(balanceField);
        userPanel.add(balanceButton);

        userPanel.add(returnButton);

        userPanel.add(new JLabel("W budowie..."));

        add(userPanel);
    }
}
