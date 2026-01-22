package Entity.commands;

import Entity.game.User;

public class Notification {
    public User user;
    public String message;

    public Notification(User user, String message) {
        this.user = user;
        this.message = message;
    }


}
