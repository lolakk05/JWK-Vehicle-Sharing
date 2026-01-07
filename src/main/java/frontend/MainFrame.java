package frontend;

import app.Main;
import pojazd.Pojazd;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame{
    private CardLayout layout;
    private JPanel mainContainer;
    private Main appLogic;
    private UserPanel userPanel;
    private VehicleListPanel vehicleListPanel;
    private VehicleDetailPanel vehicleDetailPanel;

    public MainFrame(Main appLogic) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setResizable(false);
        setTitle("JWK Vehicle Sharing");

        layout = new CardLayout();
        mainContainer = new JPanel(layout);

        //tutaj narazie tak, potem się zmieni
        LoginPanel loginPanel = new LoginPanel(this, appLogic);
        userPanel = new UserPanel(this, appLogic);
        RegisterPanel registerPanel = new RegisterPanel(this, appLogic);
        vehicleListPanel = new VehicleListPanel(this);
        vehicleDetailPanel = new VehicleDetailPanel(this, appLogic);


        mainContainer.add(loginPanel, "LOGIN");
        mainContainer.add(registerPanel, "REGISTER");
        mainContainer.add(userPanel, "USER");
        mainContainer.add(vehicleListPanel, "MAIN");
        mainContainer.add(vehicleDetailPanel, "VEHICLE");

        add(mainContainer);

        layout.show(mainContainer, "LOGIN");

        setVisible(true);
    }

    public void setVehicle(Pojazd P) {
        vehicleDetailPanel.getVehicle(P);
    }

    public void ChangeCard(String cardName) {
        if(cardName.equals("USER")){
            userPanel.getUserData();
        }
        if(cardName.equals("MAIN")){
            vehicleListPanel.getClientData();
            vehicleListPanel.refreshList();
        }

        layout.show(mainContainer, cardName);
    }
 }
