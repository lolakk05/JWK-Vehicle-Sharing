package frontend;

import backend.*;
import osoba.Serwisant;
import pojazd.Pojazd;
import zlecenieNaprawy.ZlecenieNaprawy;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Date;

public class MainFrame extends JFrame{
    private CardLayout layout;
    private JPanel mainContainer;

    private ServiceUser serviceUser;
    private ServiceVehicle serviceVehicle;
    private ServiceWorker serviceWorker;
    private ServiceRental serviceRental;

    private UserPanel userPanel;
    private LoginPanel loginPanel;
    private AcceptLoanPanel acceptLoanPanel;
    private AddWorkerPanel addWorkerPanel;
    private AddCar addCar;
    private AddMotorcycle addMotorcycle;
    private AddTir addTir;
    private AddScooter addScooter;
    private AddBike addBike;
    private VehicleListPanel vehicleListPanel;
    private RentPanel rentPanel;
    private ServiceWorkerPanel serviceWorkerPanel;
    private RemoveVehiclePanel removeVehiclePanel;

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
        this.serviceRental.setRepositoryVehicle(serviceVehicle.getRepositoryVehicle());
        this.serviceRental.setServiceUser(serviceUser);
        this.serviceRental.setRepositoryUser(serviceUser.getRepositoryUser());

        this.serviceWorker.setRepositoryRental(serviceRental.getRepositoryRental());

        loginPanel = new LoginPanel(this, serviceUser, serviceWorker);
        userPanel = new UserPanel(this, serviceUser, serviceRental, serviceWorker);
        RegisterPanel registerPanel = new RegisterPanel(this, serviceUser);
        acceptLoanPanel = new AcceptLoanPanel(this, serviceRental);
        AddWorkerPanel addWorkerPanel = new AddWorkerPanel(this, serviceWorker);
        AddCar addCarPanel = new AddCar(this, serviceVehicle);
        AddMotorcycle addMotorcyclePanel = new AddMotorcycle(this, serviceVehicle);
        AddTir addTirPanel = new AddTir(this, serviceVehicle);
        AddScooter addScooterPanel = new AddScooter(this, serviceVehicle);
        AddBike addBikePanel = new AddBike(this, serviceVehicle);
        vehicleListPanel = new VehicleListPanel(this, serviceVehicle);
        rentPanel = new RentPanel(this, serviceVehicle, serviceRental);
        removeVehiclePanel = new RemoveVehiclePanel(this, serviceVehicle);
        serviceWorkerPanel = new ServiceWorkerPanel(this, serviceWorker);

        mainContainer.add(loginPanel, "LOGIN");
        mainContainer.add(registerPanel, "REGISTER");
        mainContainer.add(userPanel, "USER");
        mainContainer.add(vehicleListPanel, "RENT");
        mainContainer.add(rentPanel, "VEHICLE");
        mainContainer.add(acceptLoanPanel, "ACCEPT_LOAN");
        mainContainer.add(addWorkerPanel, "ADD_WORKER_PANEL");
        mainContainer.add(addCarPanel, "ADD_CAR");
        mainContainer.add(addMotorcyclePanel, "ADD_MOTORCYCLE");
        mainContainer.add(addTirPanel, "ADD_TIR");
        mainContainer.add(addScooterPanel, "ADD_SCOOTER");
        mainContainer.add(addBikePanel, "ADD_BIKE");
        mainContainer.add(removeVehiclePanel, "REMOVE_VEHICLE_PANEL");
        mainContainer.add(serviceWorkerPanel, "SERVICE_WORKER_PANEL");

        add(mainContainer);

        layout.show(mainContainer, "LOGIN");


        setVisible(true);
    }

    public void setVehicle(Pojazd P) {
        rentPanel.getVehicle(P);
    }

    public void ChangeCard(String cardName) {
        if(cardName.equals("USER")){
            userPanel.getUserData();
            userPanel.refreshRentalList();
        }
        if(cardName.equals("SERVICE_WORKER_PANEL")){
            serviceWorkerPanel.refreshData();
        }
        if(cardName.equals("RENT")){
            vehicleListPanel.getClientData();
            vehicleListPanel.refreshList();
        }
        if (cardName.equals("REMOVE_VEHICLE_PANEL")) {
            removeVehiclePanel.refreshList();
        }
        if (cardName.equals("ACCEPT_LOAN")) {
            acceptLoanPanel.refreshList();
        }

        layout.show(mainContainer, cardName);
    }
 }
