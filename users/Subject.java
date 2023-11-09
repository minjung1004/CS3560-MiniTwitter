package users;

import java.util.List;
import java.util.ArrayList;

public class Subject {
    // List of the observers that are watching the user
    private List<Observer> observers = new ArrayList<Observer>();

    // Attaches an observer to the user's observers list
    public void attach(Observer observer){
        observers.add(observer);
    }

    // Detaches an observer from the user's observers list
    public void detach(Observer observer){
        observers.remove(observer);
    }

    // Notifies all observers that are attached to the observers list
    public void notifyObservers() {
        for (Observer ob: observers) {
            ob.update(this);
        }
    }
    
}
