package wypozyczenie;

public enum Status {
    OCZEKUJACE("Oczekujące"),
    AKTYWNE("Aktywne"),
    W_NAPRAWIE("W naprawie"),
    ZAKONCZONE("Zakończone"),
    ANULOWANE("Anulowane");

    private final String displayName;

    Status(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
