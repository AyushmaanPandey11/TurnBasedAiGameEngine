package api;

import Entity.boards.TicToeBoard;
import Entity.game.*;

public class AIEngine {
    public Move suggestMove(Board board, Player player) {
        if (board instanceof TicToeBoard boardInstance) {
            Move move = null;
            if (!isStarting(boardInstance, 4)) {
                move = getSmartMove(boardInstance, player);
            }
            if (move == null) {
                move = getBasicMove(boardInstance, player);
            }
            return move;
        } else {
            throw new IllegalArgumentException();
        }
    }

    private Move git add .(TicToeBoard board, Player bot){
        // make move in a cell if it meets this two conditions
        // 1. this move will win bot the game by matching along row/column or diag
        // 2. stop user from winning the game.
        GameManager gameManager = new GameManager();
        Move move= null;

        // victory move
        for (int row=0; row< 3; row++){
            for (int col=0;col <3; col++){
                if( board.getCell(row,col) == null){
                    move = new Move(new Cell(row,col),bot);
                    TicToeBoard boardCopy = board.copy();
                   boardCopy.move(move);
                   if(gameManager.isComplete(boardCopy).getWinner().equals("bot")){
                       return move;
                   }
                }
            }
        }

        // defensive move
        for (int row=0; row< 3; row++){
            for (int col=0;col <3; col++){
                if( board.getCell(row,col) == null){
                    move = new Move(new Cell(row,col),bot.flip());
                    TicToeBoard boardCopy = board.copy();
                    boardCopy.move(move);
                    if(gameManager.isComplete(boardCopy).getWinner().equals("User")){
                        return move;
                    }
                }
            }
        }
        return null;
    }

    private boolean isStarting(TicToeBoard board, int limit){
        int count =0;
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (board.getCell(row, col) != null) {
                    count++;
                }
            }
        }
        return count < limit;
    }

    private Move getBasicMove(TicToeBoard boardInstance, Player bot){
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (boardInstance.getCell(row, col) == null) {
                    return new Move(new Cell(row, col),bot);
                }
            }
        }
        return null;
    }
}
