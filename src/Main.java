import Entity.boards.TicToeBoard;
import Entity.game.Board;
import Entity.game.Cell;
import Entity.game.Move;
import Entity.game.Player;
import api.GameEngine;

import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        GameEngine gameEngine = new GameEngine();
        TicToeBoard board =  (TicToeBoard) Board.start("TicTacToe");
        Player user = new Player('O',"User"), computer = new Player('X',"bot");
        while(!gameEngine.isComplete(board).isOver()){
            System.out.println(board.toString());
            System.out.println("Game started for tictactoe board. ");
            int row,col;
            row = scanner.nextInt();
            col = scanner.nextInt();
            Move userMove = new Move(new Cell(row,col));
            gameEngine.move(board,user,userMove);
            if(!gameEngine.isComplete(board).isOver()){
                Move computerMove = gameEngine.suggestMove(board);
                gameEngine.move(board,computer, computerMove);
            }
            System.out.println(board.toString());
        }
        System.out.println("Game Winner: "+ gameEngine.isComplete(board).getWinner());
    }
}
