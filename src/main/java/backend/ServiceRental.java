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
                if(client.getKategoriaArray() != null) {
                    boolean hasCategory = false;
                    for(String cat : client.getKategoriaArray()) {
                        if(vehicle.getWymaganeUprawnienia().equals(cat)) {
                            hasCategory = true;
                            break;
                        }
                    }
                    if(!hasCategory) {
                        JOptionPane.showMessageDialog(null, "Brak odpowiednich uprawnień do wypożyczenia tego pojazdu.");
                        return false;
                    }
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
                }
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
        
        diffInDays = diffInDays + 1;
        diffInMinutes = diffInMinutes + TimeUnit.MINUTES.convert(1, TimeUnit.DAYS);
        
        lastStrategy = selectStrategyBasedOnTime(diffInMinutes, diffInHours, diffInDays);
        lastPrice = lastStrategy.wyliczKoszt(diffInMinutes, vehicle.getCenaBazowa());
        lastStartDate = startDate;
        lastEndDate = endDate;
    }

    public void cancelRental(Wypozyczenie rental) {
        int result = JOptionPane.showConfirmDialog(null, "Potwierdzenie anulowania wypożyczenia, zostanie zwrócone: " + rental.getKosztKoncowy() * 0.9 + " PLN", "Potwierdzenie zwrotu pojazdu", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            ServiceVehicle.zwolnijPojazd(rental.getPojazd());
            rental.getKlient().setSaldo(rental.getKosztKoncowy() * 0.9);
            rental.setStatus(Status.ZAKONCZONE);
            serviceUser.clientSaveData();
            if (repositoryVehicle != null) {
                repositoryVehicle.save();
            }
        }
        repositoryRental.save();
    }

    public void returnRental(Wypozyczenie rental) {
        int result = JOptionPane.showConfirmDialog(null, "Potwierdzenie zwrotu pojazdu", "Potwierdzenie zwrotu pojazdu", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            ServiceVehicle.zwolnijPojazd(rental.getPojazd());
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
        } 
        else  {
            return new StrategiaDobowa();
        }
    }

    public void acceptRental(Wypozyczenie rental) {
        rental.setStatus(Status.AKTYWNE);
        repositoryRental.save();
    }
    
    public ArrayList<Wypozyczenie> getFilteredAndSortedRentals(Klient client, String searchText, String statusFilter, String sortOrder) {
        ArrayList<Wypozyczenie> allRentals = new ArrayList<>(repositoryRental.getRentals());
        ArrayList<Wypozyczenie> filteredRentals = new ArrayList<>();
        
        for (Wypozyczenie r : allRentals) {
            if (r.getKlient().getEmail().equals(client.getEmail())) {
                Pojazd p = r.getPojazd();
                String vehicleName = (p.getMarka() + " " + p.getModel()).toLowerCase();
                
                if (searchText != null && !searchText.isEmpty() && !vehicleName.contains(searchText.toLowerCase())) {
                    continue;
                }
                
                if (statusFilter != null && !statusFilter.equals("Wszystkie") && !r.getStatus().getDisplayName().equals(statusFilter)) {
                    continue;
                }
                
                filteredRentals.add(r);
            }
        }
        
        if (sortOrder != null) {
            switch (sortOrder) {
                case "date_desc":
                    filteredRentals.sort((r1, r2) -> r2.getDataRozpoczecia().compareTo(r1.getDataRozpoczecia()));
                    break;
                case "date_asc":
                    filteredRentals.sort((r1, r2) -> r1.getDataRozpoczecia().compareTo(r2.getDataRozpoczecia()));
                    break;
                case "price_desc":
                    filteredRentals.sort((r1, r2) -> Double.compare(r2.getKosztKoncowy(), r1.getKosztKoncowy()));
                    break;
                case "price_asc":
                    filteredRentals.sort((r1, r2) -> Double.compare(r1.getKosztKoncowy(), r2.getKosztKoncowy()));
                    break;
            }
        }
        
        return filteredRentals;
    }
}
