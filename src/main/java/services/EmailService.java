package services;

import Entity.commands.EmailCommand;
import Entity.game.User;

public class EmailService {
    private void sendEmail(User user, String message){

    }

    public void send(EmailCommand command){
        sendEmail(command.getUser(),command.getMessage());
    }
}
