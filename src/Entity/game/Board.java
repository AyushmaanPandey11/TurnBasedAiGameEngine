package Entity.game;

import Entity.boards.TicToeBoard;

public class Board {
    public static Board start(String type){
        if(type.equals("TicTacToe")){
            return new TicToeBoard();
        } else {
            throw new IllegalArgumentException();
        }
    }
}
