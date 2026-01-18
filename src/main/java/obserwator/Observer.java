package obserwator;

import pojazd.Pojazd;

public interface Observer {
    void update(Pojazd vehicle, int difference);
}