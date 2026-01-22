package PubSubManager;

import java.util.ArrayList;
import java.util.List;

public class EventManager {
    List<Event> events;
    List<Subscriber> subscribers = new ArrayList<>();

    public void addEvent(Event event){
        events.add(event);
    }

    public void Subscribe(Subscriber subscriber){
        subscribers.add(subscriber);
    }
}
