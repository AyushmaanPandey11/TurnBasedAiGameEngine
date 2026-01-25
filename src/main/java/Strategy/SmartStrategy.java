package Strategy;

import Entity.boards.TicTacToeBoard;
import Entity.game.Cell;
import Entity.game.Move;
import Entity.game.Player;
import api.RuleEngine;

public class SmartStrategy extends Strategy{
    RuleEngine rules = new RuleEngine();

    @Override
    public Cell getOptimalMove(TicTacToeBoard board, Player player) {
        Cell row = getVictoryCell(board, player);
        if (row != null) return row;

        // defensive move
        Player opponent = player.flip();
        return getDefendingCell(board, opponent);
    }

    private Cell getDefendingCell(TicTacToeBoard board, Player opponent) {
        for (int row=0; row< 3; row++){
            for (int col=0;col <3; col++){
                if( board.getCell(row,col) == null){
                    Move move = new Move(Cell.getCell(row,col), opponent);
                    TicTacToeBoard boardCopy = board.move(move);
                    if (opponent.getPlayerName().equals(rules.isComplete(boardCopy).getWinner())) {
                        return Cell.getCell(row,col);
                    }
                }
            }
        }
        return null;
    }

    private Cell getVictoryCell(TicTacToeBoard board, Player bot) {
        for (int row=0; row< 3; row++){
            for (int col=0;col <3; col++){
                if( board.getCell(row,col) == null){
                    Move move = new Move(Cell.getCell(row,col), bot);
                    TicTacToeBoard boardCopy = board.move(move);
                    if (bot.getPlayerName().equals(rules.isComplete(boardCopy).getWinner())) {
                        return Cell.getCell(row,col);
                    }
                }
            }
        }
        return null;
    }
}
