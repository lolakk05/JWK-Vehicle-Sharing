package backend;

import osoba.Serwisant;
import wypozyczenie.Status;
import wypozyczenie.Wypozyczenie;
import zlecenieNaprawy.ZlecenieNaprawy;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ServiceWorker {
    private RepositoryWorker repositoryWorker;
    private RepositoryZlecen repositoryZlecen;
    private RepositoryRental repositoryRental;

    public ServiceWorker() {
        this.repositoryWorker = new RepositoryWorker();
        this.repositoryZlecen = new RepositoryZlecen();
    }

    public void setRepositoryRental(RepositoryRental repositoryRental) {
        this.repositoryRental = repositoryRental;
    }

    public List<ZlecenieNaprawy> getWolneZlecenia() {
        return repositoryZlecen.getZlecenia().stream()
                .filter(z -> z.getSerwisant() == null && !z.isCzyZakonczone())
                .collect(Collectors.toList());
    }

    public List<ZlecenieNaprawy> getZleceniaSerwisanta(Serwisant serwisant) {
        return repositoryZlecen.getZlecenia().stream()
                .filter(z -> z.getSerwisant() != null && z.getSerwisant().getPesel().equals(serwisant.getPesel()) && !z.isCzyZakonczone())
                .collect(Collectors.toList());
    }

    public void przypiszZlecenie(ZlecenieNaprawy zlecenie, Serwisant serwisant) {
        zlecenie.setSerwisant(serwisant);
        serwisant.przyjmijZlecenie(zlecenie);
        repositoryZlecen.save();
        repositoryWorker.save();
    }

    public void zakonczZlecenie(ZlecenieNaprawy zlecenie) {
        zlecenie.setCzyZakonczone(true);
        if (zlecenie.getSerwisant() != null) {
            zlecenie.getSerwisant().zakonczNaprawe(zlecenie);
        }

        // Przywracanie statusu AKTYWNE dla wypożyczenia
        if (repositoryRental != null && zlecenie.getPojazd() != null) {
            for (Wypozyczenie rental : repositoryRental.getRentals()) {
                if (rental.getPojazd().equals(zlecenie.getPojazd()) && rental.getStatus() == Status.W_NAPRAWIE) {
                    rental.setStatus(Status.AKTYWNE);
                    repositoryRental.save();
                    break;
                }
            }
        }

        repositoryZlecen.save();
        repositoryWorker.save();
    }

    public boolean login(String email, String password) {
        ArrayList<osoba.Pracownik> result = new ArrayList<>(repositoryWorker.getWorkers());
        for (osoba.Pracownik worker : result) {
            if (worker.getEmail().equals(email) && worker.getHaslo().equals(password)) {
                Session.login(worker);
                return true;
            }
        }
        return false;
    }

    public void dodajZlecenie(ZlecenieNaprawy zlecenie) {
        repositoryZlecen.dodajZlecenie(zlecenie);
    }

    public void dodajPracownika(osoba.Pracownik pracownik) {
        repositoryWorker.upload(pracownik);
    }

    public boolean hasAnyWorkers() {
        return !repositoryWorker.getWorkers().isEmpty();
    }
}
