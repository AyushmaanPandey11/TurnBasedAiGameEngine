package PubSubManager;

import java.util.ArrayList;
import java.util.List;

public class EventManager {
    List<Event> events = new ArrayList<>() ;
    List<Subscriber> subscribers = new ArrayList<>();

    public void publish(Event event){
        events.add(event);
        notifySubscribers(event);
    }

    public void Subscribe(Subscriber subscriber){
        subscribers.add(subscriber);
    }

    private void notifySubscribers(Event event) {
        for (Subscriber subscriber : subscribers) {
            subscriber.handleEvent(event);
        }
    }
}
