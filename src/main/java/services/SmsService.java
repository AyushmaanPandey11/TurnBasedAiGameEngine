
package services;

import Entity.commands.SMSCommand;
import Entity.game.User;

public class SmsService {
    private void sendSMS(User user, String message){

    }

    public void send(SMSCommand command){
        sendSMS(command.getDetails().user,command.getDetails().message);
    }
}
