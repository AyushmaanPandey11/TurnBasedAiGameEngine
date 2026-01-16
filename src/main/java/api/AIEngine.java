package api;

import Entity.boards.TicToeBoard;
import Entity.game.*;
import placements.AttackPlacement;
import placements.Placement;

import java.util.Optional;

public class AIEngine {
    RuleEngine rules = new RuleEngine();

    public Move suggestMove(Board board, Player player) {
        if (board instanceof TicToeBoard boardInstance) {
            int limit = 3;
            Cell cell;
            if (cellsCount(boardInstance) >= limit) {
                cell = getSmartCellToPlay(boardInstance, player);
                if(cell != null ) return new Move(cell,player);
            } else if ( cellsCount(boardInstance) +1 >= limit  ){
                cell = getOptimalMove(boardInstance,player);
                if (cell != null) return new Move(cell,player);
            }
            cell = getBasicCellToPlay(boardInstance);
            return new Move(cell,player);
        } else {
            throw new IllegalArgumentException();
        }
    }

    private Cell getOptimalMove(TicToeBoard board, Player player){
        Placement placement = AttackPlacement.getInstance();
        while(placement.next() != null){
            Optional<Cell> cell = placement.place(board,player);
            if(cell.isPresent()){
                return cell.get();
            }
            placement.next();
        }
        return null;
    }

    private Cell getSmartCellToPlay(TicToeBoard board, Player bot){
        // make move in a cell if it meets this two conditions
        // 1. this move will win bot the game by matching along row/column or diag
        // 2. stop user from winning the game.

        // victory move
        Cell row = getVictoryCell(board, bot, rules);
        if (row != null) return row;

        // defensive move
        Player opponent = bot.flip();
        return getDefendingCell(board, opponent, rules);
    }

    private static Cell getDefendingCell(TicToeBoard board, Player opponent, RuleEngine rules) {
        for (int row=0; row< 3; row++){
            for (int col=0;col <3; col++){
                if( board.getCell(row,col) == null){
                    Move move = new Move(new Cell(row,col), opponent);
                    TicToeBoard boardCopy = (TicToeBoard) board.move(move);
                    if (opponent.getPlayerName().equals(rules.isComplete(boardCopy).getWinner())) {
                        return new Cell(row, col);
                    }
                }
            }
        }
        return null;
    }

    private static Cell getVictoryCell(TicToeBoard board, Player bot, RuleEngine rules) {
        for (int row=0; row< 3; row++){
            for (int col=0;col <3; col++){
                if( board.getCell(row,col) == null){
                    Move move = new Move(new Cell(row,col), bot);
                    TicToeBoard boardCopy = (TicToeBoard) board.move(move);
                    if (bot.getPlayerName().equals(rules.isComplete(boardCopy).getWinner())) {
                        return new Cell(row, col);
                    }
                }
            }
        }
        return null;
    }

    private int cellsCount(TicToeBoard board){
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

    private Cell getBasicCellToPlay(TicToeBoard boardInstance){
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (boardInstance.getCell(row, col) == null) {
                    return new Cell(row, col);
                }
            }
        }
        return null;
    }
}
