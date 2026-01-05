package frontend;

import app.Main;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame{
    private CardLayout layout;
    private JPanel mainContainer;
    private Main appLogic;

    public MainFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);

        layout = new CardLayout();
        mainContainer = new JPanel(layout);

        //tutaj narazie tak, potem się zmieni
        LoginPanel loginPanel = new LoginPanel(this, appLogic);
        UserPanel userPanel = new UserPanel(this);
        RegisterPanel registerPanel = new RegisterPanel(this, appLogic);

        mainContainer.add(loginPanel, "LOGIN");
        mainContainer.add(registerPanel, "REGISTER");
        mainContainer.add(userPanel, "USER");

        add(mainContainer);

        layout.show(mainContainer, "LOGIN");

        setVisible(true);
    }

    public void ChangeCard(String cardName) {
        layout.show(mainContainer, cardName);
    }

    public static void main(String[] args) {
           SwingUtilities.invokeLater(MainFrame::new);

    }
 }
