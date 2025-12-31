import Entity.boards.TicToeBoard;
import Entity.game.Cell;
import Entity.game.Move;
import Entity.game.Player;
import api.AIEngine;
import api.GameEngine;
import api.GameManager;

import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        GameEngine gameEngine = new GameEngine();
        GameManager gameManager = new GameManager();
        AIEngine aiEngine = new AIEngine();
        TicToeBoard board =  (TicToeBoard) GameEngine.start("TicTacToe");
        Player user = new Player('O',"User"), computer = new Player('X',"bot");
        while(!gameManager.isComplete(board).isOver()){
            System.out.println(board.toString());
            System.out.println("Game started, Make your move! \n");
            int row,col;
            row = scanner.nextInt();
            col = scanner.nextInt();
            Move userMove = new Move(new Cell(row,col),user);
            gameEngine.move(board,userMove);
            if(!gameManager.isComplete(board).isOver()){
                Move computerMove = aiEngine.suggestMove(board,computer);
                gameEngine.move(board, computerMove);
            }
            System.out.println(board.toString());
        }
        System.out.println("Game Winner: "+ gameManager.isComplete(board).getWinner());
    }
}
