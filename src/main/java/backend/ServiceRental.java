package backend;

import osoba.Klient;
import wypozyczenie.Wypozyczenie;
import pojazd.Pojazd;
import strategia.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class ServiceRental {
    private RepositoryRental repositoryRental;
    private RepositoryVehicle repositoryVehicle;
    private ServiceVehicle serviceVehicle;
    
    private StrategiaCenowa lastStrategy;
    private double lastPrice;
    private Date lastStartDate;
    private Date lastEndDate;

    public ServiceRental() {
        this.repositoryRental = new RepositoryRental();
    }
    
    public ServiceRental(RepositoryVehicle repositoryVehicle, ServiceVehicle serviceVehicle) {
        this.repositoryRental = new RepositoryRental();
        this.repositoryVehicle = repositoryVehicle;
        this.serviceVehicle = serviceVehicle;
    }
    
    public void setRepositoryVehicle(RepositoryVehicle repositoryVehicle) {
        this.repositoryVehicle = repositoryVehicle;
    }
    
    public void setServiceVehicle(ServiceVehicle serviceVehicle) {
        this.serviceVehicle = serviceVehicle;
    }

    public void rent(Pojazd vehicle, String dateStart, String dateEnd, StrategiaCenowa strategia) {
        Klient client = Session.getCurrentUser();
        
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            Date dataRozpoczecia = sdf.parse(dateStart);
            Date dataZakonczenia = sdf.parse(dateEnd);

            vehicle.setStatus("zajęty");
            if (repositoryVehicle != null) {
                repositoryVehicle.save();
            }
            
            Wypozyczenie rental = new Wypozyczenie(vehicle, client, dataRozpoczecia, dataZakonczenia, strategia);
            repositoryRental.uploadAwaiting(rental);
        } catch (ParseException e) {
            e.printStackTrace();
        }
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
            return new StrategiaGodzinowa();
        } else if (hours >= 1) {
            return new StrategiaGodzinowa();
        } else {
            return new StrategiaMinutowa();
        }
    }
}
