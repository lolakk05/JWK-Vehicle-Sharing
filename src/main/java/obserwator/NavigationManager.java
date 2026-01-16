package obserwator;

import java.util.ArrayList;
import java.util.List;

public class NavigationManager {
    private List<NavigationObserver> observers = new ArrayList<>();

    public void addObserver(NavigationObserver observer) {
        observers.add(observer);
    }

    public void navigateTo(String cardName) {
        for (NavigationObserver observer : observers) {
            observer.moveTo(cardName);
        }
    }
}