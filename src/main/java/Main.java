import api.GameEngine;
import Entity.boards.TicTacToeBoard;
import Entity.game.Cell;
import Entity.game.Move;
import Entity.game.Player;
import api.AIEngine;
import api.RuleEngine;

import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        GameEngine gameEngine = new GameEngine();
        RuleEngine ruleEngine = new RuleEngine();
        AIEngine aiEngine = new AIEngine();
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
        System.out.println("Game Winner: "+ ruleEngine.isComplete(board).getWinner());
    }
}
