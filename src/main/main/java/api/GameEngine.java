package main.java.api;

import main.java.Entity.boards.TicToeBoard;
import main.java.Entity.game.*;

public class GameEngine {
    public void move(Board board, Move move ){
        if(board instanceof TicToeBoard boardInstance){
            board.move(move);
        }
    }
    public static Board start(String type){
        if(type.equals("TicTacToe")){
            return new TicToeBoard();
        } else {
            throw new IllegalArgumentException();
        }
    }
}

