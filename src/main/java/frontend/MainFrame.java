package frontend;

import backend.RepositoryVehicle;
import backend.ServiceRental;
import backend.ServiceUser;
import backend.ServiceVehicle;
import backend.ServiceWorker;
import pojazd.Pojazd;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame{
    private CardLayout layout;
    private JPanel mainContainer;

    private ServiceUser serviceUser;
    private ServiceVehicle serviceVehicle;
    private ServiceWorker serviceWorker;
    private ServiceRental serviceRental;

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

    public MainFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 800);
        setResizable(false);
        setTitle("JWK Vehicle Sharing");

        layout = new CardLayout();
        mainContainer = new JPanel(layout);

        this.serviceUser = new ServiceUser();
        this.serviceVehicle = new ServiceVehicle();
        this.serviceWorker = new ServiceWorker();
        this.serviceRental = new ServiceRental();
        this.serviceRental.setServiceVehicle(serviceVehicle);

        LoginPanel loginPanel = new LoginPanel(this, serviceUser);
        userPanel = new UserPanel(this, serviceUser);
        RegisterPanel registerPanel = new RegisterPanel(this, serviceUser);
        AcceptLoanPanel acceptLoanPanel = new AcceptLoanPanel(this);
        AddVehiclePanel addVehiclePanel = new AddVehiclePanel(this);
        AddWorkerPanel addWorkerPanel = new AddWorkerPanel(this);
        AddCar addCarPanel = new AddCar(this, serviceVehicle);
        AddMotorcycle addMotorcyclePanel = new AddMotorcycle(this, serviceVehicle);
        AddTir addTirPanel = new AddTir(this, serviceVehicle);
        AddScooter addScooterPanel = new AddScooter(this, serviceVehicle);
        AddBike addBikePanel = new AddBike(this, serviceVehicle);
        vehicleListPanel = new VehicleListPanel(this, serviceVehicle);
        vehicleDetailPanel = new VehicleDetailPanel(this, serviceVehicle, serviceRental);
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
