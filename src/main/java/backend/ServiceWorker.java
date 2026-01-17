package backend;

import osoba.Serwisant;
import zlecenieNaprawy.ZlecenieNaprawy;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ServiceWorker {
    private RepositoryWorker repositoryWorker;
    private RepositoryZlecen repositoryZlecen;

    public ServiceWorker() {
        this.repositoryWorker = new RepositoryWorker();
        this.repositoryZlecen = new RepositoryZlecen();
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
        repositoryZlecen.save();
    }

    public void zakonczZlecenie(ZlecenieNaprawy zlecenie) {
        zlecenie.setCzyZakonczone(true);
        repositoryZlecen.save();
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
