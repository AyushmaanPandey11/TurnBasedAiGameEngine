package api;

import Entity.boards.TicToeBoard;
import Entity.game.*;

public class AIEngine {
    public Move suggestMove(Board board, Player player) {
        if (board instanceof TicToeBoard boardInstance) {
            int limit = 3;
            if (movesCount(boardInstance) >= limit) {
                Move move = getSmartMove(boardInstance, player);
                if(move != null ) return move;
            }
            return getBasicMove(boardInstance,player);
        } else {
            throw new IllegalArgumentException();
        }
    }

    private Move getSmartMove(TicToeBoard board, Player bot){
        // make move in a cell if it meets this two conditions
        // 1. this move will win bot the game by matching along row/column or diag
        // 2. stop user from winning the game.
        RuleEngine gameManager = new RuleEngine();

        // victory move
        for (int row=0; row< 3; row++){
            for (int col=0;col <3; col++){
                if( board.getCell(row,col) == null){
                    Move move = new Move(new Cell(row,col),bot);
                    TicToeBoard boardCopy = board.copy();
                   boardCopy.move(move);
                    if (bot.getPlayerName().equals(gameManager.isComplete(boardCopy).getWinner())) {
                        return move;
                    }
                }
            }
        }

        // defensive move
        Player opponent = bot.flip();
        for (int row=0; row< 3; row++){
            for (int col=0;col <3; col++){
                if( board.getCell(row,col) == null){
                    Move move = new Move(new Cell(row,col),opponent);
                    TicToeBoard boardCopy = board.copy();
                    boardCopy.move(move);
                    if (opponent.getPlayerName().equals(gameManager.isComplete(boardCopy).getWinner())) {
                        return new Move(new Cell(row,col),bot);
                    }
                }
            }
        }
        return null;
    }

    private int movesCount(TicToeBoard board){
        int count =0;
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (board.getCell(row, col) != null) {
                    count = count+1;
                }
            }
        }
        return count;
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
