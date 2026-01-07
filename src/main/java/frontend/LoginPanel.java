package frontend;

import app.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPanel extends JPanel {
    private MainFrame mainFrame;
    private Main loginManager;

    public LoginPanel(MainFrame mainFrame, Main loginManager) {
        this.mainFrame = mainFrame;
        this.loginManager = loginManager;
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
                if(Main.login(emailField.getText(), new String(passwordField.getPassword()))) {
                     mainFrame.ChangeCard("MAIN");
                }
                else {
                    JOptionPane.showMessageDialog(mainFrame, "Niepoprawny email lub hasło!");
                }
                if(emailField.getText().isEmpty() || passwordField.getText().isEmpty()){
                    JOptionPane.showMessageDialog(null, "Dane nie mogą być puste");
                }
            }
        });
        loginPanel.add(loginButton);

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
