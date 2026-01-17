package backend;

import osoba.Klient;
import wypozyczenie.Status;
import wypozyczenie.Wypozyczenie;
import pojazd.Pojazd;
import strategia.*;

import java.security.Provider.Service;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.swing.JOptionPane;

public class ServiceRental {
    private RepositoryRental repositoryRental;
    private RepositoryVehicle repositoryVehicle;
    private ServiceVehicle serviceVehicle;
    private ServiceUser serviceUser;
    private RepositoryUser repositoryUser;

    private StrategiaCenowa lastStrategy;
    private double lastPrice;
    private Date lastStartDate;
    private Date lastEndDate;

    public ServiceRental() {
        this.repositoryRental = new RepositoryRental();
    }
    
    public ServiceRental(RepositoryVehicle repositoryVehicle, ServiceVehicle serviceVehicle, ServiceUser serviceUser, RepositoryUser repositoryUser) {
        this.repositoryRental = new RepositoryRental();
        this.repositoryVehicle = repositoryVehicle;
        this.serviceVehicle = serviceVehicle;
        this.serviceUser = serviceUser;
        this.repositoryUser = repositoryUser;
    }

    public RepositoryRental getRepositoryRental() {
        return repositoryRental;
    }

    public ArrayList<Wypozyczenie> getRentals() {
        return repositoryRental.getRentals();
    }
    
    public void setRepositoryVehicle(RepositoryVehicle repositoryVehicle) {
        this.repositoryVehicle = repositoryVehicle;
    }
    
    public void setServiceVehicle(ServiceVehicle serviceVehicle) {
        this.serviceVehicle = serviceVehicle;
    }

    public void setServiceUser(ServiceUser serviceUser) {
        this.serviceUser = serviceUser;
    }

    public void setRepositoryUser(RepositoryUser repositoryUser) {
        this.repositoryUser = repositoryUser;
    }
    
    public boolean rent(Pojazd vehicle, String dateStart, String dateEnd, StrategiaCenowa strategia) {
        if (!(Session.getCurrentUser() instanceof Klient)) {
            return false;
        }
        Klient client = (Klient) Session.getCurrentUser();
        
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            Date dataRozpoczecia = sdf.parse(dateStart);
            Date dataZakonczenia = sdf.parse(dateEnd);

            if(client.getSaldo() >= lastPrice) {
                client.setSaldo(-lastPrice);
                serviceUser.clientSaveData();
                vehicle.setStatus("zajęty");
                if (repositoryVehicle != null) {
                    repositoryVehicle.save();
                }
                Wypozyczenie rental = new Wypozyczenie(vehicle, client, dataRozpoczecia, dataZakonczenia, strategia, Status.OCZEKUJACE);
                repositoryRental.upload(rental);
                JOptionPane.showMessageDialog(null, "Pojazd został wypożyczony!");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Niewystarczające środki na koncie. Proszę doładować saldo.");
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public void calculateRental(Pojazd vehicle, Date startDate, Date endDate) {
        long diffInMinutes = TimeUnit.MINUTES.convert(endDate.getTime() - startDate.getTime(), TimeUnit.MILLISECONDS);
        long diffInHours = TimeUnit.HOURS.convert(endDate.getTime() - startDate.getTime(), TimeUnit.MILLISECONDS);
        long diffInDays = TimeUnit.DAYS.convert(endDate.getTime() - startDate.getTime(), TimeUnit.MILLISECONDS);
        
        lastStrategy = selectStrategyBasedOnTime(diffInMinutes, diffInHours, diffInDays);
        lastPrice = lastStrategy.wyliczKoszt(diffInMinutes, vehicle.getCenaBazowa());
        lastStartDate = startDate;
        lastEndDate = endDate;
    }

    public void returnRental(Wypozyczenie rental) {
        int result = JOptionPane.showConfirmDialog(null, "Potwierdzenie zwrotu pojazdu, zostanie zwrócone: " + rental.getKosztKoncowy() * 0.9 + " PLN", "Potwierdzenie zwrotu pojazdu", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            rental.getPojazd().setStatus("wolny");
            rental.getKlient().setSaldo(rental.getKosztKoncowy() * 0.9);
            rental.setStatus(Status.ZAKONCZONE);
            serviceUser.clientSaveData();
            if (repositoryVehicle != null) {
                repositoryVehicle.save();
            }
        }
        repositoryRental.save();
    }
    
    public StrategiaCenowa getLastStrategy() { return lastStrategy; }
    public double getLastPrice() { return lastPrice; }
    public Date getLastStartDate() { return lastStartDate; }
    public Date getLastEndDate() { return lastEndDate; }
    
    public void clearCalculation() {
        lastStrategy = null;
        lastPrice = 0;
        lastStartDate = null;
        lastEndDate = null;
    }
    
    private StrategiaCenowa selectStrategyBasedOnTime(long minutes, long hours, long days) {
        if (days > 10) {
            return new StrategiaDlugoterminowa();
        } else if (days >= 1) {
            return new StrategiaDobowa();
        }
        else {
            return new StrategiaDobowa();
        }
    }
}
