package backend;

import zlecenieNaprawy.ZlecenieNaprawy;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class RepositoryZlecen {
    private List<ZlecenieNaprawy> zlecenia;
    private static final String FILE_PATH = "data/zlecenia.ser";

    public RepositoryZlecen() {
        this.zlecenia = new ArrayList<>();
        load();
    }

    public void load() {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            return;
        }
        try (ObjectInputStream is = new ObjectInputStream(new FileInputStream(file))) {
            zlecenia = (List<ZlecenieNaprawy>) is.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void save() {
        try (ObjectOutputStream so = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            so.writeObject(zlecenia);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<ZlecenieNaprawy> getZlecenia() {
        return zlecenia;
    }

    public void dodajZlecenie(ZlecenieNaprawy zlecenie) {
        zlecenia.add(zlecenie);
        save();
    }
}
