package frontend;

import backend.ServiceRental;
import backend.ServiceUser;
import backend.ServiceVehicle;
import backend.ServiceWorker;
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

        LoginPanel loginPanel = new LoginPanel(this, serviceUser, serviceWorker);
        userPanel = new UserPanel(this, serviceUser);
        RegisterPanel registerPanel = new RegisterPanel(this, serviceUser);
        AcceptLoanPanel acceptLoanPanel = new AcceptLoanPanel(this);
        AddWorkerPanel addWorkerPanel = new AddWorkerPanel(this, serviceWorker);
        AddCar addCarPanel = new AddCar(this, serviceVehicle);
        AddMotorcycle addMotorcyclePanel = new AddMotorcycle(this, serviceVehicle);
        AddTir addTirPanel = new AddTir(this, serviceVehicle);
        AddScooter addScooterPanel = new AddScooter(this, serviceVehicle);
        AddBike addBikePanel = new AddBike(this, serviceVehicle);
        vehicleListPanel = new VehicleListPanel(this, serviceVehicle);
        rentPanel = new RentPanel(this, serviceVehicle, serviceRental);
        RemoveVehiclePanel removeVehiclePanel = new RemoveVehiclePanel(this);
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


        ///  TEST DANE DLA SERWISANTA
        initializeExampleData();
        /// ^^^ to wyzej mozna usunac na luzaczku

        setVisible(true);
    }


    /// CALA FUNKCJA NIZEJ TO PO PROSTU WYGENEROWANIE PRZYKLADOWYCH DANYCH DLA SERWISANTA
    /// ZEBY PRZETESTOWAC SOBIE JAK TO WYGLADA I DZIALA, PASSY DO KONTA:
    /// email: serwisant@ ; haslo: serwisant


    private void initializeExampleData() {
        if (serviceVehicle.getVehicles().isEmpty()) {
            try {
                serviceVehicle.addCar(new String[]{"Toyota", "Corolla", "2020", "Srebrny", "1300", "100", "B", "VIN123", "KR12345", "1.8", "5", "Benzyna", "Sedan", "5"});
                serviceVehicle.addBike(new String[]{"Giant", "Roam", "2022", "Czarny", "15", "20", "Górski", "28", "Shimano"});
            } catch (Exception e) {
                System.out.println("Błąd podczas dodawania przykładowych pojazdów: " + e.getMessage());
            }
        }

        if (serviceWorker.getWolneZlecenia().isEmpty()) {
            ArrayList<Pojazd> vehicles = serviceVehicle.getVehicles();
            if (!vehicles.isEmpty()) {
                serviceWorker.dodajZlecenie(new ZlecenieNaprawy("Wymiana oleju", new Date(), 1, 200.0, vehicles.get(0)));
                if (vehicles.size() > 1) {
                    serviceWorker.dodajZlecenie(new ZlecenieNaprawy("Naprawa hamulca", new Date(), 2, 50.0, vehicles.get(1)));
                }
            }
        }
    }

    /// KONIEC TESTU

    public void setVehicle(Pojazd P) {
        rentPanel.getVehicle(P);
    }

    public void ChangeCard(String cardName) {
        if(cardName.equals("USER")){
            userPanel.getUserData();
        }
        if(cardName.equals("SERVICE_WORKER_PANEL")){
            serviceWorkerPanel.refreshData();
        }
        if(cardName.equals("MAIN")){
            vehicleListPanel.getClientData();
            vehicleListPanel.refreshList();
        }

        layout.show(mainContainer, cardName);
    }
 }
