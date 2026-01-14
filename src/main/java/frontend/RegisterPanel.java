package frontend;

import app.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RegisterPanel extends JPanel {
    private CardLayout layout;
    private MainFrame mainFrame;
    private Main registerLogic;

    private JTextField driverLicenseNumber;
    private JCheckBox checkBox;
    private JCheckBox checkBox1;
    private JCheckBox checkBox2;
    private JCheckBox checkBox3;


    public void toggleLicense(boolean value) {
        driverLicenseNumber.setEnabled(value);
        checkBox.setEnabled(value);
        checkBox1.setEnabled(value);
        checkBox2.setEnabled(value);
        checkBox3.setEnabled(value);
    }

    public RegisterPanel(MainFrame mainFrame, Main registerLogic) {
        this.mainFrame = mainFrame;
        this.registerLogic = registerLogic;
        setLayout(new GridBagLayout());

        JPanel registerPanel = new JPanel();
        registerPanel.setLayout(new GridLayout(0, 2, 10, 10));

        registerPanel.add(new JLabel("Imie: "));
        JTextField name = new JTextField(15);
        registerPanel.add(name);

        registerPanel.add(new JLabel("Nazwisko: "));
        JTextField surname = new JTextField(15);
        registerPanel.add(surname);

        registerPanel.add(new JLabel("Pesel"));
        JTextField pesel =  new JTextField(15);
        registerPanel.add(pesel);

        registerPanel.add(new JLabel("Wiek:"));
        JTextField age = new JTextField(15);
        registerPanel.add(age);

        registerPanel.add(new JLabel("Email:"));
        JTextField email = new JTextField(15);
        registerPanel.add(email);

        registerPanel.add(new JLabel("Hasło:"));
        JPasswordField password = new JPasswordField(15);
        registerPanel.add(password);

        registerPanel.add(new JLabel("Numer telefonu:"));
        JTextField phone = new JTextField(15);
        registerPanel.add(phone);

        JPanel radioPanel =  new JPanel(new FlowLayout(FlowLayout.LEFT));
        registerPanel.add(new JLabel("Prawo jazdy: "));
        JRadioButton radioButton = new JRadioButton("Tak");
        radioButton.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {
               if(radioButton.isSelected()) {
                   toggleLicense(true);
               }
           }
        });
        JRadioButton radioButton1 = new JRadioButton("Nie", true);
        radioButton1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(radioButton1.isSelected()) {
                    toggleLicense(false);
                }
            }
        });
        radioPanel.add(radioButton);
        radioPanel.add(radioButton1);

        ButtonGroup radioGroup = new ButtonGroup();
        radioGroup.add(radioButton);
        radioGroup.add(radioButton1);

        registerPanel.add(radioPanel);

        registerPanel.add(new JLabel("Numer prawa jazdy: "));
        driverLicenseNumber = new JTextField(15);
        driverLicenseNumber.setEnabled(false);
        registerPanel.add(driverLicenseNumber);

        JPanel checkPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        registerPanel.add(new JLabel("Kategorie: "));
        checkBox = new JCheckBox("A");
        checkBox1 = new JCheckBox("B");
        checkBox2 = new JCheckBox("C+E");
        checkBox3 = new JCheckBox("Karta rowerowa");

        checkPanel.add(checkBox);
        checkPanel.add(checkBox1);
        checkPanel.add(checkBox2);
        checkPanel.add(checkBox3);

        Component[] components = checkPanel.getComponents();

        for(Component component : components){
            if(component instanceof JCheckBox){
                component.setEnabled(false);
            }
        }

        registerPanel.add(checkPanel);

        JButton returnButton = new JButton("Powrót");
        returnButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainFrame.ChangeCard("LOGIN");
            }
        });
        JButton registerButton = new JButton("Zarejestruj się");
        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                boolean hasLicense = radioButton.isSelected();
                List<String> categories = new ArrayList<>();
                for(Component component : components){
                    if(component instanceof JCheckBox){
                        if(((JCheckBox)component).isSelected()){
                            categories.add(((JCheckBox)component).getText());
                        }
                    }
                }

                Object[] user = {name.getText(), surname.getText(), pesel.getText(), age.getText(), email.getText(), new String(password.getPassword()), phone.getText(), hasLicense, driverLicenseNumber.getText(), categories};
                try {
                    registerLogic.registerClient(user);
                    JOptionPane.showMessageDialog(null, "Użytkownik zarejestrowany pomyślnie!");

                    name.setText(null);
                    surname.setText(null);
                    pesel.setText(null);
                    age.setText(null);
                    email.setText(null);
                    password.setText(null);
                    phone.setText(null);
                    driverLicenseNumber.setText(null);
                    checkBox.setSelected(false);
                    checkBox1.setSelected(false);
                    checkBox2.setSelected(false);
                    checkBox3.setSelected(false);
                    radioButton.setSelected(false);

                    mainFrame.ChangeCard("LOGIN");
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        registerPanel.add(returnButton);
        registerPanel.add(registerButton);


        add(registerPanel);
    }
}
