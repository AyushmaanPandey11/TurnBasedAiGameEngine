import Entity.commands.EmailCommand;
import Entity.commands.SMSCommand;
import PubSubManager.Event;
import PubSubManager.EventManager;
import PubSubManager.Subscriber;
import services.EmailService;
import api.GameEngine;
import Entity.boards.TicTacToeBoard;
import Entity.game.Cell;
import Entity.game.Move;
import Entity.game.Player;
import api.AIEngine;
import api.RuleEngine;
import services.SmsService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        GameEngine gameEngine = new GameEngine();
        RuleEngine ruleEngine = new RuleEngine();
        EmailService emailService = new EmailService();
        SmsService smsService = new SmsService();
        AIEngine aiEngine = new AIEngine();
        EventManager eventManager = new EventManager();
        eventManager.Subscribe(new Subscriber(event -> emailService.send(new EmailCommand(event))));
        eventManager.Subscribe(new Subscriber(event -> smsService.send(new SMSCommand(event))));
        TicTacToeBoard board =  (TicTacToeBoard) GameEngine.start("TicTacToe");
        Player user = new Player('O',"User"), computer = new Player('X',"bot");
        while(!ruleEngine.isComplete(board).isOver()){
            System.out.println("Game started, Make your move! \n");
            int row,col;
            row = scanner.nextInt();
            col = scanner.nextInt();
            Move userMove = new Move(new Cell(row,col),user);
            gameEngine.move(board,userMove);
            if(!ruleEngine.isComplete(board).isOver()){
                Move computerMove = aiEngine.suggestMove(board,computer);
                gameEngine.move(board, computerMove);
            }
            System.out.println(board.toString());
        }
        eventManager.addEvent(new Event(user.getUser(),"Congratulations!! You won","username@gmail.com","win"));
        System.out.println("Game Winner: "+ ruleEngine.isComplete(board).getWinner());
    }
}
