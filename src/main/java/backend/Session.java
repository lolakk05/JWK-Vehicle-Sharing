package backend;

import osoba.Osoba;

import java.awt.*;

public class Session {
    private static Osoba currentUser;
    private CardLayout layout;

    public static void login(Osoba user) {
        currentUser = user;
    }

    public static void logout() {
        currentUser = null;
    }

    public static Osoba getCurrentUser() {
        return currentUser;
    }
}
