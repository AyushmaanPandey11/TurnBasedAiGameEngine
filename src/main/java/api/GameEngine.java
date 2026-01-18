package api;

import Entity.boards.TicTacToeBoard;
import Entity.game.*;

public class GameEngine {
    public void move(Board board, Move move ){
        if (move == null){
            System.out.println("Null Move coming");
            return;
        }
        if(board instanceof TicTacToeBoard boardInstance){
            board.move(move);
        }
    }
    public static Board start(String type){
        if(type.equals("TicTacToe")){
            return new TicTacToeBoard();
        } else {
            throw new IllegalArgumentException();
        }
    }
}

