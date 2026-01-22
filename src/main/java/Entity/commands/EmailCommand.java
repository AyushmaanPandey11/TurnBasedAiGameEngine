package Entity.commands;

import Entity.game.User;
import PubSubManager.Event;

public class EmailCommand {
    Notification notification;
    String link;

    public EmailCommand(Event event) {
        this.notification = new Notification(event.getUser(), event.getMessage());
        this.link = event.getLink();
    }

    public User getUser(){
        return notification.user;
    }

    public String getMessage(){
        return notification.message;
    }
}
