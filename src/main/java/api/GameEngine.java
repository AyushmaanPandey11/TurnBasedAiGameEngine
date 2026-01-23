package api;

import Entity.boards.TicTacToeBoard;
import Entity.game.*;

public class GameEngine {
    public Board move(Board board, Move move ){
        if (move == null){
            System.out.println("Null Move coming");
            return board;
        }
        if(board instanceof TicTacToeBoard ){
            board = board.move(move);
        }
        return board;
    }
    public static Board start(String type){
        if(type.equals("TicTacToe")){
            return new TicTacToeBoard();
        } else {
            throw new IllegalArgumentException();
        }
    }
}

