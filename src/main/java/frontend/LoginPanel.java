package frontend;

import backend.ServiceUser;
import backend.ServiceWorker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPanel extends JPanel {
    private MainFrame mainFrame;
    private ServiceUser serviceUser;
    private ServiceWorker serviceWorker;

    public LoginPanel(MainFrame mainFrame, ServiceUser serviceUser, ServiceWorker serviceWorker) {
        this.mainFrame = mainFrame;
        this.serviceUser = serviceUser;
        this.serviceWorker = serviceWorker;
        setLayout(new GridBagLayout());

        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new GridLayout(7, 1, 10, 10));

        loginPanel.add(new JLabel("Email: "));
        JTextField emailField = new JTextField(15);
        loginPanel.add(emailField);

        loginPanel.add(new JLabel("Hasło: "));
        JPasswordField passwordField = new JPasswordField(15);
        loginPanel.add(passwordField);

        JButton loginButton = new JButton("Zaloguj się");
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (emailField.getText().equals("admin") && passwordField.getText().equals("admin")) {
                    mainFrame.ChangeCard("ACCEPT_LOAN");
                    emailField.setText(null);
                    passwordField.setText(null);
                }
                else if(serviceUser.login(emailField.getText(), new String(passwordField.getPassword()))) {
                     mainFrame.ChangeCard("USER");
                     emailField.setText(null);
                     passwordField.setText(null);
                }
                else if(serviceWorker.login(emailField.getText(), new String(passwordField.getPassword()))) {
                    mainFrame.ChangeCard("SERVICE_WORKER_PANEL");
                    emailField.setText(null);
                    passwordField.setText(null);
                }
                else if(emailField.getText().isEmpty() || passwordField.getText().isEmpty()){
                    JOptionPane.showMessageDialog(null, "Dane nie mogą być puste");
                }
                else {
                    JOptionPane.showMessageDialog(mainFrame, "Niepoprawny email lub hasło!");
                }
            }
        });
        loginPanel.add(loginButton);
        SwingUtilities.invokeLater(() -> {
            JRootPane root = SwingUtilities.getRootPane(this);
            if (root != null) {
                root.setDefaultButton(loginButton);
            }
        });

        loginPanel.add(new JLabel("Nie masz konta?"));
        JButton registerButton = new JButton("Zarejestruj się");
        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainFrame.ChangeCard("REGISTER");
            }
        });
        loginPanel.add(registerButton);

        add(loginPanel);
    }
}
