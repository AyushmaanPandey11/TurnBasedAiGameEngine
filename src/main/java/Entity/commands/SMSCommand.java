package Entity.commands;


import Entity.game.User;
import PubSubManager.Event;

public class SMSCommand {
    Notification notifications;
    String link;

    public SMSCommand(Event event) {
        this.notifications = new Notification(event.getUser(), event.getMessage());
        this.link = event.getLink();
    }

    public User getUser(){
        return notifications.user;
    }

    public String getMessage(){
        return notifications.message;
    }
}
