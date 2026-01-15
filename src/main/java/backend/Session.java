package backend;

import osoba.Klient;

import java.awt.*;

public class Session {
    private static Klient currentUser;
    private CardLayout layout;

    public static void login(Klient user) {
        currentUser = user;
    }

    public static void logout() {
        currentUser = null;
    }

    public static Klient getCurrentUser() {
        return currentUser;
    }
}
