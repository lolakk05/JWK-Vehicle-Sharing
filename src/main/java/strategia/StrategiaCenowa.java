package strategia;

import java.io.Serializable;

public interface StrategiaCenowa extends Serializable {
    double wyliczKoszt(long czasTrwania, double stawkaBazowa);
}
