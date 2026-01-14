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
    private AcceptLoanPanel acceptLoanPanel;
    private AddVehiclePanel addVehiclePanel;
    private AddWorkerPanel addWorkerPanel;
    private AddCar addCar;
    private AddMotorcycle addMotorcycle;
    private AddTir addTir;
    private AddScooter addScooter;
    private AddBike addBike;
    private VehicleListPanel vehicleListPanel;
    private VehicleDetailPanel vehicleDetailPanel;

    public MainFrame(Main appLogic) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 800);
        setResizable(false);
        setTitle("JWK Vehicle Sharing");

        layout = new CardLayout();
        mainContainer = new JPanel(layout);

        LoginPanel loginPanel = new LoginPanel(this, appLogic);
        userPanel = new UserPanel(this, appLogic);
        RegisterPanel registerPanel = new RegisterPanel(this, appLogic);
        AcceptLoanPanel acceptLoanPanel = new AcceptLoanPanel(this);
        AddVehiclePanel addVehiclePanel = new AddVehiclePanel(this);
        AddWorkerPanel addWorkerPanel = new AddWorkerPanel(this);
        AddCar addCarPanel = new AddCar(this);
        AddMotorcycle addMotorcyclePanel = new AddMotorcycle(this);
        AddTir addTirPanel = new AddTir(this);
        AddScooter addScooterPanel = new AddScooter(this);
        AddBike addBikePanel = new AddBike(this);
        vehicleListPanel = new VehicleListPanel(this);
        vehicleDetailPanel = new VehicleDetailPanel(this, appLogic);
        RemoveVehiclePanel removeVehiclePanel = new RemoveVehiclePanel(this);


        mainContainer.add(loginPanel, "LOGIN");
        mainContainer.add(registerPanel, "REGISTER");
        mainContainer.add(userPanel, "USER");
        mainContainer.add(vehicleListPanel, "MAIN");
        mainContainer.add(vehicleDetailPanel, "VEHICLE");
        mainContainer.add(acceptLoanPanel, "ACCEPT_LOAN");
        mainContainer.add(addVehiclePanel, "ADD_VEHICLE_PANEL");
        mainContainer.add(addWorkerPanel, "ADD_WORKER_PANEL");
        mainContainer.add(addCarPanel, "ADD_CAR");
        mainContainer.add(addMotorcyclePanel, "ADD_MOTORCYCLE");
        mainContainer.add(addTirPanel, "ADD_TIR");
        mainContainer.add(addScooterPanel, "ADD_SCOOTER");
        mainContainer.add(addBikePanel, "ADD_BIKE");
        mainContainer.add(removeVehiclePanel, "REMOVE_VEHICLE_PANEL");

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
